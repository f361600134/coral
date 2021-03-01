package org.coral.server.game.module.mainmission.service;

import java.util.List;

import org.coral.server.core.event.PlayerEventBase;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.mainmission.domain.MainMissionDomain;
import org.coral.server.game.module.mainmission.manager.MainMissionManager;
import org.coral.server.game.module.mission.type.IMission;
import org.coral.server.game.module.player.service.PlayerService;
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
public class MainMissionService implements IMainMissionService{
	
	private static final Logger log = LoggerFactory.getLogger(MainMissionService.class);
	
	@Autowired private PlayerService playerService;
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
		domain.onProcess(event);
	}
	
	/**
	 * 更新任务信息到客户端
	 * @param domain
	 */
	public void responseMissions(MainMissionDomain domain) {
		List<IMission> updateList = domain.getUpdateList();
		if (updateList.isEmpty()) {
			return;
		}
//		//1.new Resproto
//		List<PBMissionInfo> proto = Lists.newArrayList();
//		for (IMission iMission : updateList) {
//			proto.add(iMission.toProto());
//		}
//		playerService.sendMessage(domain.getPlayerId(), proto);
	}
	
	/**
	 * 任务领取奖励
	 * @param playerId 玩家id
	 * @param configId 任务id
	 * @return
	 */
	public ErrorCode rewardMission(long playerId, int configId) {
		MainMissionDomain domain = manager.getDomain(playerId);
		if (domain == null) {
			return ErrorCode.UNKNOWN_ERROR; //错误码
		}
		ErrorCode errorCode = domain.onReward(configId, NatureEnum.Unknown);
		if (errorCode.isSuccess()) {
			//	更新任务状态
			responseMissions(domain);
		}
		return ErrorCode.SUCCESS;
	}

}
