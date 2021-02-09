package org.coral.server.game.module.mission.type;

import org.coral.orm.core.annotation.PO;
import org.coral.server.game.data.config.ConfigMissionMgr;
import org.coral.server.game.module.mission.domain.AbstractMission;

/**
 * 	任务类型-主线任务类, 单体任务类
 * @author Jeremy
 */
public class MainMission extends AbstractMission{

	public MainMission(int configId) {
		super(configId);
	}

	@Override
	public int getCompleteType() {
		return ConfigMissionMgr.getConfig(configId).getCompleteType();
	}

	@Override
	public int getCompleteCondition() {
		return ConfigMissionMgr.getConfig(configId).getCompleteValue();
	}

	@Override
	public int getCompleteValue() { 
		return ConfigMissionMgr.getConfig(configId).getCompleteTotal();
	}
	
}
