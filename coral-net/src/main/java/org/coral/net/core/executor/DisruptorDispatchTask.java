package org.coral.net.core.executor;

import org.coral.net.core.ControllerProcessor;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisruptorDispatchTask implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(DisruptorDispatchTask.class);
	
	private ControllerProcessor processor;
	private GameSession session;
	private Packet packet;

	public DisruptorDispatchTask(ControllerProcessor processor, GameSession session, Packet packet) {
		this.processor = processor;
		this.session = session;
		this.packet = packet;
	}

	@Override
	public void run() {
		try {
			processor.invoke(session, packet);
			log.info("====> DisruptorDispatchTask run, threadName:{}", Thread.currentThread().getName());
		} catch (Exception e) {
			log.error("DisruptorDispatchTask error", e);
		}
	}

}