package org.coral.server.queue;

import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.coral.server.game.module.chatplus.domain.ChatRecord;

import com.google.common.collect.Lists;

public class TestQueueList {
	
	public static void main(String[] args) {
		Deque<ChatRecord> queue = new ConcurrentLinkedDeque<>();
		queue.add(new ChatRecord(1));
		queue.add(new ChatRecord(2));
		queue.add(new ChatRecord(3));
		
		List<ChatRecord> list = Lists.newArrayList();
		list.add(queue.peekLast());
		
		System.out.println(queue);
		System.out.println(list);
		
		queue.removeLast();
		
		System.out.println(queue);
		System.out.println(list);
	}

}
