package org.coral.net.core.executor;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DisruptorExecutor组合.
 *
 */
public class DisruptorExecutorGroup {
	
	private static final Logger log = LoggerFactory.getLogger(DisruptorDispatchTask.class);
	
	private final AtomicInteger childIndex = new AtomicInteger();
	private DisruptorExecutor[] children;
	//是否是2的幂次方,用于取模运算或位运算
	//private boolean powerOfTow;

	
	public DisruptorExecutorGroup() {
	}
	
	/**
	 * 构造函数.
	 * 
	 * @param size         . 直接executor 数量.
	 * @param executorName . 执行数量.
	 */
	public DisruptorExecutorGroup(int size, String executorName) {
		this.children = new DisruptorExecutor[size];
		for (int i = 0; i < size; i++) {
			children[i] = new DisruptorExecutor(executorName + "-" + i);
		}
		//powerOfTow = (size & -size) == size;
		this.startUp();
	}

	/**
	 * 根据index获取DisruptorExecutor.
	 * 
	 * @param index . index.
	 * @return 执行器.
	 */
	public DisruptorExecutor getExecutor(int index) {
		if (index < 0 || index >= children.length) {
			return null;
		}
		return children[index];
	}

	/**
	 * 获取Executor.
	 * TODO 验证一下, 如果使用next获取到的Executor是否能保证同一个线程处理的任务
	 * @return executor.
	 */
	public DisruptorExecutor next() {
		return children[Math.abs(childIndex.getAndIncrement() % children.length)];
	}

	/**
	 * 获取group的大小.
	 * 
	 * @return 数量.
	 */
	public int size() {
		return children.length;
	}

	/**
	 * 启动.
	 */
	public void startUp() {
		for (DisruptorExecutor executor : children) {
			executor.startUp();
		}
	}
	
	/**
	 * 执行
	 * @date 2020年7月7日
	 * @param index 唯一编号
	 * @param task 任务
	 */
	public void execute(final int sessionId, final Runnable task) {
		int	index = sessionId % size();
		getExecutor(index).execute(task);
	}
	
	/**
	 * 执行
	 * @date 2020年7月7日
	 * @param index 唯一编号
	 * @param task 任务
	 */
	public void execute(ExecutorSelector selector, final Runnable task) {
		int	index = selector.selectorId() % size();
		getExecutor(index).execute(task);
	}
	
}
