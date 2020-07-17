package org.coral.net.core.base.executor;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * DisruptorExecutor组合.
 *
 */
@Component
public class DisruptorExecutorGroup implements InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(DisruptorExecutorGroup.class);

	private final AtomicInteger childIndex = new AtomicInteger();
	private DisruptorExecutor[] children;

	
	public DisruptorExecutorGroup() {}
	
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
	 * 
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
	public void execute(int index, Runnable task) {
		index = index % size();
		getExecutor(index).execute(task);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		long startTime = System.currentTimeMillis();
		int size = Runtime.getRuntime().availableProcessors() * 2;
		String executorName = "Disruptor";
		startTime = System.currentTimeMillis();
		this.children = new DisruptorExecutor[size];
		for (int i = 0; i < size; i++) {
			children[i] = new DisruptorExecutor(executorName + "-" + i);
		}
		startUp();
		log.info("End to init DisruptorExecutorGroup, initWorkers:{}, cost time:{}", 
				size, (System.currentTimeMillis() - startTime));
	}

}
