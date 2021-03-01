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
import org.coral.server.game.module.mission.type.AbstractMission;
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
		//this.missionHandler = new DefaultMissionHandler(getPlayerId(), bean.getMissionData());
		this.missionHandler = Builder.newBuilder()
				.playerId(getPlayerId())
				.missionData(bean.getMissionData())
				.afterRewardedListener((mission, missionData)->{
					//	主线任务完成后,需要接取下一条任务
					final int configId = mission.getConfigId();
					//	领奖后,主线任务移除掉旧务,加入任务链的下一个任务  
					//MissionTypeData<MainMissionType> missionData = mainMission.getMissionData();
					//missionData.onFinished(configId);
					//ConfigMission config = ConfigMissionMgr.getConfig(configId);
					//int nextConfigId = config.getNextMissionId();
					int nextConfigId = 0;
					AbstractMission nextNission = MainMissionType.create(nextConfigId);
					//FIXME 这里很懵逼?为什么呢
					//missionData.addMissionPojo(nextNission);
				})
				.build();
//		missionHandler.afterRewarded(new IMissionListener<IMission>() {
//			@Override
//			public void call(IMission mission) {
//				
//			}
//		});
	}
	
	@Override
	public MissionTypeData<? extends AbstractMission> getMissionTypeData() {
		return bean.getMissionData();
	}
	
	@Override
	public Collection<PBMissionInfo> toProto() {
		return missionHandler.toProto();
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