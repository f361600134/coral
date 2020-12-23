package org.coral.server.game.module.mission;

import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.module.mission.AbstractMission.MissionState;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 任务接口类
 * @author Jereme
 *
 */
public interface IMission {
	
	/** 配置id*/
	public int getConfigId();
	/** 进度*/
	public int getProgress();
	/** 状态*/
	public MissionState getState();
	/** 设置进度*/
	public void setProgress(int progress);
	/** 设置状态*/
	public void setState(MissionState state);
	/** 达成类型*/
	@JSONField(serialize=false)
	public int getCompleteType();
	/** 达成条件*/
	@JSONField(serialize=false)
	public int getCompleteCondition();
	/** 达成值*/
	@JSONField(serialize=false)
	public int getCompleteValue();
	/**转协议*/
	public PBBag.PBMissionInfo toProto();
	
	/**是否未激活*/
	@JSONField(serialize = false)
	default public boolean isNone() {
		return getState() == MissionState.STATE_NONE;
	}
	
	/**是否激活*/
	@JSONField(serialize = false)
	default public boolean isActived() {
		return getState() == MissionState.STATE_ACTIVED;
	}
	/**是否已领奖*/
	@JSONField(serialize = false)
	default public boolean isRewarded() {
		return getState() == MissionState.STATE_REWARDED;
	}
	
}
