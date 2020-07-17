package org.coral.server.game.module.player;

import org.coral.net.core.base.GameSession;
import org.coral.server.game.module.player.domain.Player;

public class PlayerContext {

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
	}
	
	public PlayerContext(Player player, GameSession session) {
		this.player = player;
		this.session = session;
	}
	
	
	
}
