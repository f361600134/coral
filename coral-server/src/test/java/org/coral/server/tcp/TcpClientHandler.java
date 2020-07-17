package org.coral.server.tcp;

import org.coral.net.core.base.Packet;
import org.coral.server.game.module.player.proto.ReqLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 客户端处理类
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	
	private static final Logger log = LoggerFactory.getLogger(TcpClientHandler.class);

    /**
     * 在到服务器的连接已经建立之后将被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	ReqLogin relogin = ReqLogin.create();
    	Object obj = Packet.encode(relogin);
    	ctx.writeAndFlush(obj);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks !", CharsetUtil.UTF_8));
        log.info("==================server channelActive==================");
    }
    
    /**
     * 当从服务器接收到一个消息时被调用
     * @param channelHandlerContext
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        log.info("Client received: {}", byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 在处理过程中引发异常时被调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
