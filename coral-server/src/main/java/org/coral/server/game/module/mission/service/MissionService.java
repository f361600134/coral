package org.coral.server.game.module.mission.service;

import org.coral.server.core.event.IEvent;
import org.coral.server.core.event.PlayerEventBase;
import org.coral.server.game.module.mission.domain.MainMissionDomain;
import org.coral.server.game.module.mission.manager.MainMissionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Jeremy
 *
 */
@Service
public class MissionService implements IMissionService{
	
	private static final Logger log = LoggerFactory.getLogger(MissionService.class);
	
	@Autowired private MainMissionManager manager;
	
	public void onLogin(long playerId) {
		
	}
	
	public void onLogout(long playerId) {
		manager.remove(playerId);
	}
	
	/**
	 * 所有的事件, 都走这里? 还是声明一个任务事件, 仅任务事件走这里?
	 * 理论上来说, 任务事件涵盖所有可能的事件, 所以都走一遍没什么关系
	 * @param event  
	 * @return void  
	 * @date 2021年2月21日下午11:21:25
	 */
	public void onEvent(PlayerEventBase event) {
		long playerId = event.getPlayerId();
		MainMissionDomain domain = manager.getDomain(playerId);
		if (domain  == null) {
			log.info("onEvent error, playerId:{}", playerId);
			return;
		}
	}

}
