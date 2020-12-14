package org.coral.server.game.module.chat.service.impl;

import java.util.Collection;

import org.coral.server.game.module.chat.assist.ChatEnum;

public class FamilyChat extends AbstractChat{

	public FamilyChat(long domainId) {
		super(domainId);
	}

	@Override
	public int getChannel() {
		return ChatEnum.CH_FAMILY.getCh();
	}

	@Override
	public Collection<Long> findPlayerIds() {
		//1.通过家族id, 获取到家族
		//2.返回家族内的在线玩家列表
		return null;
	}

}
