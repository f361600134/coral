package org.coral.server.game.module.mission.domain;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.game.module.mission.type.MainMissionType;

public class MainMissionDomain {
	
	private static final Logger log = LogManager.getLogger(MainMissionDomain.class);

	//主线任务
	private MainMission mainMission;
//	private Map<Integer, IMission> missions;
//	private MissionHandler handler;

	public MainMissionDomain() {
//		this.handler = new MissionHandler();
	}
	
	/**
	 * 初始化任务
	 */
	public void init(MainMission mainMission) {
		this.mainMission = mainMission;
	}
	
	/**
	 * 获取所有任务列表
	 * @return
	 */
	public Collection<MainMissionType> getAllMission(){
		return mainMission.getMissionData().getMissionPojos().values();
	}
	
}