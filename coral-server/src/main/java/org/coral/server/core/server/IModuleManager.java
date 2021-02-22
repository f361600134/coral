package org.coral.server.core.server;

/**
 * 管理类接口, 由事件触发
 * @author Administrator
 */
public interface IModuleManager<T> {
	
	public T getDomain(long playerId);
	
	/**
	 * 根据玩家id移除掉domain
	 * @param playerId
	 */
	public void remove(long playerId);

}
