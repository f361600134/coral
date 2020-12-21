package org.coral.server.game.module.player.service;

import java.util.Collection;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.module.player.domain.PlayerContext;

public interface IPlayerService {
	
	/**
	 * 获取所有在线玩家
	 * @return
	 */
	public Collection<Long> getPlayerIds();
	
	/**
	 * 获取获取玩家上下文
	 * @return
	 */
	public PlayerContext getPlayerContext(Long playerId);
	
	/**
	 * 获取获取玩家上下文
	 * @return
	 */
	public Collection<PlayerContext> getPlayerContexts();
	
	/**
	 * 发送消息
	 * @param playerId
	 * @param protocol
	 */
	public void sendMessage(long playerId, IProtocol protocol);
	
	/**
	 * 群发送消息
	 * @param playerId
	 * @param protocol
	 */
	public void sendMessage(Collection<Long> playerIds, IProtocol protocol);

}
