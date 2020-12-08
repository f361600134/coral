package org.coral.server.utils;

/**
 * 
 * 过滤
 */
public interface Filter<T> {
	
	/**
	 * 接收条件
	 * @return true-条件满足
	 */
	boolean accept(T object);

}
