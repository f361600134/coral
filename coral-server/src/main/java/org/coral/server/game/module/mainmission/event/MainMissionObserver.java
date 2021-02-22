package org.coral.server.game.module.mainmission.event;

import org.coral.server.core.event.IObserver;
import org.coral.server.core.event.PlayerEventBase;
import org.coral.server.game.module.mainmission.service.MainMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMissionObserver implements IObserver {
	
	@Autowired private MainMissionService missionService;
	
	/**
	 * 任务默认接收所有事件,然后在任务内进行事件判断
	 * @param event
	 */
	public void onEvent(PlayerEventBase event){
		missionService.onEvent(event);
	}
	
}
