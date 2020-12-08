package org.coral.server.game.module.wealth.service;

import java.lang.reflect.Method;
import java.util.Map;

import org.coral.server.core.reflect.MethodInvoker;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.module.wealth.annotation.WealthIndex;
import org.coral.server.game.module.wealth.handler.WealthHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class WealthService implements InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(WealthService.class);

	@Autowired
	private WealthHandler playerWealthHandler;

	/**
	 * 奖励方法
	 */
	private Map<Byte, MethodInvoker> rewardMethods = Maps.newHashMap();

	/**
	 * 消耗方法
	 */
	private Map<Byte, MethodInvoker> costMethods = Maps.newHashMap();

	/**
	 * 检查方法
	 */
	private Map<Byte, MethodInvoker> checkMethods = Maps.newHashMap();

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
			byte perp = (byte) (key / 10000);
			MethodInvoker rewardInvoker = rewardMethods.get(perp);
			if (rewardInvoker == null) {
				throw new IllegalArgumentException(String.format("No such type:%s", perp));
			}
			int value = rewardMap.get(key);
			if (value < 0) {
				log.info("Negative item reward, playerId:{}, value:{}, nEnum:{}", playerId, value, nEnum);
				value = Math.abs(value);
			}
			rewardInvoker.invoke(playerId, key, value);
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
			byte perp = (byte) (key / 10000);
			MethodInvoker costInvoker = costMethods.get(perp);
			if (costInvoker == null) {
				throw new IllegalArgumentException(String.format("No such type:%s", perp));
			}
			int value = costMap.get(key);
			if (value < 0) {
				log.info("Negative item cost, playerId:{}, value:{}, nEnum:{}", playerId, value, nEnum);
				value = Math.abs(value);
			}
			costInvoker.invoke(playerId, key, value);
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
			byte perp = (byte) (key / 10000);
			MethodInvoker invoker = checkMethods.get(perp);
			if (invoker == null) {
				throw new IllegalArgumentException(String.format("No such type:%s", perp));
			}
			int value = costMap.get(key);
			if (value < 0) {
				log.info("Invalid value when check item, playerId:{}, value:{}", playerId, value);
				value = Math.abs(value);
			}
			Boolean obj = (Boolean) invoker.invoke(playerId, key, value);
			if (!obj)
				return false;
		}
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Method[] methods = playerWealthHandler.getClass().getDeclaredMethods();
		for (Method method : methods) {
			WealthIndex annotation = method.getAnnotation(WealthIndex.class);
			if (annotation == null) {
				continue;
			}
			MethodInvoker invoker = MethodInvoker.create(playerWealthHandler, method);
			if (annotation.type() == WealthIndex.CHECK) {
				this.checkMethods.put((byte) annotation.value().getType(), invoker);
			} else if (annotation.type() == WealthIndex.REWARD) {
				this.rewardMethods.put((byte) annotation.value().getType(), invoker);
			} else if (annotation.type() == WealthIndex.COST) {
				this.costMethods.put((byte) annotation.value().getType(), invoker);
			}
		}
	}

}
