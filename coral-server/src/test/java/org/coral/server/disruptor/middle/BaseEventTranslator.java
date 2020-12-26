package org.coral.server.disruptor.middle;

import org.coral.server.core.event.IEvent;

import com.lmax.disruptor.EventTranslatorOneArg;

public class BaseEventTranslator implements EventTranslatorOneArg<TaskEvent, IEvent>{

	@Override
	public void translateTo(TaskEvent taskEvent, long sequence, IEvent event) {
		taskEvent.setEvent(event);
	}

}
