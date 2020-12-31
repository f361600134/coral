package org.coral.server.queue;

import org.coral.server.utils.CircularFifoQueue;
import org.coral.server.utils.ConcurrentFixSizeQueue;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.MinMaxPriorityQueue;


public class QueueTest {
	
	public static void main(String[] args) {
		testQueue();
	}
	
	public static void testQueue() {
		ConcurrentFixSizeQueue<Integer> queue = new ConcurrentFixSizeQueue<Integer>(2);
		queue.add(1);
		queue.add(2);
		queue.add(3);
		System.out.println(queue);
	}
	
	public static void testCircularFifoQueue() {
		CircularFifoQueue<Integer> queue = new CircularFifoQueue<Integer>(2);
		queue.add(1);
		queue.add(2);
		queue.add(3);
//		String json = JSON.toJSONString(queue);
//		System.out.println(json);
//		queue =	JSON.parseObject(json, new TypeReference<CircularFifoQueue<Integer>>() {});
//		System.out.println(queue);
	}
	
	public static void testEvictingQueue() {
		EvictingQueue<Integer> queue = EvictingQueue.create(2);
		queue.add(1);
		queue.add(2);
		queue.add(3);
//		System.out.println(queue);
//		JSONObject  json = JSONObject.toJSON(queue);
//		System.out.println(json);
//		System.out.println(q);
	}
	
	public static void testMinMaxPriorityQueue() {
		MinMaxPriorityQueue<Integer> queue = MinMaxPriorityQueue.create();
		queue.add(2);
		queue.add(1);
		queue.add(3);
		System.out.println(queue);
	}

	 
} 
