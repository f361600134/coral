package org.coral.server.game.module.mission.type;

import java.util.Map;

import org.coral.server.game.data.config.ConfigMissionMgr;

/**
 * 	任务类型-主线任务类, 单体任务类
 * @author Jeremy
 */
public class MainMissionType extends AbstractMission{

	public MainMissionType(int configId) {
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
	
	@Override
	public Map<Integer, Integer> getReward() {
		return ConfigMissionMgr.getConfig(configId).getRewardMap();
	}

	public static MainMissionType create(int configId) {
		return new MainMissionType(configId);
	}
	
	@Override
	public String toString() {
		return "MainMissionType [configId=" + configId + ", state=" + state + "]";
	}
	
}
