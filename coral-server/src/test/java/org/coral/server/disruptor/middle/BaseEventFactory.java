package org.coral.server.disruptor.middle;

import com.lmax.disruptor.EventFactory;

public class BaseEventFactory implements EventFactory<TaskEvent>{
	@Override
	public TaskEvent newInstance() {
		return new TaskEvent();
	}
	
}
