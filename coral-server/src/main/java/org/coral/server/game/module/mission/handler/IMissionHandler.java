package org.coral.server.game.module.mission.handler;

import java.util.Collection;
import java.util.List;

import org.coral.server.core.event.IEvent;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.mission.type.IMission;

/**
 * 	任务处理类接口
 * @author Jeremy
 *
 */
public interface IMissionHandler {
	
	
	public long getPlayerId();
	
	/**
	 * 	通过任务id获取到任务
	 * @param configId
	 * @return
	 */
	public IMission getMission(int configId);
	
	/**
	 * 获取所有的任务集合
	 * @return
	 */
	public Collection<? extends IMission> getMissions();
	
	/**
	 * 	任务序列化
	 * @return
	 */
	public Collection<PBBag.PBMissionInfo> toProto();
	
	/**
	 * 	当处理任务
	 * @param event
	 * @param value
	 * @return
	 */
	public ErrorCode onProcess(IEvent event);
	
	
	/**
	 * 	当领取任务奖励
	 * @param event
	 * @param value
	 * @return
	 */
	public ErrorCode onReward(int configId);
	
	/**
	 * 获取更新的任务信息
	 * @return
	 */
	public List<IMission> getUpdateList();
	
}
