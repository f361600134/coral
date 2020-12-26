package org.coral.net.core.executor;

import java.util.HashMap;
import java.util.Map;

/**
 * Disruptor线程策略
 * 线程策略不集成在spring中
 */
public enum DisruptorStrategy {
	
	/**
	 * 在单一个体中执行
	 */
	SINGLE,
	
	/**
	 * 在公共线程中执行
	 */
	COMMON;
	
	private static Map<DisruptorStrategy, DisruptorExecutorGroup> disruptors = new HashMap<>();
	
	static {
		disruptors.put(SINGLE, new DisruptorExecutorGroup(4, "single-disruptor"));
		disruptors.put(COMMON, new DisruptorExecutorGroup(1, "common-disruptor"));
	}
	
	public static DisruptorExecutorGroup get(DisruptorStrategy strategy) {
		return disruptors.get(strategy);
	}
	
}