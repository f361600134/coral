package org.coral.server.game.module.mission.manager;

import java.util.Map;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.module.mission.domain.MainMission;
import org.coral.server.game.module.mission.domain.MainMissionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class MainMissionManager {
	
	@Autowired private DataProcessorAsyn process;
	
	/**玩家任务缓存*/
	private final static Map<Long, MainMissionDomain> PLAYER_DOMAINS = Maps.newConcurrentMap();
	
	/**
	 * 获取玩家背包
	 */
	public MainMissionDomain getDomain(long playerId) {
		MainMissionDomain domain = PLAYER_DOMAINS.get(playerId);
		if (domain == null) {
			MainMission mission = process.selectOneByIndex(MainMission.class, new Object[] {playerId});
			if (mission == null) {
				mission = MainMission.create(playerId);
			}
			domain = new MainMissionDomain();
			domain.init(mission);
			PLAYER_DOMAINS.put(playerId, domain);
		}
		return domain;
	}
	

}
