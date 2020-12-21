package org.coral.server.game.module.mission;

import org.coral.server.game.data.proto.PBBag;

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
	public int getState();
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
	
}
