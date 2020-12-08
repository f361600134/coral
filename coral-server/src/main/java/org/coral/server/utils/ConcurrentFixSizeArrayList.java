package org.coral.server.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * 1. 固定列表数据, 溢出则移除 
 * 2. 线程安全
 * 
 * @auth Jeremy
 * @date 2019年5月16日下午7:49:39
 */
public class ConcurrentFixSizeArrayList<E> implements List<E> {

	private List<E> list;
	private int capacity = (int) (1L << 4); // 16
	private final int MAX_CAPACITY = (int) (1L << 14); // 最大容量设置为16384

	public ConcurrentFixSizeArrayList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("The capacity value is less than 0");
		}
		if (capacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("The capacity value is great than " + MAX_CAPACITY);
		}
		this.capacity = capacity; // default 16
		this.list = new ArrayList<E>();
	}

	@Override
	public int size() {
		synchronized (list) {
			return list.size();
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (list) {
			return list.isEmpty();
		}
	}

	@Override
	public boolean contains(Object o) {
		synchronized (list) {
			return list.contains(o);
		}
	}

	@Override
	public Iterator<E> iterator() {
		synchronized (list) {
			return list.iterator();
		}
	}

	@Override
	public Object[] toArray() {
		synchronized (list) {
			return list.toArray();
		}
	}

	@Override
	public <T> T[] toArray(T[] a) {
		synchronized (list) {
			return list.toArray(a);
		}
	}

	@Override
	public boolean add(E e) {
		try {
			synchronized (list) {
				boolean b = list.add(e);
				if (b)
					removeFirst();
				return b;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 不做加锁, 所有添加调用
	 * 
	 * @return void
	 * @date 2019年5月17日下午1:53:36
	 */
	private void removeFirst() {
		while (list.size() > capacity) {
			list.remove(0);
		}
	}

	@Override
	public boolean remove(Object o) {
		try {
			synchronized (list) {
				return list.remove(o);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		try {
			synchronized (list) {
				return list.containsAll(c);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		try {
			synchronized (list) {
				boolean b = list.addAll(c);
				removeFirst();
				return b;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		try {
			synchronized (list) {
				boolean b = list.addAll(index, c);
				removeFirst();
				return b;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		try {
			synchronized (list) {
				return list.removeAll(c);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		try {
			synchronized (list) {
				return list.retainAll(c);
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void clear() {
		try {
			synchronized (list) {
				list.clear();
			}
		} catch (Exception e) {
		}
	}

	@Override
	public E get(int index) {
		try {
			synchronized (list) {
				return list.get(index);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public E set(int index, E element) {
		try {
			synchronized (list) {
				return list.set(index, element);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void add(int index, E element) {
		try {
			synchronized (list) {
				list.add(index, element);
			}
		} catch (Exception e) {
		}
	}

	@Override
	public E remove(int index) {
		try {
			synchronized (list) {
				return list.remove(index);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int indexOf(Object o) {
		synchronized (list) {
			return list.indexOf(o);
		}
	}

	@Override
	public int lastIndexOf(Object o) {
		synchronized (list) {
			return list.lastIndexOf(o);
		}
	}

	@Override
	public ListIterator<E> listIterator() {
		synchronized (list) {
			return list.listIterator();
		}
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		synchronized (list) {
			return list.listIterator(index);
		}
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		synchronized (list) {
			return list.subList(fromIndex, toIndex);
		}
	}

	@Override
	public String toString() {
		synchronized (list) {
			return list.toString();
		}
	}
	
	
//	public static class writeJob implements Runnable {
//		
//		private int max;
//		private List<Integer> list;
//		
//		public writeJob(int max, List<Integer> list) {
//			this.max = max;
//			this.list = list;
//		}
//
//		@Override
//		public void run() {
//			for (int i = 0; i < max; i++) {
//				list.add(i);
//			}
//		}
//		
//	}
//	
//	public static class readJob implements Runnable {
//		
//		private int max;
//		private List<Integer> list;
//		
//		public readJob(int max, List<Integer> list) {
//			this.max = max;
//			this.list = list;
//		}
//
//		@Override
//		public void run() {
//			for (int i = 0; i < max; i++) {
//				list.contains((Integer)i);
//			}
//		}
//		
//	}
//
//	public static void main(String[] args) {
//		int max = 1000000;
//		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10);
//		long startTime = System.currentTimeMillis();
//		List<Integer> list = new ConcurrentFixSizeArrayList<Integer>(20);
//		writeJob job = null;
//		readJob rjob = null;
//		for (int i = 1; i <= 5; i++) {
//			job = new writeJob(max, list);
//			cachedThreadPool.execute(job);
//		}
//		for (int i = 1; i <= 5; i++) {
//			rjob = new readJob(max, list);
//			cachedThreadPool.execute(rjob);
//		}
//		cachedThreadPool.shutdown();
//		while (true) {
//			if (cachedThreadPool.isTerminated()) {
//				System.out.println(list);
//				break;
//			}
//		}
//		System.out.println("cost time:" + (System.currentTimeMillis() - startTime));
//	}

}
