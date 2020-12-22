package org.coral.server.game.module.mission;

public interface IMissionProcess {
	
	/**
	 * 处理类型
	 * @return
	 */
	public int type();
	
	/**
	 * 处理器
	 * @param playerId
	 * @param loginDays 当前登陆天数
	 * @return
	 */
	public boolean doProcess(long playerId, int value);

}
