package org.coral.server.disruptor.base;

import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Test {
	
	    public static void main(String[] args) {
	        int bufferSize = 1024*1024;//环形队列长度，必须是2的N次方
	        EventFactory<LongEvent> eventFactory = new LongEventFactory();
	        /**
	         * 定义Disruptor，基于单生产者，阻塞策略
	         */
	        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory,bufferSize, Executors.defaultThreadFactory(),ProducerType.SINGLE,new BlockingWaitStrategy());
	        /////////////////////////////////////////////////////////////////////
	        serial(disruptor);//这里是调用各种不同方法的地方.
	        /////////////////////////////////////////////////////////////////////
	        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
	        /**
	         * 输入10
	         */
	        ringBuffer.publishEvent(new LongEventTranslator(),10L);
//	        ringBuffer.publishEvent(new LongEventTranslator(),100L);
	        
//	        System.out.println(ringBuffer.get(ringBuffer.getCursor()).getNumber());
	    }
	    
	    /**
	     * 并行计算实现,c1,c2互相不依赖
	     * <br/>
	     * p --> c11
	     *   --> c21
	     */
	    public static void parallel(Disruptor<LongEvent> disruptor){
	        disruptor.handleEventsWith(new C11EventHandler(),new C21EventHandler());
	        disruptor.start();
	    }
	    
	    /**
	     * 串行依次执行
	     * <br/>
	     * p --> c11 --> c21
	     * @param disruptor
	     */
	    public static void serial(Disruptor<LongEvent> disruptor){
	        disruptor.handleEventsWith(new C11EventHandler()).then(new C21EventHandler());
	        disruptor.start();
	    }
	    
	    /**
	     * 链式并行计算
	     * <br/>
	     *   --> c11 --> c12
	     * p
	     *   --> c21 --> c22
	     * @param disruptor
	     */
	    public static void chain(Disruptor<LongEvent> disruptor){
	        disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler());
	        disruptor.handleEventsWith(new C21EventHandler()).then(new C22EventHandler());
	        disruptor.start();
	    }
	    
	    /**
	     * 并行计算实现,c1,c2互相不依赖,同时C1，C2分别有2个实例
	     * <br/>
	     * p --> c11
	     *   --> c21
	     */
	    public static void parallelWithPool(Disruptor<LongEvent> disruptor){
	        disruptor.handleEventsWithWorkerPool(new C11EventHandler(),new C11EventHandler());
	        disruptor.handleEventsWithWorkerPool(new C21EventHandler(),new C21EventHandler());
	        disruptor.start();
	    }
	    
	    /**
	     * 串行依次执行,同时C11，C21分别有2个实例
	      * <br/>
	      * p --> c11 --> c21
	      * @param disruptor
	      */
	     public static void serialWithPool(Disruptor<LongEvent> disruptor){
	         disruptor.handleEventsWithWorkerPool(new C11EventHandler(),new C11EventHandler()).then(new C21EventHandler(),new C21EventHandler());
	         disruptor.start();
	     }

}
