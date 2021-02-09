package org.coral.server.game.module.mission.domain;

import org.coral.server.game.data.proto.PBBag;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 任务接口类
 * @author Jeremy
 */
public interface IMission {
	
	/** 配置id*/
	public int getConfigId();
	/** 进度*/
	public int getProgress();
	/** 状态*/
	public int getState();
	/** 设置进度*/
	public void setProgress(int progress);
	/** 设置状态*/
	public void setState(int state);
	/** 达成类型*/
	@JSONField(serialize=false)
	public int getCompleteType();
	/** 达成条件*/
	@JSONField(serialize=false)
	public int getCompleteCondition();
	/** 达成值*/
	@JSONField(serialize=false)
	public int getCompleteValue();
	
	/**是否未激活*/
	@JSONField(serialize = false)
	default public boolean isNone() {
		return getState() == MissionState.STATE_NONE.getValue();
	}
	
	/**是否激活*/
	@JSONField(serialize = false)
	default public boolean isActived() {
		return getState() == MissionState.STATE_ACTIVED.getValue();
	}
	/**是否已领奖*/
	@JSONField(serialize = false)
	default public boolean isRewarded() {
		return getState() == MissionState.STATE_REWARDED.getValue();
	}
	
	/**
	 * 处理任务
	 * @param processData 完成次数
	 */
	public boolean progressMission(int processData);
	
	/**
	 * 转协议
	 * @return
	 */
	default public PBBag.PBMissionInfo toProto() {
		PBBag.PBMissionInfo.Builder builder = PBBag.PBMissionInfo.newBuilder();
		builder.setConfigId(getConfigId());
		builder.setProgress(getProgress());
		builder.setState(getState());
		return builder.build();
	}
	
}
