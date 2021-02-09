package org.coral.server.lettuce;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.reactive.RedisPubSubReactiveCommands;

public class LettuceReactiveTest {
	
	private static Logger logger = LoggerFactory.getLogger(LettuceReactiveTest.class);

	@org.junit.Before
	public void before() {

	}

	@org.junit.Test
	public void test() {
		
		RedisURI uri = RedisURI.create("redis://139.9.44.104:6379");
		uri.setPassword("Jeremy2oe5.");
		
		RedisClient client = RedisClient.create(uri); 
		
//		StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();
//		RedisPubSubAsyncCommands<String, String> async = connection.async();
//		async.publish("channel", "Hello, Redis!");
		
		//	开启一个响应式redis连接
		StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();
		RedisPubSubReactiveCommands<String, String> reactive = connection.reactive();
		
		//	官网问题代码
//		reactive.observeChannels().subscribe(message -> {
//			logger.debug("Got {} on channel",  message);
//			System.out.println("message:"+message+", onSubscribe called on thread " + Thread.currentThread().getName());
//		});
//		reactive.subscribe("channel").subscribe();
		
		//	响应式操作redis服务器
//		reactive.set("channel", "Hello, Redis!!!")
//		.doOnSubscribe(res -> {
//			System.out.println(res+"====> onSubscribe called on thread " + Thread.currentThread().getName());
//		})
//		.doOnNext(val ->{
//			System.out.println("onNext called on thread " + Thread.currentThread().getName());
//		})
//		.then();
//		reactive.publish("channel", "Hello, Redis!!!");
		
//		//	老外测试例子
//		try {
//			reactive.set("key", "value").subscribe(new Consumer<String>() {
//		      @Override
//		      public void accept(final String res) {
//		        assertEquals("OK", res);
//		      }
//		    });
////		    await().atMost(15, TimeUnit.SECONDS).until(TestUtil.reportedSpansSize(tracer), equalTo(2));
//		  }catch (Exception e) {
//		}
		//	官网另一个例子
		reactive.multi().subscribe(multiResponse -> {
		    reactive.set("val", "1").subscribe();
		    reactive.incr("val").subscribe();
		    reactive.get("val").subscribe((val)->{
		    	System.out.println("====>"+val);
		    });
		    reactive.exec().subscribe();
		});
		
		
//		  final List<MockSpan> spans = tracer.finishedSpans();
//		  assertEquals(2, spans.size());

	}

	@org.junit.After
	public void after() {
		try {
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
