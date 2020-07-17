package org.coral.net.core.base.executor;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.coral.net.core.HandlerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单生产者，单消费者  用 LinkedBlockingqueue
 * 多生产者，单消费者   用 LinkedBlockingqueue
 * 单生产者 ，多消费者   用 ConcurrentLinkedQueue
 * 多生产者 ，多消费者   用 ConcurrentLinkedQueue
 * 即: 单消费者LinkedBlockingqueue, 多消费者ConcurrentLinkedQueue
 * @author Jeremy
 * @date 2020年7月6日
 *
 */
public class DispatchWorker implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(DispatchWorker.class);
	
	private HandlerProcessor processor;
	private Queue<DispatchTask> taskQueue;

	/**
	 * 初始化任务
	 * @date 2020年7月6日
	 */
	public DispatchWorker(HandlerProcessor processor) {
		this.processor = processor;
		taskQueue = new LinkedBlockingQueue<DispatchTask>();
	}
	
	/**
	 * 任务排队
	 * @date 2020年7月6日
	 * @param task
	 * @throws Exception
	 */
	public void addTask(DispatchTask task) throws Exception {
		this.taskQueue.add(task);
	}

	@Override
	public void run() {
		for (;;) {
			try {
				DispatchTask task = taskQueue.poll();
				if (task != null) {
					processor.invoke(task.getSession(), task.getPacket());
				}
			} catch (Exception e) {
				log.error("协议执行过程出错", e);
			}
		}
	}
	
}