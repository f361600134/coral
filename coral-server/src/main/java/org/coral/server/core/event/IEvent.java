package org.coral.server.core.event;

import org.apache.commons.lang3.StringUtils;

public interface IEvent {
	
	public String name();
	
	default public boolean isSameEvent(String eventName) {
		return StringUtils.equals(name(), eventName);
	}
	
}
