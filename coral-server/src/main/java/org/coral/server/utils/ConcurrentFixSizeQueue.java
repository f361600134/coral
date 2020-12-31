package org.coral.server.utils;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 1. 固定列表数据, 溢出则移除 
 * 2. 线程安全
 * @auth Jeremy
 * @date 2019年5月16日下午7:49:39
 */
public class ConcurrentFixSizeQueue<E> extends ConcurrentLinkedQueue<E> implements Queue<E>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8686912617781664463L;
	
	private int capacity = (int) (1L << 4); // 16
	private final int MAX_CAPACITY = (int) (1L << 14); // 最大容量设置为16384

	public ConcurrentFixSizeQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("The capacity value is less than 0");
		}
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("The capacity value is great than " + MAX_CAPACITY);
		}
		this.capacity = capacity; // default 16
	}

	@Override
	public boolean add(E e) {
		if (super.size() >= capacity) {
			super.remove();
		}
		return super.add(e);
	}
	
	@Override
	public boolean offer(E e) {
		if (super.size() >= capacity) {
			super.remove();
		}
		return super.offer(e);
	}

}
