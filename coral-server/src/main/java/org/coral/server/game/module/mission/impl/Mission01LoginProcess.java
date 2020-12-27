package org.coral.server.game.module.mission.impl;

import org.coral.server.game.module.mission.MissionEnum;

/**
 * 这里增加实践，表示某一个事件触发。
 * @auth Jeremy
 * @date 2020年12月28日上午12:17:25
 */
public class Mission01LoginProcess {
	
	/**
	 * 处理类型
	 */
	public int type() {
		return MissionEnum.TYPE_LOGIN.getType();
	}

	public boolean doProcess(long playerId, int value) {
		// TODO Auto-generated method stub
		return false;
		
	}

//	@Override
//	public boolean doProcess(long playerId, int value, List<Integer> configs) {
//		try {
//			//计算活动第几天
//			boolean chg = false;
//			//获取登陆任务
////			List<Integer> configs = missionConfigs.get();
//			if(configs != null && !configs.isEmpty())
//			{//有任务可以判断完成或记录进度
//				for(Integer configId : configs){
//					EntityMission mission = missions.get(configId);
//					if(mission == null || mission.getCompleteValue() != value)
//						continue;
//					chg = progressMission(1, mission) || chg;
//				}
//			}
//			return chg;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("onLogin error, e:", e);
//			return false;
//		}
//	}

}
