package org.coral.server.game.module.mission;

import java.util.List;
import java.util.Map;
	
	/**
	 * 处理类型
	 * @return
	 */
	public int type();
	
	/**
	 * 处理器
	 * @param playerId
	 * @param loginDays 当前登陆天数
	 * @return
	 */
	public boolean doProcess(int value, List<Integer> configs, Map<Integer, IMission> missions);

}
