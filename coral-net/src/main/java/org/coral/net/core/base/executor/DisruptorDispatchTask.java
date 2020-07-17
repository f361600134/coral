package org.coral.net.core.base.executor;

import org.coral.net.core.HandlerProcessor;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisruptorDispatchTask implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(DisruptorDispatchTask.class);
	
	private HandlerProcessor processor;
	private GameSession session;
	private Packet packet;

	public DisruptorDispatchTask(HandlerProcessor processor, GameSession session, Packet packet) {
		this.processor = processor;
		this.session = session;
		this.packet = packet;
	}

	@Override
	public void run() {
		try {
			processor.invoke(session, packet);
		} catch (Exception e) {
			log.error("DisruptorDispatchTask error", e);
		}
	}

}