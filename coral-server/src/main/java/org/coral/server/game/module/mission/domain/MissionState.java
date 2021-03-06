package org.coral.server.game.module.mission.domain;

/**
 * 	任务状态
 * @author Jeremy
 */
public enum MissionState{
	STATE_NONE(0),		//未完成
	STATE_COMPLETE(1),	//已完成
	STATE_REWARDED(2),	//已領取
	;
	private final int value;
	private MissionState(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}