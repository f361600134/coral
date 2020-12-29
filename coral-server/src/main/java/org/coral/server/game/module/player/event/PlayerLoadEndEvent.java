package org.coral.server.game.module.player.event;

import org.coral.server.core.event.PlayerEventBase;

/**
 * 从数据库加载了玩家的事件
 */
public class PlayerLoadEndEvent extends PlayerEventBase {
	
	public static String ID = PlayerLoadEndEvent.class.getSimpleName();

    public PlayerLoadEndEvent(long playerId) {
    	super(playerId);
    }
    
    public static PlayerLoadEndEvent create(long playerId) {
    	return new PlayerLoadEndEvent(playerId);
    }
}
