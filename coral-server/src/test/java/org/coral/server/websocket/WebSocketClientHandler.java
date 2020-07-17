package org.coral.server.websocket;

import org.coral.net.core.base.DataCarrier;
import org.coral.net.core.base.Packet;
import org.coral.server.game.module.player.proto.ReqLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * 客户端处理类
 */
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger log = LoggerFactory.getLogger(WebSocketClientHandler.class);

	private final WebSocketClientHandshaker handshaker;
	private ChannelPromise handshakeFuture;

	public WebSocketClientHandler(WebSocketClientHandshaker handshaker) {
		this.handshaker = handshaker;
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		handshakeFuture = ctx.newPromise();
	}

	/**
	 * 在到服务器的连接已经建立之后将被调用
	 * 
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
//      ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks !", CharsetUtil.UTF_8));
		log.info("==================server channelActive==================");
		handshaker.handshake(ctx.channel());
//    	ReqLogin relogin = ReqLogin.create();
//    	Object obj = Packet.encode(relogin);
//    	ctx.writeAndFlush(obj);
	}

	/**
	 * 当从服务器接收到一个消息时被调用
	 * 
	 * @param channelHandlerContext
	 * @param byteBuf
	 * @throws Exception
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
		log.info("==================server channelRead0==================111");
		Channel ch = channelHandlerContext.channel();
		if (!handshaker.isHandshakeComplete()) {
			handshaker.finishHandshake(ch, (FullHttpResponse) msg);
			System.out.println("WebSocket Client connected!");
			handshakeFuture.setSuccess();
			sendTestMessage(channelHandlerContext);
			return;
		}
		log.info("==================server channelRead0==================222");
		if (msg instanceof FullHttpResponse) {
			FullHttpResponse response = (FullHttpResponse) msg;
			throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + response.status() + ", content="
					+ response.content().toString(CharsetUtil.UTF_8) + ')');
		}
		log.info("==================server channelRead0==================333");
		WebSocketFrame frame = (WebSocketFrame) msg;
		if (frame instanceof TextWebSocketFrame) {
			TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
			System.out.println("WebSocket Client received message: " + textFrame.text());
		} else if (frame instanceof PongWebSocketFrame) {
			System.out.println("WebSocket Client received pong");
		} else if (frame instanceof CloseWebSocketFrame) {
			System.out.println("WebSocket Client received closing");
			ch.close();
		} else if (frame instanceof BinaryWebSocketFrame) {
			log.info("==================server channelRead0==================444");
			BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
			ByteBuf byteBuf = binaryWebSocketFrame.content();
			Packet packet = Packet.decode(byteBuf);
			System.out.println("WebSocket Client received, msgId:{}" + packet.cmd());
		}
	}

	private void sendTestMessage(ChannelHandlerContext ctx) throws Exception {
		ReqLogin relogin = ReqLogin.create();
		DataCarrier obj = Packet.encode(relogin);
		BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(createBuf(obj));
		ctx.writeAndFlush(binaryWebSocketFrame);
	}

	private ByteBuf createBuf(DataCarrier obj) {
		ByteBuf byteBuf = Unpooled.buffer(256);
		byteBuf.writeShort(obj.getProtocol());
		byteBuf.writeBytes(obj.getStructure());
		return byteBuf;
	}

	/**
	 * 在处理过程中引发异常时被调用
	 * 
	 * @param ctx
	 * @param cause
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		if (!handshakeFuture.isDone()) {
			handshakeFuture.setFailure(cause);
		}
		ctx.close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
		/* 心跳处理 */
		if (evt instanceof IdleStateEvent) {
			// ctx.channel().writeAndFlush(new ReqHeartbeatMsg());
			ReqLogin relogin = ReqLogin.create();
			Object obj = Packet.encode(relogin);
			ctx.channel().writeAndFlush(obj);
		}
	}
}
