package org.coral.server.game.module.mission.handler;

import java.util.Collection;
import java.util.List;

import org.coral.server.core.event.IEvent;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.mission.type.AbstractMission;
import org.coral.server.game.module.mission.type.IMission;
import org.coral.server.game.module.mission.type.MissionTypeData;

/**
 * 	任务处理类接口
 * @author Jeremy
 *
 */
public interface IMissionHandler {
	
	public long getPlayerId();
	
	/**
	 * 新增接口, 获取统一的MissionTypeData, 替代掉getMission, getMissions方法
	 * 任务处理器, 不应该直接代理任务类, 应该代理MissionTypeData, 具体任务类 由MissionTypeData去控制
	 * @return
	 */
	public MissionTypeData<? extends AbstractMission> getMissionTypeData();
	
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
	public ErrorCode onReward(int configId, NatureEnum nenum);
	
	/**
	 * 获取更新的任务信息
	 * @return
	 */
	public List<IMission> getUpdateList();
	
}
