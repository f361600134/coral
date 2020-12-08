package org.coral.server.game.module.chat.service;

import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class CommandAssist {
	
	private static final Logger log = LoggerFactory.getLogger(CommandService.class);

//	@Autowired
//	private ItemController itemController;
//	@Autowired
//	private ItemService itemService;
//	
//	@Autowired
//	private CheckinController checkinController;
//
//	@Autowired
//	private PlayerResourceAssist resourceAssist;
//
//	@Autowired
//	private SessionManager sessionManager;
	
	
	public static Map<String, Method> methodMap = Maps.newHashMap();
	static {
		Method[] methods = CommandAssist.class.getDeclaredMethods();
		for (Method method : methods) {
			methodMap.put(method.getName().toLowerCase(), method);
		}
	}
	
//	/**
//	 * @test onLogin
//	 */
//	public void onLogin(long playerId, String data[]) {
//		Session session = sessionManager.getSession(playerId);
//		itemService.onLogin(playerId);
//	}
//	
//	/**
//	 * @test sendMail,1
//	 */
//	public void sendMail(long playerId, String data[]) {
////		Session session = sessionManager.getSession(playerId);
//		int configId = Integer.valueOf(data[1]);
//		Context.getMailService().sendMail(playerId, configId, Maps.newHashMap(), "");
//	}
//	
//	/**
//	 * @test getOtherPlayer,1
//	 */
//	public void getOtherPlayer(Session session, String data[]) {
//		long playerId = Integer.valueOf(data[1]);
//		OtherPlayer otherPlayer = Context.getOtherPlayerService().getOtherPlayerById(playerId);
//		log.info("getOtherPlayer playerId:{}, otherPlayer:{}", playerId, otherPlayer);
//	}
//	/**
//	 * @test getOtherPlayer,1
//	 */
//	public void checkinInfo(Session session, String data[]) {
//		ReqCheckinInfo.Builder req = ReqCheckinInfo.newBuilder();
//		checkinController.ReqCheckinInfo(session, req.build());
//	}
//	
//	/**
//	 * @test getOtherPlayer,1
//	 */
//	public void checkin(Session session, String data[]) {
//		ReqCheckin.Builder req = ReqCheckin.newBuilder();
//		checkinController.ReqCheckin(session, req.build());
//	}

}
