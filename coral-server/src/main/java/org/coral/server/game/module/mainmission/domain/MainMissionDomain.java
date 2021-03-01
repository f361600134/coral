package org.coral.server.game.module.mainmission.domain;

import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.core.event.IEvent;
import org.coral.server.core.server.AbstractModuleDomain;
import org.coral.server.game.data.proto.PBBag.PBMissionInfo;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.mission.handler.DefaultMissionHandler.Builder;
import org.coral.server.game.module.mission.handler.IMissionHandler;
import org.coral.server.game.module.mission.type.IMission;
import org.coral.server.game.module.mission.type.MainMissionType;
import org.coral.server.game.module.mission.type.MissionTypeData;

public class MainMissionDomain extends AbstractModuleDomain<MainMission> implements IMissionHandler{
	
	private static final Logger log = LogManager.getLogger(MainMissionDomain.class);
	
	//代理处理代理类
	private IMissionHandler missionHandler;
	
	public MainMissionDomain() {
	}

	public long getPlayerId() {
		return bean.getPlayerId();
	}
	
	@Override
	public void initData(List<MainMission> v) {
		super.initData(v);
		this.missionHandler = Builder.newBuilder()
				.playerId(getPlayerId())
				.missionData(getMissionTypeData())
				.afterRewardedListener((mission, missionData)->{
					//	主线任务完成后,需要接取下一条任务
					//final int configId = mission.getConfigId();
					//ConfigMission config = ConfigMissionMgr.getConfig(configId);
					//int nextConfigId = config.getNextMissionId();
					int nextConfigId = 0;
					IMission nextNission = MainMissionType.create(nextConfigId);
					missionData.addMissionPojo(nextNission);
				})
				.build();
	}
	
	@Override
	public MissionTypeData<? extends IMission> getMissionTypeData() {
		//初始化时指定了具体子类型, 所以获取的时候, 为了通用处理,需要使用?表示为Imission子类型
		return bean.getMissionData();
	}
	
	@Override
	public Collection<PBMissionInfo> toProto() {
		return missionHandler.toProto();
	}
	
	/**
	 * warning: 可能会有强转异常
	 */
	@SuppressWarnings("unchecked")
	public void onInit() {
		int configId = 0;
		IMission initNission = MainMissionType.create(configId);
		MissionTypeData<IMission> missionData = (MissionTypeData<IMission>)getMissionTypeData();
		missionData.addMissionPojo(initNission);
	}
	
	@Override
	public ErrorCode onProcess(IEvent event) {
		ErrorCode errorCode = missionHandler.onProcess(event);
		if (errorCode.isSuccess()) {
			bean.update();
		}
		return errorCode;
	}
	
	@Override
	public ErrorCode onReward(int configId, NatureEnum nenum) {
		ErrorCode errorCode =  missionHandler.onReward(configId, nenum);
		if (errorCode.isSuccess()) {
			bean.update();
		}
		return errorCode;
	}
	
	@Override
	public List<IMission> getUpdateList() {
		return missionHandler.getUpdateList();
	}

}