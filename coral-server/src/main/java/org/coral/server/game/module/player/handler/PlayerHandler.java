package org.coral.server.game.module.player.handler;

import org.coral.net.core.annotation.Cmd;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IHandler;
import org.coral.server.game.data.proto.PBLogin;
import org.coral.server.game.module.player.proto.AckLoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlayerHandler implements IHandler{
	
	private static final Logger log = LoggerFactory.getLogger(PlayerHandler.class);
	
	@Cmd(id = 1, mustLogin = false)
	public void login(GameSession session, PBLogin.ReqLogin req) {
		log.info("PlayerHandler login， session:{}", session);
		AckLoginResp ack = AckLoginResp.create();
		session.push(ack);
	}

}
