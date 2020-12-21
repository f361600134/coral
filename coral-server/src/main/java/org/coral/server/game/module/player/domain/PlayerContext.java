package org.coral.server.game.module.player.domain;

import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IProtocol;
import org.coral.net.core.base.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 玩家上下文
 * 
 * @auth Jeremy
 * @date 2020年9月7日下午10:48:55
 */
public class PlayerContext {

	private static final Logger log = LoggerFactory.getLogger(PlayerContext.class);

	/** 玩家对象 */
	private Player player;
	/** 玩家会话 */
	private GameSession session;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameSession getSession() {
		return session;
	}

	public void setSession(GameSession session) {
		this.session = session;
	}

	public PlayerContext() {
	}

	public PlayerContext(Player player) {
		this.player = player;
	}

	public PlayerContext(Player player, GameSession session) {
		this.player = player;
		this.session = session;
	}

	public void send(IProtocol protocol) {
		if (isOnline()) {
			session.send(Packet.encode(protocol));
			log.info("send message:[{}], playerId:{}, size={}B, data:{}", protocol.protocol(), player.getPlayerId(),
					protocol, protocol.toBytes().length);
		}
	}

	public boolean isOnline() {
		return session != null && session.isConnect();
	}

	public boolean isLoaded() {
		return player != null;
	}

	public boolean isLogined() {
		return session != null;
	}

	public long getPlayerId() {
		return player == null ? 0 : player.getPlayerId();
	}

	/**
	 * 被挤掉
	 */
	public void forceLogout() {
		if (this.session != null && this.session.isConnect()) {
			// TODO 推送客户端消息, 下线
			log.info("推送客户端消息, 下线 forceLogout");
			// AckUtility.ResponseDisconnect(56, this.channel);
		}
	}

	public void setData() {

	}

}
