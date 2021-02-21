package org.coral.server.game.module.mission.type;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 任务类型数据, 对任务进行封装
 * @auth Jeremy
 * @date 2021年2月11日下午4:06:54
 */
public class MissionTypeData<T extends AbstractMission> {

	/**
	 * 当前已接取具体任务类, 完成以后从任务列表中移除, 
	 * 如无特殊情况, 不允许初始化时加载玩家所有任务数据,
	 */
	private Map<Integer, T> missionPojos;

	/**
	 * 已完成任务Id列表, 对已经完成的任务, 在此记录
	 */
	private List<Integer> finishIds;

	public MissionTypeData() {
		this.missionPojos = Maps.newHashMap();
		this.finishIds = Lists.newArrayList();
	}

	public Map<Integer, T> getMissionPojos() {
		return missionPojos;
	}

	public void setMissionPojos(Map<Integer, T> missionPojos) {
		this.missionPojos = missionPojos;
	}

	public List<Integer> getFinishIds() {
		return finishIds;
	}

	public void setFinishIds(List<Integer> finishIds) {
		this.finishIds = finishIds;
	}

	@Override
	public String toString() {
		return "MissionTypeData [missionPojos=" + missionPojos + ", finishIds=" + finishIds + "]";
	}

}
