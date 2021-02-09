package org.coral.server.game.module.mission.type;

import org.coral.server.game.module.mission.domain.AbstractMission;

/**
 * 	任务类型-神器任务
 * @author Jeremy
 */
public class ArtifactMission extends AbstractMission{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1376359651686295501L;

	public ArtifactMission(int configId) {
		super(configId);
	}

	@Override
	public int getCompleteType() {
		//return ConfigMissionMgr.getConfig(getConfigId()).getCompleteType();
		return 0;
	}
 
	@Override
	public int getCompleteCondition() {
		//return ConfigMissionMgr.getConfig(getConfigId()).getCompleteCondition();
		return 0;
	}

	@Override
	public int getCompleteValue() {
		//return ConfigMissionMgr.getConfig(getConfigId()).getCompleteValue();
		return 0;
	}


}
