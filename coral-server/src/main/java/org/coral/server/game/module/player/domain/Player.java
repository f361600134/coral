package org.coral.server.game.module.player.domain;

import java.util.Map;

import org.coral.server.game.module.base.PlayerPo;
import org.coral.server.game.module.chat.domain.ChatRule;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

@Repository
public class Player extends PlayerPo {
	
	/**
	 * 属性map
	 */
	private Map<Integer, Integer> propertieMap;
	
	/**
	 * key:频道号 value: 聊天约束
	 */
	private Map<Integer, ChatRule> chatRuleMap;
	
	public Map<Integer, Integer> getPropertieMap() {
		return propertieMap;
	}

	public void setPropertieMap(Map<Integer, Integer> propertieMap) {
		this.propertieMap = propertieMap;
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
	
	public ChatRule getChatRule(int channel) {
		if (chatRuleMap == null) {
			chatRuleMap = Maps.newHashMap();
		}
		ChatRule chatRule = chatRuleMap.get(channel);
		if (chatRule == null) {
			chatRule = ChatRule.create(channel);
			chatRuleMap.put(channel, chatRule);
		}
		return chatRule;
	}

}
