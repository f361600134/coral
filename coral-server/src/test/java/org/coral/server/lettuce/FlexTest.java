package org.coral.server.lettuce;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.google.common.collect.Maps;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FlexTest {

	/**
	 * Subscriber interface
	 */
	@org.junit.Test
	public void testSubscriber() {
		Flux.just("Ben", "Michael", "Mark").subscribe(new Subscriber<String>() {
			public void onSubscribe(Subscription s) {
				s.request(3);
			}

			public void onNext(String s) {
				System.out.println("Hello " + s + "!");
			}

			public void onError(Throwable t) {

			}
			public void onComplete() {
				System.out.println("Completed");
			}
		});
	}

	/**
	 * doOnNext
	 * 1. 测试doOnNext执行流程
	 * 2.最后subscribe时,最终流处理的结果,不同的方法调用, 同一个方法,最后因为条件的变化,最终结果会发生变化
	 */
	@org.junit.Test
	public void testDoOnNext() {
		// Way 1
		Flux.just("Ben", "Michael", "Mark").doOnNext(new Consumer<String>() {
			public void accept(String s) {
				System.out.println("Hello " + s + "!");
			}
		}).doOnComplete(new Runnable() {
			public void run() {
				System.out.println("Completed");
			}
		}).subscribe();

		// Way 2
		Flux.just("Ben", "Michael", "Mark").doOnNext(s -> System.out.println("Hello " + s + "!"))
				.doOnComplete(() -> System.out.println("Completed")).subscribe();
	}

	/**
	 * doOnNext
	 */
	@org.junit.Test
	public void test() {
		Map<String, Integer> commands = Maps.newHashMap();
		commands.put("Ben", 11);
		commands.put("Michael", 15);
		commands.put("Mark", 33);
		// Way 1
		Flux.just("Ben", "Michael", "Mark").flatMap(e -> {
			return Flux.just(commands.get(e));
		}).subscribe(result -> System.out.println("Number of elements in sets: " + result));
//		.flatMap(e->{
//			return Flux.just(commands.get(e));
//		})
//		.reduce((sum, current) -> sum + current)
//		.subscribe(result -> System.out.println("Number of elements in sets: " + result));
	}
	
	/**
	 * 异步
	 */
	@org.junit.Test
	public void reactive() {
		Map<String, Integer> commands = Maps.newHashMap();
		commands.put("Ben", 11);
		commands.put("Michael", 15);
		commands.put("Mark", 33);
		
		Flux.just("Ben", "Michael", "Mark")
		.flatMap(key -> {
		    System.out.println("Map 1: " + key + " (" + Thread.currentThread().getName() + ")");
		    return Flux.just(commands.get(key));
		})
		.flatMap(value -> {
		    System.out.println("Map 2: " + value + " (" + Thread.currentThread().getName() + ")");
		    return Flux.just(value);
		})
		.subscribeOn(Schedulers.parallel()).subscribe();
		
		try {
			System.out.println("Thread:"+Thread.currentThread().getName());
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printName() {
		System.out.println("============>Thread name:" + Thread.currentThread().getName());
	}

}
