package org.coral.server.game.module.wealth.handler;

import org.coral.server.game.helper.ResourceType;
import org.coral.server.game.module.player.service.PlayerService;
import org.coral.server.game.module.wealth.annotation.WealthIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class WealthHandler {
	
	@Autowired private PlayerService playerService;
	
	/**
	 * 属性奖励
	 * @param player
	 * @param reward
	 */
	@WealthIndex(ResourceType.Property)
	public void propertiesReward(long playerId, int configId, int added) {
		playerService.addProerties(playerId, configId, added);
	}
	
	/**
	 * 属性消耗
	 * @param player
	 * @param reward
	 */
	@WealthIndex(ResourceType.Property)
	public void propertiesCost(long playerId, int configId, int added) {
		playerService.addProerties(playerId, configId, -added);
	}
	
	/**
	 * 属性检测
	 * @param player
	 * @param reward
	 */
	@WealthIndex(ResourceType.Property)
	public boolean propertiesCheck(long playerId, int configId, int added) {
		return playerService.checkProerties(playerId, configId, added);
	}
	

}
