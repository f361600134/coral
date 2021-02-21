package org.coral.server.game.module.mission.manager;

import org.coral.server.core.server.AbstractModuleManager;
import org.coral.server.game.module.mission.domain.MainMission;
import org.coral.server.game.module.mission.domain.MainMissionDomain;
import org.springframework.stereotype.Component;

@Component
public class MainMissionManager extends AbstractModuleManager<MainMissionDomain>{
	
	/**
	 * 获取玩家背包
	 */
	public MainMissionDomain getDomain(long playerId) {
		MainMissionDomain domain = domains.get(playerId);
		if (domain == null) {
			MainMission mission = process.selectOneByIndex(MainMission.class, new Object[] {playerId});
			if (mission == null) {
				mission = MainMission.create(playerId);
			}
			domain = new MainMissionDomain();
			domain.init(mission);
			domains.put(playerId, domain);
		}
		return domain;
	}

}
