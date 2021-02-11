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
public class MissionTypeData {

	/**
	 * 当前已接取具体任务类
	 */
	private Map<Integer, IMission> missionPojos;

	/**
	 * 已完成任务Id列表
	 */
	private List<Integer> finishIds;

	public MissionTypeData() {
		this.missionPojos = Maps.newHashMap();
		this.finishIds = Lists.newArrayList();
	}

	public Map<Integer, IMission> getMissionPojos() {
		return missionPojos;
	}

	public void setMissionPojos(Map<Integer, IMission> missionPojos) {
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
