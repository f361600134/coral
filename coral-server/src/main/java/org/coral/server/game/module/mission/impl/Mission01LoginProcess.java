package org.coral.server.game.module.mission.impl;

import org.coral.server.game.module.mission.IMission;
import org.coral.server.game.module.mission.MissionEnum;

public class Mission01LoginProcess {
//implements IMissionProcess{
	
	/**
	 * 处理类型
	 */
	public int type() {
		return MissionEnum.TYPE_LOGIN.getType();
	}

	public boolean doProcess(int value, IMission mission) {
		try {
			//计算活动第几天
			boolean chg = false;
			//获取登陆任务
//			List<Integer> configs = missionConfigs.get();
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission == null || mission.getCompleteValue() != value)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
