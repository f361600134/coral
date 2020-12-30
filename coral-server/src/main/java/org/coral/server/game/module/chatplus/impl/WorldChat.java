package org.coral.server.game.module.chatplus.impl;

import java.util.Collection;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.chat.assist.ChatEnum;
import org.coral.server.game.module.player.service.PlayerService;

/**
 * @author Jeremy
 *
 */
public class WorldChat extends AbstractChat{

	public WorldChat(long uniqueId) {
		super(ChatEnum.CH_WORLD.getCh(), uniqueId);
	}

	@Override
	public Collection<Long> findPlayerIds() {
		PlayerService playerService = SpringContextHolder.getInstance().getBean(PlayerService.class);
		return  playerService.getPlayerIds();
	}

}
