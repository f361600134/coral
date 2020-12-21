package org.coral.server.game.module.resource;

import org.coral.server.game.helper.log.NatureEnum;

/**
 *  单个资源相关接口， 独立判断单类型资源是否满足条件的接口。
 * @auth Jeremy
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
	 * 奖励
	 * @param playerId 玩家id
	 * @param configId 配置id
	 * @param value 值为正整数
	 * @param nEnum 资源枚举
	 */
	public void reward(long playerId, Integer configId, Integer value, NatureEnum nEnum);
	
	/**
	 * 消耗
	 * @param playerId 玩家id
	 * @param configId 配置id
	 * @param value 值为正整数
	 * @param nEnum 资源枚举
	 * @param desc  其他描述
	 */
	public void cost(long playerId, Integer configId, Integer value, NatureEnum nEnum);
	
}
