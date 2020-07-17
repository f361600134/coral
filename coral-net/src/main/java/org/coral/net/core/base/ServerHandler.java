package org.coral.net.core.base;


import org.coral.net.core.HandlerProcessor;
import org.coral.net.core.base.executor2.DisruptorDispatchTask;
import org.coral.net.core.base.executor2.DisruptorExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.buffer.ByteBuf;

/**
 * 游戏服分发处理器
 */
@Component
public class ServerHandler implements IServerHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ServerHandler.class);
	
	@Autowired protected HandlerProcessor processor;
	
	@Autowired protected DisruptorExecutorGroup group;
	
	protected boolean serverRunning; // 服务器状态, true-运行中
	
	public void onConnect(GameSession session) {
		log.info("客户端连接游戏服:{}", session.getChannel().remoteAddress());
	}

	public void onReceive(GameSession session, ByteBuf message) {
		Packet packet = null;
		try {
			if (!serverRunning) {
				log.error("服务器不在运行状态, 舍弃消息"); 
				return;
			} 
			packet = Packet.decode(message);
			Commander commander = processor.getCommander(packet.cmd());
			if (commander == null) {
				log.info("收到未处理协议, cmd=[{}]",  packet.cmd());
				return;
			}
			if (commander.isMustLogin()) {
				//TODO
				log.info("协议[{}]需要登录成功后才能请求", packet.cmd()); 
				return;
			} else {
				group.execute(session.getId(), new DisruptorDispatchTask(processor, session, packet));
			}
		} catch (Exception e) {
			if (packet == null) {
				log.error("协议解析失败", e);
			} else {
				log.error("Packet调用过程出错, cmd={}", packet.cmd(), e);
			}
		} 
		
	}

	@Override
	public void onClose(GameSession session) {
		log.info("客户端连接断开:{}", session.getChannel().remoteAddress());
	}

	@Override
	public void onException(GameSession session, Throwable e) {
		log.error("游戏协议通信过程出错", e);
	}

	@Override
	public void serverStatus(boolean running) {
		this.serverRunning = running;
	}
	
}
