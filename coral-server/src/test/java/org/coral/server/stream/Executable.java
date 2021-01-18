package org.coral.server.stream;

/**
 * 可执行接口
 * @author Jeremy
 * @date 2020年8月13日
 *
 * @param <V>
 */
@FunctionalInterface
public interface Executable<V> {
	public V execute() throws Exception;
}