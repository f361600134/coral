package org.coral.server.game.module.mission.manager;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.coral.server.game.module.mission.process.IMissionProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * 任务处理器管理类, 在游戏里不同模块的同一种类型任务, 达成类型必须一致(这是大前提)
 * 比如:登录完成的任务实现方式就是通过登录/次日重置完成, 不同模块需保持一致.
 * 
 * 多个任务事件,可能对应同一个任务类型. 
 * 比如:登录任务,登录事件,重置事件都可以完成这个类型的任务
 * 
 * 这个管理器作用就是,对已经实现的任务管理器,在初始化时进行分类, 通过任务类型对应某一个任务处理器.一个任务事件对应多个
 * 任务类型. 这样在收到事件后, 就能获取到当前任务模块内需要处理的任务类型集.也能获取到任务处理器.
 *
 * @author Jeremy
 *
 */
@Component
public class MissionProcessManager implements InitializingBean{
	
	private static Logger log = LoggerFactory.getLogger(MissionProcessManager.class);
	
	@Autowired(required =  false)
	private List<IMissionProcess> processList;

	/**
	 * 初始化任务处理器
	 * key: 任务类型
	 * value:value:处理器
	 */
	private Map<Integer, IMissionProcess> processMap;
	
	/**
	 * key:事件id
	 * value: 任务类型
	 */
	private Multimap<String, Integer> missionTypes;
	
	public MissionProcessManager() {
		this.processMap = Maps.newHashMap();
		this.missionTypes = HashMultimap.create();
	}

	public Map<Integer, IMissionProcess> getProcessMap() {
		return processMap;
	}

	public void setProcessMap(Map<Integer, IMissionProcess> processMap) {
		this.processMap = processMap;
	}
	
	public void addProcessMap(IMissionProcess missionProcess) {
		this.processMap.put(missionProcess.type(), missionProcess);
		for (String eventId : missionProcess.focusEvents()) {
			this.missionTypes.put(eventId, missionProcess.type());
		}
	}
	
	public IMissionProcess getProcess(int type) {
		return processMap.get(type);
	}
	
	/**
	 * @param eventId 事件id
	 * @return Collection<Integer> 任务类型
	 */
	public Collection<Integer> getMissionTypes(String eventId) {
		return missionTypes.get(eventId);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		/*
		 * 初始化任务处理器, 可以搞一个任务类型标, 此处仅加载已经配置的任务类型.
		 *此处默认初始化所有程序已经定义的处理器
		 */
		log.info("初始化任务处理器:{}", processList);
		processList.forEach(e->addProcessMap(e));
		
	}

	
}
