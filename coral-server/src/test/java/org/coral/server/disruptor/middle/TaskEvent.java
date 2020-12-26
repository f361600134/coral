package org.coral.server.disruptor.middle;

import org.coral.server.core.event.IEvent;

public class TaskEvent {
	
	private IEvent event;

	public IEvent getEvent() {
		return event;
	}

	public void setEvent(IEvent event) {
		this.event = event;
	}
	
	public boolean isSameEvent(String eventName){
		return event.isSameEvent(eventName);
	}

}
