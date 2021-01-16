package org.coral.server.game.module.mission.domain;

/**
 * 主线任务类
 * @author Administrator
 *
 */
public class MainMission extends AbstractMission{

	public MainMission(int configId) {
		super(configId);
	}

	@Override
	public int getCompleteType() {
		return 0;
	}

	@Override
	public int getCompleteCondition() {
		return 0;
		
	}

	@Override
	public int getCompleteValue() { 
		return 0;
	}

}
