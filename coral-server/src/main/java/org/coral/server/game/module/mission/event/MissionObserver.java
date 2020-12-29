package org.coral.server.game.module.mission.event;

import org.coral.server.core.event.IEvent;
import org.coral.server.core.event.IObserver;
import org.springframework.stereotype.Component;

@Component
public class MissionObserver implements IObserver {
	
	/**
	 * 任务默认接收所有事件,然后在任务内进行事件判断
	 * @param event
	 */
	public void onLogin(IEvent event){
		
	}
	
}
