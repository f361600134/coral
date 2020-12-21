package org.coral.server.game.module.player.domain;

import java.util.Map;

import org.coral.server.game.module.base.PlayerPo;
import org.springframework.stereotype.Repository;

@Repository
public class Player extends PlayerPo {
	
	/**
	 * 属性map
	 */
	private Map<Integer, Integer> propertieMap;
	
//	public Map<Integer, Integer> getPropertieMap() {
//		return propertieMap;
//	}
//
//	public void setPropertieMap(Map<Integer, Integer> propertieMap) {
//		this.propertieMap = propertieMap;
//	}
	
	/**
	 * 检查金币是否足够
	 * @param value
	 * @return void
	 * @date 2020年8月20日下午5:35:35
	 */
	public boolean checkProperties(int configId, int value) {
		return getProperties(configId) >= value;
	}
	
	/**
	 * 获取属性值
	 * @param configId
	 * @return int  
	 * @date 2020年12月20日下午5:26:37
	 */
	public int getProperties(int configId) {
		return propertieMap.getOrDefault(configId, 0);
	}
	
	/**
	 * 属性增加值，数值只能为正
	 * @param configId
	 * @param added
	 * @return int  
	 * @date 2020年12月20日下午5:26:57
	 */
	public void addProperties(int configId, int added) {
		int val = getProperties(configId);
		if (added < 0) return;
		val += added;
		propertieMap.put(configId, val);
		return;
	}
	
	/**
	 * 属性减少值，数值只能为正，有函数自己实现减少操作
	 * @param configId
	 * @param added
	 * @return int  
	 * @date 2020年12月20日下午5:26:57
	 */
	public void reduceProperties(int configId, int added) {
		int val = getProperties(configId);
		if (added < 0) return;
		val -= added;
		propertieMap.put(configId, val);
		return;
	}
	
	
	/**
	 * 检查金币是否足够
	 * @param value
	 * @return void
	 * @date 2020年8月20日下午5:35:35
	 */
	public boolean checkIngot(int value) {
		return getIngot() >= value;
	}

	/**
	 * 增加金币, 或增或减
	 * @param value
	 * @return void
	 * @date 2020年8月20日下午5:35:35
	 */
	public boolean addIngot(int value) {
		if (value < 0 && ingot < Math.abs(value))
			return false;
		ingot += value;
		if (ingot > Integer.MAX_VALUE)
			ingot = Integer.MAX_VALUE;
		return true;
	}

	/**
	 * 修改铜钱数量
	 * @param value 增加金币
	 */
	public boolean addCopper(int value) {
		if (value < 0 && copper < Math.abs(value))
			return false;
		copper += value;
		if (copper > Integer.MAX_VALUE)
			copper = Integer.MAX_VALUE;
		return true;
	}
	
//	public ChatRule getChatRule(int channel) {
//		if (chatRuleMap == null) {
//			chatRuleMap = Maps.newHashMap();
//		}
//		ChatRule chatRule = chatRuleMap.get(channel);
//		if (chatRule == null) {
//			chatRule = ChatRule.create(channel);
//			chatRuleMap.put(channel, chatRule);
//		}
//		return chatRule;
//	}

}
