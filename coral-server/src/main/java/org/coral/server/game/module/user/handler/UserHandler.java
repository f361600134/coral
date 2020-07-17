package org.coral.server.game.module.user.handler;

import org.coral.net.core.annotation.Cmd;
import org.coral.net.core.base.IHandler;
import org.coral.server.game.data.proto.PBLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserHandler implements IHandler{
	
	private static final Logger log = LoggerFactory.getLogger(UserHandler.class);
	
	@Cmd(id = 0, mustLogin = false)
	public void login(Object obj, PBLogin.ReqReLogin req) {
		log.info("UserHandler login");
	}

}
