package org.coral.server.game.module.chat.service.impl;

import java.util.Collection;

import org.coral.server.game.module.chat.assist.ChatEnum;
import org.coral.server.game.module.player.domain.Player;

import com.google.common.collect.Lists;

/**
 * 私聊
 * @author Administrator
 */
public class PrivateChat extends AbstractChat{

	public PrivateChat(long domainId) {
		super(domainId);
	}

	@Override
	public int getChannel() {
		return ChatEnum.CH_PRIVATE.getCh();
	}

	/**
	 * 私聊玩家
	 */
	@Override
	public Collection<Long> findPlayerIds() {
		return Lists.newArrayList(getDomainId());
	}

	/**
	 * @param recvId 接受聊天信息的玩家id
	 */
	@Override
	public long getRecId(Player player, long recvId) {
		return recvId;
	}
	
}
