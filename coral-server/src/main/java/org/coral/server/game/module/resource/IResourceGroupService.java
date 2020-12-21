package org.coral.server.game.module.resource;

import java.util.Map;

import org.coral.server.game.helper.log.NatureEnum;

/**
 * 资源组接口
 * @author Jeremy
 */
public interface IResourceGroupService {
	
	/**
	 * 检查数量是否足够
	 * @param playerId 玩家id
	 * @param costMap  消耗map, value值为正整数. 逐个判断, 全部满足返回true, 否则返回false
	 */
	public boolean check(long playerId, Map<Integer, Integer> costMap);
	
	/**
	 * 奖励
	 * @param playerId 玩家id
	 * @param rewardMap value值为正整数
	 * @param nEnum 资源枚举
	 */
	public void reward(long playerId, Map<Integer, Integer> rewardMap, NatureEnum nEnum);
	
	/**
	 * 消耗
	 *
	 * @param playerId 玩家id
	 * @param costMap value值为正整数
	 * @param nEnum   资源枚举
	 * @param desc 其他描述
	 */
	public void cost(long playerId, Map<Integer, Integer> costMap, NatureEnum nEnum);

}
