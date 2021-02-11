package org.coral.server.game.module.mission.process;

import org.coral.server.core.event.IEvent;
import org.coral.server.game.module.mission.type.IMission;

public interface IMissionProcess{
	
	/**
	 * 处理类型
	 * @return
	 */
	public int type();
	
	/**
	 * 	处理逻辑
	 * @param playerId
	 * @param 校验值,
	 * @return
	 */
	public boolean process(int value, IMission mission, IEvent event);
	
	/**
	 * 对应的事件集合
	 * 一个任务有多种触发的事件完成
	 * 比如,玩家登陆任务,对应登陆事件以及玩家每日重置事件.
	 * @return
	 */
	public String[] focusEvents();
	
	/**
	 * 获取到事件触发条件, 默认条件必须要与配置表内的值一致
	 * 大多数条件为0,所以默认返回0,
	 * 特殊需要双条件的任务,子类实现具体条件值返回.然后再比较值
	 * 对于极为特殊的任务,需要多条件的, 此类不变, 对配置内的条件值改为List,然后判断是否包含此条件值
	 * @return
	 */
	default public int getCondition(IEvent event) {
		return 0;
	}

}
