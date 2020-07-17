package org.coral.net.core.base.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.coral.net.core.HandlerProcessor;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 协议分发工作者
 * 1. 显示实例化固定线程, 初始化线程消耗20s的时长.弃用
 * 2. 使用springboot提供的线程池
 */
public class DispatchExecutor  {
	
	private static final Logger log = LoggerFactory.getLogger(DispatchExecutor.class);
	
	private DispatchWorker[] workers;
	private ExecutorService[] children;
	
	public DispatchExecutor() {
		
	}
	
	/**
	 * 初始化线程池
	 * @date 2020年7月6日
	 * @param processor
	 * @param size
	 * @param name
	 */
	public void initWorkers(HandlerProcessor processor, int size) {
		long startTime = System.currentTimeMillis();
		workers = new DispatchWorker[size];
		children = new ExecutorService[size];
		for (int i = 0; i < size; i++) {
			//初始化
			DispatchWorker worker = new DispatchWorker(processor);
			ExecutorService executor = Executors.newSingleThreadExecutor();
			executor.execute(worker);

			children [i] = executor;
			workers [i] = worker;
		}
		log.info("End to init DispatchExecutor, initWorkers:{}, cost time:{}", 
				size, (System.currentTimeMillis() - startTime));
	}
	
	/**
	 * 添加任务
	 * @date 2020年7月6日
	 * @param session
	 * @param packet
	 */
	public void addTask(GameSession session, Packet packet) {
		try {
			DispatchTask task = new DispatchTask(session, packet);
			int idx = session.getId() % workers.length;
			workers[idx].addTask(task);
		} catch (Exception e) {
			log.info("addTask error, e:", e);
		}
	}

}
