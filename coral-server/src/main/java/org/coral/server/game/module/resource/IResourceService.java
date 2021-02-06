package org.coral.server.game.module.resource;

import org.coral.server.game.helper.log.NatureEnum;

/**
 *  单个资源[消耗]相关接口， 独立判断单类型资源是否满足条件的接口。
 * @author Jeremy
 * @date 2020年12月20日下午1:22:43
 */
public interface IResourceService {
	
	/**
	 * 属性类型，道具。用于获取handler
	 * @return  
	 * @return int  
	 * @date 2020年12月20日下午9:48:54
	 */
	public int resType();
	
	/**
	 * 检测是否可以添加
	 * @param playerId 玩家id
	 * @param configId 配置id
	 * @param value 值为正整数
	 */
	public boolean checkAdd(long playerId, Integer configId, Integer value);
	
	/**
	 * 检查数量是否足够
	 * @param playerId 玩家id
	 * @param configId 配置id
	 * @param value 值为正整数
	 */
	public boolean checkEnough(long playerId, Integer configId, Integer value);
	
	
	/**
	 * 奖励,根据配置id,奖励指定数量物品
	 * @param playerId 玩家id
	 * @param configId 配置id
	 * @param value 值为正整数
	 * @param nEnum 资源枚举
	 */
	public void reward(long playerId, Integer configId, Integer value, NatureEnum nEnum);
	
	/**
	 * 消耗,根据配置id,消耗指定数量
	 * @param playerId 玩家id
	 * @param configId 配置id
	 * @param value 值为正整数
	 * @param nEnum 资源枚举
	 * @param desc  其他描述
	 */
	public void cost(long playerId, Integer configId, Integer value, NatureEnum nEnum);
	
	/**
	 * 根据唯一id扣除, 通常来说, 唯一的物品, 数量只能是1.所以 这里没有数量需求.
	 * @param playerId
	 * @param uniqueId
	 * @param value
	 * @param nEnum  
	 * @return void  
	 * @date 2021年1月16日下午5:43:08
	 */
	public void cost(long playerId, Long uniqueId, NatureEnum nEnum);
}
