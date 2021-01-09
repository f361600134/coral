package org.coral.server.game.module.chatfinal.impl;

import java.math.BigInteger;
import java.util.Collection;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.chatfinal.domain.ChatEnum;
import org.coral.server.game.module.player.service.PlayerService;


/**
 * @author Jeremy
 */
public class SystemChat extends AbstractChat {

	public SystemChat() {
		super(ChatEnum.CH_SYSTEM.getCh());
	}
	
	/**
	 * 系统聊天, 频道号即可, 保证id唯一即可
	 */
	@Override
	public BigInteger getUniqueId(long senderId, long targetId) {
		BigInteger bigInteger = BigInteger.valueOf(getChannel()).shiftLeft(64)
				.add(BigInteger.valueOf(getChannel()));
		return bigInteger;
	}

	/**
	 * 系统聊天,接收者为所有在线玩家
	 */
	@Override
	public Collection<Long> findReceiverIds(BigInteger uniqueId) {
		PlayerService playerService = SpringContextHolder.getInstance().getBean(PlayerService.class);
		return  playerService.getPlayerIds();
	}
	
	/**
	 * 系统频道, 不能由玩家直接进行聊天.
	 * 一般由玩家游戏内某个模块触发(如获得极品装备), 通过系统频道进行内容推送,
	 *  或者由GM后台推送.(如发送公告)
	 *  所以默认成功成功.
	 */
	@Override
	public ErrorCode checkChat(long senderId, BigInteger uniqueId) {
		return ErrorCode.SUCCESS;
	}

}
