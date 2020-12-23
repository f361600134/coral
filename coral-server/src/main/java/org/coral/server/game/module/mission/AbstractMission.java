package org.coral.server.game.module.mission;

import org.coral.server.game.data.proto.PBBag;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class AbstractMission implements IMission {

	protected int configId; // 任务id
	protected int progress; // 任务进度
	protected MissionState state; // 任务状态
	
	public enum MissionState{
		STATE_NONE(0),		//未激活
		STATE_ACTIVED(1),	//已激活
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

	public AbstractMission(int configId, int progress) {
		super();
		this.configId = configId;
		this.progress = progress;
		this.state = MissionState.STATE_NONE;
	}

	@Override
	public int getConfigId() {
		return configId;
	}

	@Override
	public int getProgress() {
		return progress;
	}

	@Override
	public MissionState getState() {
		return state;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void setState(MissionState state) {
		this.state = state;
	}

	@Override
	public PBBag.PBMissionInfo toProto() {
		PBBag.PBMissionInfo.Builder builder = PBBag.PBMissionInfo.newBuilder();
		builder.setConfigId(getConfigId());
		builder.setProgress(getProgress());
		builder.setState(getState().getValue());
		return builder.build();
	}

}
