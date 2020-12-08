package org.coral.server.game.module.player.helper;

import java.util.Collection;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.player.service.PlayerService;

public class PlayerHelper {
	
	public static void sendMessage(long playerId, IProtocol protocol) {
		PlayerService playerService = SpringContextHolder.getInstance().getBean(PlayerService.class);
		playerService.sendMessage(playerId, protocol);
	}
	
	public static void sendMessage(Collection<Long> playerIds, IProtocol protocol) {
		PlayerService playerService = SpringContextHolder.getInstance().getBean(PlayerService.class);
		for (Long playerId : playerIds) {
			playerService.sendMessage(playerId, protocol);
		}
	}

}
