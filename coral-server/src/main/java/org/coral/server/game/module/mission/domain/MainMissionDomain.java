package org.coral.server.game.module.mission.domain;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.game.data.config.ConfigMissionMgr;
import org.coral.server.game.data.config.pojo.ConfigMission;
import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.module.mission.handler.AbstractMissionHandler;
import org.coral.server.game.module.mission.type.IMission;
import org.coral.server.game.module.mission.type.MainMissionType;
import org.coral.server.game.module.mission.type.MissionTypeData;
import org.coral.server.game.module.resource.IResourceGroupService;

public class MainMissionDomain extends AbstractMissionHandler{
	
	private static final Logger log = LogManager.getLogger(MainMissionDomain.class);
	
	//主线任务
	private MainMission mainMission;
	
	public MainMissionDomain() {
		this.mainMission = new MainMission();
	}
	/**
	 * 初始化任务
	 */
	public void init(MainMission mainMission) {
		this.mainMission = mainMission;
	}
	
	@Override
	public Collection<? extends IMission> getMissions() {
		return mainMission.getMissionData().getMissionPojos().values();
	}
	
	@Override
	public IMission getMission(int configId) {
		return mainMission.getMissionData().getMission(configId);
	}
	
	@Override
	public long getPlayerId() {
		return mainMission.getPlayerId();
	}
	
	@Override
	public void afterProcess() {
		mainMission.update();
	}
	
	/**
	 * 这样处理着实有风险, 因为暴露了接口外部可以调用
	 */
	@Override
	protected void doReward(IMission mission) {
		final int configId = mission.getConfigId();
		ConfigMission config = ConfigMissionMgr.getConfig(configId);
		IResourceGroupService itemService = SpringContextHolder.getInstance().getBean(IResourceGroupService.class);
		itemService.reward(getPlayerId(), config.getRewardMap(), NatureEnum.GM);
	}
	
	@Override
	protected void afterRewarded(IMission mission) {
		final int configId = mission.getConfigId();
		//	领奖后,主线任务移除掉旧务,加入任务链的下一个任务  
		MissionTypeData<MainMissionType> missionData = mainMission.getMissionData();
		missionData.onFinished(configId);
		//ConfigMission config = ConfigMissionMgr.getConfig(configId);
		//int nextConfigId = config.getNextMissionId();
		int nextConfigId = 0;
		MainMissionType nextNission = MainMissionType.create(nextConfigId);
		missionData.addMissionPojo(nextNission);
		mainMission.update();
	}
	
}