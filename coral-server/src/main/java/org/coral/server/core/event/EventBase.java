package org.coral.server.core.event;

/**
 * 事件基础类
 */
public abstract class EventBase implements IEvent{
	
    private final long playerId;

    public EventBase(long playerId) {
    	this.playerId = playerId;
    }

	public long getPlayerId() {
		return playerId;
	}
	
	@Override
	public String name() {
		return this.getClass().getSimpleName();
	}
	
}
