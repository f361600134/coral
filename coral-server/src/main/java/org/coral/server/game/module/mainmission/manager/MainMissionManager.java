package org.coral.server.game.module.mainmission.manager;

import org.coral.server.core.server.AbstractModuleManager;
import org.coral.server.game.module.mainmission.domain.MainMission;
import org.coral.server.game.module.mainmission.domain.MainMissionDomain;
import org.springframework.stereotype.Component;

@Component
public class MainMissionManager extends AbstractModuleManager<MainMissionDomain>{
	
	@Override
	public MainMissionDomain getFromDb(long playerId) {
		MainMissionDomain domain = new MainMissionDomain();
		MainMission mission = process.selectOneByIndex(MainMission.class, new Object[] {playerId});
		if (mission == null) {
			mission = MainMission.create(playerId);
		}
		domain.init(mission);
		return domain;
	}

}
