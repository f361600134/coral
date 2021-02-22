package org.coral.server.game.module.mission.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coral.server.core.event.IEvent;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.mission.manager.MissionProcessManager;
import org.coral.server.game.module.mission.process.IMissionProcess;
import org.coral.server.game.module.mission.type.IMission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * 任务处理器, 每个任务模块维护自己的任务. 以及任务类型对应的id集合
 * @author Jeremy
 */
public abstract class AbstractMissionHandler implements IMissionHandler{
	
	private static final Logger log = LoggerFactory.getLogger(MissionProcessManager.class);

	/**
	 * key:任务类型
	 * value: 任务id集合
	 */
	private transient Multimap<Integer, Integer> missionConfigs;
	
	private transient List<IMission> updateList;

	public AbstractMissionHandler() {
		this.missionConfigs = ArrayListMultimap.create();
		this.updateList = Lists.newArrayList();
		initMissionConfigs();
	}

	private void initMissionConfigs() {
		getMissions().forEach(e -> {
			this.missionConfigs.put(e.getCompleteType(), e.getConfigId());
		});
	}

	public Collection<PBBag.PBMissionInfo> toProto() {
		List<PBBag.PBMissionInfo> colls = new ArrayList<PBBag.PBMissionInfo>();
		for (IMission mission : getMissions()) {
			colls.add(mission.toProto());
		}
		return colls;
	}
	
	@Override
	public List<IMission> getUpdateList() {
		return updateList;
	}
	
	/**
	 * 处理任务
	 * 1.任务处理管理器通过任务类型获取到处理类,
	 * 2.通过任务类型获取到任务列表
	 * 3.当前任务集合,通过任务列表获取到任务对象, 被任务处理类去处理任务
	 * @return
	 */
	public ErrorCode onProcess(IEvent event) {
		//TODO 这里不想每次都通过spring去获取manager, 看看怎么优化
		MissionProcessManager manager = SpringContextHolder.getInstance().getBean(MissionProcessManager.class);
		//获取到任务类型
		Collection<Integer> missionTypes = manager.getMissionTypes(event.getEventId());
		for (Integer missionType : missionTypes) {
			IMissionProcess missionProcess = manager.getProcess(missionType);
			Collection<Integer> configIds = missionConfigs.get(missionType);
			for (Integer configId : configIds) {
				IMission mission = getMission(configId);
				boolean bool = missionProcess.process(mission, event);
				if (!bool) break;
				this.updateList.add(mission);
			}
		}
		afterProcess();
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 领取奖励, 具体奖励进背包由子类完成
	 */
	@Override
	public ErrorCode onReward(int configId) {
		IMission mission = getMission(configId);
		if (mission.isNone()) {
			return ErrorCode.MISSION_NOT_COMPLATE; //任务未完成
		}
		if (mission.isRewarded()) {
			return ErrorCode.MISSION_REWARDES; //任务已领奖
		}
		this.updateList.add(mission);
		doReward(mission);
		afterRewarded(mission);
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 	处理任务后的逻辑,
	 * @param configId 任务id
	 * @return
	 */
	protected abstract void afterProcess();
	
	/**
	 * 任务处理器, 仅处理任务部分, 发奖部分由子类实现. 仅仅处理发奖
	 * @param configId
	 */
	protected abstract void doReward(IMission mission);
	
	/**
	 * 发奖后后续操作, 发奖后的后续操作 
	 * @param configId
	 */
	protected abstract void afterRewarded(IMission mission);
	
}
