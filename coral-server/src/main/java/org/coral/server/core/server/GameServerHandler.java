package org.coral.server.core.server;


import org.coral.net.core.base.Commander;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.Packet;
import org.coral.net.core.base.ServerHandler;
import org.coral.net.core.base.executor.DisruptorDispatchTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.buffer.ByteBuf;

/**
 * 游戏服分发处理器
 */
@Component
public class GameServerHandler extends ServerHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GameServerHandler.class);
	
	public void onConnect(GameSession session) {
		log.info("客户端连接游戏服:{}", session.getChannel().remoteAddress());
	}

	public void onReceive(GameSession session, ByteBuf message) {
		log.info("客户端连接游戏服:{}", session.getChannel().remoteAddress());
		Packet packet = null;
		try {
			if (!serverRunning) {
				log.error("服务器不在运行状态, 舍弃消息"); 
				return;
			} 
			packet = Packet.decode(message);
			log.info("收到未处理协议, processor:{}",  processor.getCommanderMap());
			Commander commander = processor.getCommander(packet.cmd());
			if (commander == null) {
				log.info("收到未处理协议, cmd=[{}]",  packet.cmd());
				return;
			}
			if (commander.isMustLogin()) {
//				if (session.getPlayerId() <= 0L) { // 未登录
//					log.debug("协议[{}]需要登录成功后才能请求", packet.cmd()); 
//					S2CCommonReply reply = S2CCommonReply.newBuilder().setProtoCode(packet.cmd()).setSuccess(1).build();
//					SimpleProtocol proto = SimpleProtocol.create(ProtocolCode.COMMON_S2C_REPLY, reply);
//					session.send(Packet.encode(proto));
//					return;
//				}
//				PlayerContext playerCtx = session.getUserData();
//				IPlayerActor playerActor = playerCtx.getPlayerActor();
//				playerActor.process(session, processor, packet);
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
}
