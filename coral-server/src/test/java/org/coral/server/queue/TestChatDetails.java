package org.coral.server.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.coral.server.game.module.chatplus.domain.ChatDetails;
import org.coral.server.game.module.chatplus.domain.ChatRecord;

public class TestChatDetails {
	
	/**
	 *写任务, 增加任务(增加聊天内容)
	 * @author Administrator
	 *
	 */
	public static class writeJob implements Runnable {
		
		private int max;
		private int name;
		private ChatRecord record;
		
		public writeJob(int max, int name, ChatRecord record) {
			this.max = max;
			this.record = record;
			this.name = name;
		}

		@Override
		public void run() {
			ChatDetails chat = null;
			for (int i = 0; i < max; i++) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				chat = ChatDetails.create(i, ""+name, 1, 1);
				record.addChat(chat);
				System.out.println("=====>"+record);
			}
		}
		
	}
	
	/**
	 * 另外一条写任务, 修改ChatDetails内的属性
	 * @author Administrator
	 *
	 */
	public static class readJob implements Runnable {
		
		private int max;
		private ChatRecord record;
		
		public readJob(int max, ChatRecord record) {
			this.max = max;
			this.record = record;
		}

		@Override
		public void run() {
			for (int i = 0; i < max; i++) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
				record.addDelPlayer(1);
			}
		}
		
	}
	
	public static void main(String[] args) {
		int max = 100000;
		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10);
		long startTime = System.currentTimeMillis();
		ChatRecord record = new ChatRecord(1);
//		List<ChatDetails> list = new ConcurrentFixSizeArrayList<ChatDetails>(20);
		writeJob job = null;
		readJob rjob = null;
		for (int i = 1; i <= 5; i++) {
			job = new writeJob(max, i, record);
			cachedThreadPool.execute(job);
		}
		for (int i = 1; i <= 5; i++) {
			rjob = new readJob(max, record);
			cachedThreadPool.execute(rjob);
		}
		cachedThreadPool.shutdown();
		while (true) {
			if (cachedThreadPool.isTerminated()) {
//				System.out.println(record);
				break;
			}
		}
		System.out.println("cost time:" + (System.currentTimeMillis() - startTime));
	}

	

}
