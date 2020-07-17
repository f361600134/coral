package org.coral.net.network.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 空闲连接检测处理器
 */
public class IdleDetectionHandler extends ChannelDuplexHandler {
	
	private static final Logger log = LoggerFactory.getLogger(IdleDetectionHandler.class);

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
            	log.debug("服务端主动断开空闲连接, {}", ctx.channel().remoteAddress());
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                // send heartbeat ...
            }
        }
	}
	
}
