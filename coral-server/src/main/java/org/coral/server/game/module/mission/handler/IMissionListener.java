package org.coral.server.game.module.mission.handler;

/**
 * 任务处理完成监听器, 用于处理任务后的操作
 * @auth Jeremy
 * @date 2021年2月28日下午9:45:37
 */
@FunctionalInterface
public interface IMissionListener<V1, V2> {
	
	/**
	 * 任务回调
	 * @param v
	 * @return  
	 * @return R  
	 * @date 2021年2月28日下午9:50:14
	 */
	public void call(V1 v1, V2 v2);

}
