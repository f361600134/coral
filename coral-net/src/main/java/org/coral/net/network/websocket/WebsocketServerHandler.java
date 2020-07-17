package org.coral.net.network.websocket;

import java.io.IOException;

import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

/**
 * 游戏服务器消息处理器 注意: 1. 一条连接对应一个TcpServerHandler 2. 一条连接对应一个GameSession 3.
 * 所有TcpServerHandler持有的IServerHandler都是同一个引用
 */
public class WebsocketServerHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger log = LoggerFactory.getLogger(WebsocketServerHandler.class);

	// 处理握手
	private WebSocketServerHandshaker handshaker;

	private GameSession session;
	private IServerHandler serverHandler;

	public WebsocketServerHandler(IServerHandler serverHandler) {
		this.serverHandler = serverHandler;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("============channelActive=============");
		session = GameSession.create(ctx.channel()); // 新建session
		serverHandler.onConnect(session);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("============channelInactive=============");
		serverHandler.onClose(session);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		log.info("============channelRead0============={}", msg);
		if (msg instanceof HttpRequest) { // 处理握手请求
			handleHttpRequest(ctx, (HttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) { // 处理其他请求
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}else {
			log.info("Not found msg:{}", msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.info("============exceptionCaught=============");
		if (cause instanceof IOException) {
			log.info("exceptionCaught IOException error, e:", cause);
			return;
		} else {
			cause.printStackTrace();
			serverHandler.onException(session, cause);
			log.info("exceptionCaught error, e:", cause);
		}
	}

	/**
	 * 第一次请求是http请求, 握手升级为websocket
	 * 
	 * @date 2020年7月16日
	 * @param ctx
	 * @param req
	 */
	private void handleHttpRequest(ChannelHandlerContext ctx, HttpRequest req) {
//		log.info("========[{}]处理握手", ctx.channel());
		// Handle a bad request.
		if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
			log.info("[{}]握手失败,[{}][{}]", ctx.channel(), req.decoderResult().isSuccess(),
					req.headers().get("Upgrade"));
			sendHttpResponse(ctx, req,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}

		// Handshake
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req),
				null, true, 5 * 1024 * 1024);
		handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
			log.info("[{}]握手失败,不支持的websocket版本", ctx.channel());
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			log.info("[{}]处理握手", ctx.channel());
			handshaker.handshake(ctx.channel(), req);
		}
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		log.info("========[{}]处理协议", ctx.channel());
		// Check for closing frame
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
		}
		// 心跳信息
		if (frame instanceof PingWebSocketFrame) {
			Channel channel = ctx.channel();
			log.info("channel[{}], 用户[{}],websocket心跳请求", channel, session.getPlayerId());
			ctx.write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		// 不支持文本协议
		if (frame instanceof TextWebSocketFrame) {
			// Echo the frame
			log.info("=============解析消息TextWebSocketFrame=============");
			ctx.write(frame.retain());
			return;
		}
		if (frame instanceof BinaryWebSocketFrame) {
			BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
			ByteBuf buf = binaryWebSocketFrame.content();
			log.info("=============解析消息BinaryWebSocketFrame=============");
			// 解析消息
			serverHandler.onReceive(session, buf);
		}
	}

	private String getWebSocketLocation(HttpRequest req) {
		String location = req.headers().get(HttpHeaderNames.HOST) + "/websocket";
		return "ws://" + location;
	}

	private void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req, FullHttpResponse res) {
		// Generate an error page if response getStatus code is not OK (200).
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
			HttpUtil.setContentLength(res, res.content().readableBytes());
		}

		// Send the response and close the connection if necessary.
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

}
