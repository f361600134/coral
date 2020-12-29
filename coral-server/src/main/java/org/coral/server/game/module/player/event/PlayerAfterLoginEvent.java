package org.coral.server.game.module.player.event;

import org.coral.server.core.event.PlayerEventBase;

public class PlayerAfterLoginEvent extends PlayerEventBase {
	
	public static String ID = PlayerAfterLoginEvent.class.getSimpleName();

    public PlayerAfterLoginEvent(long playerId) {
        super(playerId);
    }
    
    public static PlayerAfterLoginEvent create(long playerId) {
    	return new PlayerAfterLoginEvent(playerId);
    }

}
