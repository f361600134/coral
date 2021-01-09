package org.coral.server.game.module.chatfinal.impl;

import java.math.BigInteger;
import java.util.Collection;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.chatfinal.domain.ChatEnum;
import org.coral.server.game.module.player.service.PlayerService;

/**
 * @author lijr
 */
public class WorldChat extends AbstractChat {

	public WorldChat() {
		super(ChatEnum.CH_WORLD.getCh());
	}

	/**
	 * 世界聊天, 频道号即可, 保证id唯一即可
	 */
	@Override
	public BigInteger getUniqueId(long senderId, long targetId) {
		BigInteger bigInteger = BigInteger.valueOf(getChannel()).shiftLeft(64)
				.add(BigInteger.valueOf(getChannel()));
		return bigInteger;
	}

	/**
	 * 世界聊天,接收者为所有在线玩家
	 */
	@Override
	public Collection<Long> findReceiverIds(BigInteger uniqueId) {
		PlayerService playerService = SpringContextHolder.getInstance().getBean(PlayerService.class);
		return  playerService.getPlayerIds();
	}

}
