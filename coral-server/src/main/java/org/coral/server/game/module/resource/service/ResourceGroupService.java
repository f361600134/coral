package org.coral.server.game.module.resource.service;

import java.util.List;
import java.util.Map;

import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.module.resource.IResourceGroupService;
import org.coral.server.game.module.resource.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class ResourceGroupService implements IResourceGroupService, InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(ResourceGroupService.class);

	@Autowired private List<IResourceService> resourceServices;
	
	private Map<Integer, IResourceService> serviceMap;

	/**
	 * 奖励
	 * 
	 * @param playerId
	 *            玩家id
	 * @param rewardMap
	 *            value值为正整数
	 * @param nEnum
	 *            资源枚举
	 * @param desc
	 *            其他描述
	 */
	public void reward(long playerId, Map<Integer, Integer> rewardMap, NatureEnum nEnum) {
		for (Integer key : rewardMap.keySet()) {
			int perp = key / 10000;
			IResourceService service = serviceMap.get(perp);
			if (service == null) {
				throw new IllegalArgumentException(String.format("No such type:%s", perp));
			}
			int value = rewardMap.get(key);
			if (value < 0) {
				log.info("Negative item reward, playerId:{}, value:{}, nEnum:{}", playerId, value, nEnum);
				value = Math.abs(value);
			}
			service.reward(playerId, key, value, nEnum);
		}
	}

	/**
	 * 消耗
	 *
	 * @param playerId
	 *            玩家id
	 * @param costMap
	 *            value值为正整数
	 * @param nEnum
	 *            资源枚举
	 * @param desc
	 *            其他描述
	 */
	public void cost(long playerId, Map<Integer, Integer> costMap, NatureEnum nEnum) {
		for (Integer key : costMap.keySet()) {
			int perp = key / 10000;
			IResourceService service = serviceMap.get(perp);
			if (service == null) {
				throw new IllegalArgumentException(String.format("No such type:%s", perp));
			}
			int value = costMap.get(key);
			if (value < 0) {
				log.info("Negative item cost, playerId:{}, value:{}, nEnum:{}", playerId, value, nEnum);
				value = Math.abs(value);
			}
			service.cost(playerId, key, value, nEnum);
		}
	}

	/**
	 * 检查数量是否足够
	 *
	 * @param playerId
	 *            玩家id
	 * @param costMap
	 *            消耗map, value值为正整数. 逐个判断, 全部满足返回true, 否则返回false
	 */
	public boolean check(long playerId, Map<Integer, Integer> costMap) {
		for (Integer key : costMap.keySet()) {
			int perp = key / 10000;
			IResourceService service = serviceMap.get(perp);
			if (service == null) {
				throw new IllegalArgumentException(String.format("No such type:%s", perp));
			}
			int value = costMap.get(key);
			if (value < 0) {
				log.info("Invalid value when check item, playerId:{}, value:{}", playerId, value);
				value = Math.abs(value);
			}
			boolean obj = service.checkEnough(playerId, key, value);
			if (!obj) return false;
		}
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.serviceMap = Maps.newConcurrentMap();
		for (IResourceService service : resourceServices) {
			this.serviceMap.put(service.resType(), service);
		}
	}

}
