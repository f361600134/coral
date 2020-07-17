package org.coral.server.game.module.player;

import java.util.Map;

import org.coral.orm.core.Processor;
import org.coral.server.game.module.player.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class PlayerManager {
	
	@Autowired private Processor process;
	
	private Map<Long, PlayerContext> playerContextMap = Maps.newConcurrentMap();
	
	/**
	 * 获取一个玩家
	 * @date 2020年7月17日
	 * @param playerId
	 * @return
	 */
	public PlayerContext getPlayerContext(Long playerId) {
		PlayerContext playerCtx = playerContextMap.get(playerId);
		if (playerCtx == null) {
			playerCtx = getPlayerById(playerId);
			if (playerCtx != null) {
				playerContextMap.put(playerId, playerCtx);
			}
		}
		return playerCtx;
	}
	
	/**
	 * 获取一个玩家
	 * @date 2020年7月17日
	 * @param playerId
	 * @return
	 */
	private PlayerContext getPlayerById(long playerId) {
		Player player = (Player)process.select(Player.class, new Object[] {playerId});
		PlayerContext playerCtx = new PlayerContext(player);
		return playerCtx;
	}

}
