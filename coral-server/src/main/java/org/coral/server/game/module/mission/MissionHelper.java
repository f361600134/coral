package org.coral.server.game.module.mission;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象任务域, 实现任务业务
 * 
 * @author Jeremy
 *
 */
public class MissionHelper {

	/**
	 * 初始化任务处理器
	 */
	private Map<Integer, IMissionProcess> processMap;

	public Map<Integer, IMissionProcess> getProcessMap() {
		return processMap;
	}

	public void setProcessMap(Map<Integer, IMissionProcess> processMap) {
		this.processMap = processMap;
	}
	
	public IMissionProcess getProcess(int type) {
		return processMap.get(type);
	}
	

}
