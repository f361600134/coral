package org.coral.server.core.server;

/**
 * 管理类接口, 由事件触发
 * @author Administrator
 */
public interface IManager {
	
	/**
	 * 当系统启动时
	 */
	public void onStartup();
	
	/**
	 * 当系统关闭
	 */
	public void onShutdown();
	

}
