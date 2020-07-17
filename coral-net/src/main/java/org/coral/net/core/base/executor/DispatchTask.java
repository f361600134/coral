package org.coral.net.core.base.executor;

import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.Packet;

public class DispatchTask {
	
	private GameSession session;
	private Packet packet;

	public DispatchTask(GameSession session, Packet packet) {
		this.session = session;
		this.packet = packet;
	}

	public GameSession getSession() {
		return session;
	}

	public void setSession(GameSession session) {
		this.session = session;
	}

	public Packet getPacket() {
		return packet;
	}

	public void setPacket(Packet packet) {
		this.packet = packet;
	}
	
//	void invoke(HandlerProcessor processor) throws Exception {
//		processor.invoke(session, packet.cmd(), packet.data());
//	}

}