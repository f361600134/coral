package org.coral.server.core.server;

public interface IModuleManager<T> {
	
	public T getDomain(long playerId);
	
	public void remove(long playerId);

}
