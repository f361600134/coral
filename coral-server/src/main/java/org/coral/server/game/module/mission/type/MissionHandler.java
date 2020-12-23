package org.coral.server.game.module.mission.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.module.mission.AbstractMission.MissionState;
import org.coral.server.game.module.mission.IMission;
import org.coral.server.game.module.mission.MissionEnum;
import org.coral.server.game.module.mission.MissionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class MissionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(MissionHelper.class);

	protected long playerId;
	private Map<Integer, IMission> missions;
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
		for (IMission mission : missions.values()) {
			this.missionConfigs.put(mission.getCompleteType(), mission.getConfigId());
		}
	}

	/**
	 * 任务进度
	 */
	public boolean progressMission(int progressDelta, IMission mission) {
		if(mission==null || mission.isActived() || mission.isRewarded())
			return false;

		int progress = mission.getProgress();
		progress += progressDelta;
		progress = Math.min(progress, mission.getCompleteValue());
		mission.setProgress(progress);

		if(mission.isNone() && this.isMissionCanComplete(mission.getConfigId()))
		{//状态为未完成且当前可以完成
			mission.setState(MissionState.STATE_ACTIVED);
		}
		return true;
	}
	/**
	 * 任务是否满足完成条件
	 */
	@JSONField(serialize=false)
	private boolean isMissionCanComplete(int missionId)
	{
		IMission mission = this.missions.get(missionId);
		if(mission==null)
			return false;
		if(mission.getCompleteType()== MissionEnum.TYPE_DEFAULT.getType()
				&& mission.getProgress()<mission.getCompleteValue())
			mission.setProgress(mission.getCompleteValue());	//无完成条件的,直接完成

		return mission.getProgress()>=mission.getCompleteValue();
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
	 * 登陆
	 */
	public boolean onLogin(int loginDays) {
		try {
			//计算活动第几天
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_LOGIN.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					IMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteValue() != loginDays)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onLogin error, e:", e);
			return false;
		}
	}

}
