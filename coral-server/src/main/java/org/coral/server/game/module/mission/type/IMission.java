package org.coral.server.game.module.mission.type;

import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.module.mission.domain.MissionState;

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
	
	/** 设置额外参数*/
	public void setAdditional(long additional);
	/** 获取额外参数*/
	public long getAdditional();
	
	//此项仅支持单条件达成值
	//达成类型, 达成条件, 达成值如果为多条件, 则用数组表示
	//对于任务执行, 则通过完成类型,多个任务执行代理同一条任务
	/** 达成类型*/
	@JSONField(serialize=false)
	public int getCompleteType();
	/** 达成条件*/
	@JSONField(serialize=false)
	public int getCompleteCondition();
	/** 达成值*/
	@JSONField(serialize=false)
	public int getCompleteValue();
	
	/**是否未完成*/
	@JSONField(serialize = false)
	default public boolean isNone() {
		return getState() == MissionState.STATE_NONE.getValue();
	}
	
	/**是否完成*/
	@JSONField(serialize = false)
	default public boolean isComplete() {
		return getState() == MissionState.STATE_COMPLETE.getValue();
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
