package org.coral.server.disruptor.middle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;

import org.coral.server.disruptor.base.LongEventTranslator;
import org.coral.server.game.module.item.event.ItemAddEvent;

import com.google.common.collect.Lists;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Test {
	
	    public static void main(String[] args) {
	        int bufferSize = 1024*1024;//环形队列长度，必须是2的N次方
	        EventFactory<TaskEvent> eventFactory = new BaseEventFactory();
	        /**
	         * 定义Disruptor，基于单生产者，阻塞策略
	         */
	        Disruptor<TaskEvent> disruptor = new Disruptor<TaskEvent>(eventFactory,bufferSize, Executors.defaultThreadFactory(),ProducerType.SINGLE,new BlockingWaitStrategy());
	        /////////////////////////////////////////////////////////////////////
	        register(disruptor);//这里是调用各种不同方法的地方.
	        /////////////////////////////////////////////////////////////////////
	        RingBuffer<TaskEvent> ringBuffer = disruptor.getRingBuffer();
	        
	    	ItemAddEvent event = ItemAddEvent.create(1, 1, 1, 1);
	        for (int i = 0; i < 10; i++) {
		        ringBuffer.publishEvent(new BaseEventTranslator(),event);
			}
//	        System.out.println("threadName:"+Thread.currentThread().getName()+", count:"+event.getCount());
	        
//	        ringBuffer.publishEvent(new LongEventTranslator(),100L);
	        
//	        System.out.println(ringBuffer.get(ringBuffer.getCursor()).getNumber());
	    }
	    
	    /**
	     * 并行计算实现,c1,c2互相不依赖
	     * <br/>
	     * p --> c11
	     *   --> c21
	     */
	    public static void register(Disruptor<TaskEvent> disruptor){
	    	List<EventHandler<TaskEvent>> handlers = Lists.newArrayList(new C1EventHandler(), new C2EventHandler(), new C2EventHandler(), new C3EventHandler());
	    	for (EventHandler<TaskEvent> eventHandler : handlers) {
	    		disruptor.handleEventsWith(eventHandler);
			}
	        disruptor.start();
	    }
	    

}
