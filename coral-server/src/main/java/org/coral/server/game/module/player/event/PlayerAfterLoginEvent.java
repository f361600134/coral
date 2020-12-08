package org.coral.server.game.module.player.event;

import org.coral.server.core.event.EventBase;

public class PlayerAfterLoginEvent extends EventBase {

    public PlayerAfterLoginEvent(long playerId) {
        super(playerId);
    }
    
    public static PlayerAfterLoginEvent create(long playerId) {
    	return new PlayerAfterLoginEvent(playerId);
    }

}
