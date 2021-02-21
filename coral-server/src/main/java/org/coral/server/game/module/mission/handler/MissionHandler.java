package org.coral.server.game.module.mission.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.coral.server.core.event.IEvent;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.module.mission.manager.MissionProcessManager;
import org.coral.server.game.module.mission.process.IMissionProcess;
import org.coral.server.game.module.mission.type.IMission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * 任务处理器,应该是一个通用的处理器
 * @author Jeremy
 */
public class MissionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(MissionProcessManager.class);

	protected long playerId;
	/**
	 * key:任务id
	 * value:任务对象
	 */
	private Map<Integer, IMission> missions;
	
	/**
	 * key:任务类型
	 * value: 任务id集合
	 */
	private transient Multimap<Integer, Integer> missionConfigs;

	public MissionHandler() {
		this.missions = Maps.newHashMap();
		this.missionConfigs = ArrayListMultimap.create();
	}

	public MissionHandler(long playerId) {
		this.playerId = playerId;
		this.missions = Maps.newHashMap();
		this.missionConfigs = ArrayListMultimap.create();
	}
	
	public IMission getMission(int configId) {
		return missions.get(configId);
	}

	public Map<Integer, IMission> getMissions() {
		return missions;
	}
	
	public void setMissions(Map<Integer,IMission> missions) {
		this.missions = missions;
		this.missionConfigs.clear();
		for (IMission mission : missions.values()) {
			this.missionConfigs.put(mission.getCompleteType(), mission.getConfigId());
		}
	}

	/**
	 * 判断全部是否领取, 全部领取则可以激活
	 * @return
	 */
	public boolean isAllReward() {
		for (IMission mission : missions.values()) {
			if (!mission.isRewarded()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否领取, 全部领取则可以激活
	 * @param configId
	 * @return
	 */
	public boolean isRewarded(int configId) {
		IMission mission = getMission(configId);
		return mission == null ? false : mission.isRewarded();
	}

	/**
	 * 判断是否激活
	 * @param configId
	 * @return
	 */
	public boolean isActived(int configId) {
		IMission mission = getMission(configId);
		return mission == null ? false : mission.isActived();
	}

	public Collection<PBBag.PBMissionInfo> toProto() {
		List<PBBag.PBMissionInfo> colls = new ArrayList<PBBag.PBMissionInfo>();
		for (IMission mission : missions.values()) {
			colls.add(mission.toProto());
		}
		return colls;
	}
	
	/**
	 * 处理任务
	 * 1.任务处理管理器通过任务类型获取到处理类,
	 * 2.通过任务类型获取到任务列表
	 * 3.当前任务集合,通过任务列表获取到任务对象, 被任务处理类去处理任务
	 * @return
	 */
	public boolean onProcess(IEvent event, int value) {
		//TODO
		MissionProcessManager manager =  new MissionProcessManager();
		//获取到任务类型
		Collection<Integer> missionTypes = manager.getMissionTypes(event.getEventId());
		for (Integer missionType : missionTypes) {
			IMissionProcess missionProcess = manager.getProcess(missionType);
			Collection<Integer> configIds = missionConfigs.get(missionType);
			for (Integer configId : configIds) {
				IMission mission = missions.get(configId);
				boolean bool = missionProcess.process(value, mission, event);
				if (!bool) break;
			}
		}
		return false;
	}
	
	/**
	 * 当处理任务
	 * @return
	 */
	public boolean onProcess(int value, int value2) {
		return false;
	}
	
}
