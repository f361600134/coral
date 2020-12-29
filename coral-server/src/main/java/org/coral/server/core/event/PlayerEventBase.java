package org.coral.server.core.event;

/**
 * 玩家事件基础类
 */
public abstract class PlayerEventBase extends BaseEvent{
	
    private final long playerId;

    public PlayerEventBase(long playerId) {
    	this.playerId = playerId;
    }
    
    public PlayerEventBase(long playerId, long time) {
    	super(time);
    	this.playerId = playerId;
    }

	public long getPlayerId() {
		return playerId;
	}
	
	@Override
	public String getEventId() {
		return this.getClass().getSimpleName();
	}
	
}
