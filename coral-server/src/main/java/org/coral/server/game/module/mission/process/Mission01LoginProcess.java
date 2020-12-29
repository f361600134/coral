package org.coral.server.game.module.mission.process;

import org.coral.server.core.event.IEvent;
import org.coral.server.game.module.mission.domain.IMission;
import org.coral.server.game.module.mission.domain.MissionEnum;
import org.coral.server.game.module.player.event.PlayerAfterLoginEvent;
import org.springframework.stereotype.Component;

/**
 * 这里增加事件，表示某一个事件触发。
 * @auth Jeremy
 * @date 2020年12月28日上午12:17:25
 */
@Component
public class Mission01LoginProcess extends AbstractMissionProcess{
	
	/**
	 * 处理类型
	 */
	public int type() {
		return MissionEnum.TYPE_LOGIN.getType();
	}

	@Override
	public String[] focusEvents() {
		return new String[] {PlayerAfterLoginEvent.ID};
	}

	@Override
	public boolean doProcess(int value, IMission mission, IEvent event) {
		if (mission.getCompleteValue() == value) {
			mission.progressMission(1);
			return true;
		}
		return false;
	}

}
