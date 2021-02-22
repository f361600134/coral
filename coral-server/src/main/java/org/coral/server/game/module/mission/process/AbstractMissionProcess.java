package org.coral.server.game.module.mission.process;

import org.coral.server.core.event.IEvent;
import org.coral.server.game.module.mission.type.IMission;

public abstract class AbstractMissionProcess implements IMissionProcess{
	
	/**
	 * 任务处理器
	 * return true, 任务正确处理完流程, false如果无次任务, 或者任务不是未达成状态, 则表示未完成
	 */
	@Override
	public boolean process(IMission mission, IEvent event) {
		//	处理任务, 如果任务不是未完成状态, 则跳过
		if (mission == null || !mission.isNone()) {
			return false;
		}
		//	如果条件不为0,则判断条件是否与达成条件一致.
		int condition = getCondition(mission, event);
		if (condition != 0 && mission.getCompleteCondition() != condition) {
			return false;
		}
		//	具体完成值,
		int completeValue = getCompleteValue(mission, event);
		mission.progressMission(completeValue);
		return true;
	}

}
