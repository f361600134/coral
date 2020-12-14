package org.coral.server.game.module.chat.service.impl;

import java.util.Collection;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.chat.assist.ChatEnum;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.service.PlayerService;

/**
 * 世界聊天
 * @author Administrator
 *
 */
public class WorldChat extends AbstractChat{

	public WorldChat(long domainId) {
		super(domainId);
	}

	@Override
	public int getChannel() {
		return ChatEnum.CH_WORLD.getCh();
	}

	@Override
	public Collection<Long> findPlayerIds() {
		PlayerService playerService = SpringContextHolder.getInstance().getBean(PlayerService.class);
		return  playerService.getPlayerIds();
	}

	@Override
	public long getRecId(Player player, long recvId) {
		return 0;
	}

}
