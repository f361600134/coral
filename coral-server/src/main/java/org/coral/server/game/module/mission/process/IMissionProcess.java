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
	public boolean process(IMission mission, IEvent event);
	
	/**
	 * 对应的事件集合
	 * 一个任务有多种触发的事件完成
	 * 比如,玩家登陆任务,对应登陆事件以及玩家每日重置事件.
	 * @return
	 */
	public String[] focusEvents();
	
	/**
	 * 	获取到事件触发条件, 默认条件必须要与配置表内的值一致.
	 * 	大多数条件为0,所以默认返回0,
	 * 	特殊需要双条件的任务,子类实现具体条件值返回.然后再比较值
	 * 	对于极为特殊的任务,需要多条件的, 此类不变, 对配置内的条件值改为List,然后判断是否包含此条件值
	 * 
	 * 	譬如:
	 *  1.完成金币副本(1001)扫荡10次, 发起方会发送一个副本完成事件,且携带副本id, 此方法获取副本id
	 * 	2.完成任务(1001)任务1次, 完成任务后发起方会发送一个完成任务事件, 带有任务id,此方法获取任务id
	 *	3.每日登陆1次,登陆成功后, 此方法根据当前时间, 计算是登陆总天数, 返回值即为当前登陆天数
	 * @return
	 */
	default public int getCondition(IMission mission, IEvent event) {
		return 0;
	}

	/**
	 * 	获取到值事件携带的达成值数据,  发起方由发起方推送, 接收方默认次数视为1
	 *	譬如:
	 *  1.完成金币副本(1001扫荡10次, 发起方会发送副本完成事件, 且携带一个扫次次数,此方法获取具体的次数,去累加进度 
	 * 	2.完成任务(1001任务1次, 完成任务后发起方会发送一个完成任务事件, 或许不带有次数,此方法则默认视为完成1次
	 *  3.每日登陆1次,登陆成功后, 此方法根据当前时间, 计算是登陆总天数, 返回值即为当前登陆天数,完成值即为1次
	 * @return
	 */
	default public int getCompleteValue(IMission mission, IEvent event) {
		return 1;
	}
}
