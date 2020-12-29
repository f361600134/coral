package org.coral.server.game.module.mission.domain;

import org.coral.server.game.module.base.MissionPo;

public abstract class AbstractMission extends MissionPo implements IMission {

	public AbstractMission(int configId) {
		super();
		this.configId = configId;
		this.progress = 0;
		this.state = MissionState.STATE_NONE.getValue();
	}

	/**
	 * 任务进度
	 */
	public boolean progressMission(int progressDelta) {
		int progress = getProgress();
		progress += progressDelta;
		progress = Math.min(progress, getCompleteValue());
		setProgress(progress);

		if(isNone() && this.isMissionCanComplete())
		{//状态为未完成且当前可以完成
			setState(MissionState.STATE_ACTIVED.getValue());
		}
		return true;
	}
	
	/**
	 *  判断任务是否可以完成
	 * @return
	 */
	private boolean isMissionCanComplete(){
		if(getCompleteType() == MissionEnum.TYPE_DEFAULT.getType() 
				&& getProgress()<getCompleteValue())
			setProgress(getCompleteValue());	//无完成条件的,直接完成
		return getProgress()>=getCompleteValue();
	}
	
}
