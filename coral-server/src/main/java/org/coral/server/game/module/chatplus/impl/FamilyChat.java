package org.coral.server.game.module.chatplus.impl;

import java.util.Collection;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.chatplus.assist.ChatEnum;
import org.coral.server.game.module.chatplus.domain.ChatDetails;
import org.coral.server.game.module.chatplus.proto.PBChatInfoBuilder;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.service.IPlayerService;

public class FamilyChat extends AbstractChat {

	/**
	 * 家族聊天uniqueId为家族id
	 * 
	 * @param uniqueId
	 */
	public FamilyChat(long uniqueId) {
		super(ChatEnum.CH_FAMILY.getCh(), uniqueId);
	}

	@Override
	public Collection<Long> findPlayerIds() {
		// TODO 通过uniqueid查询到家族, 通过家族查询到在线玩家列表
		return null;
	}

}
