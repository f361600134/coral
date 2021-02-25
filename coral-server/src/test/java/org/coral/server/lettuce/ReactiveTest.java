package org.coral.server.lettuce;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Maps;

public class ReactiveTest {
	
	public static void main(String[] args) {
		Map<String, Integer> commands = Maps.newHashMap();
		commands.put("Ben", 11);
		commands.put("Michael", 15);
		commands.put("Mark", 33);
		
//		Observable.just("Ben", "Michael", "Mark")
		//.take(2)
//		.flatMap(e->{
//			System.out.println(e+", Thread:"+Thread.currentThread().getName());
//			return Observable.just(commands.get(e));
//		})
//		.doOnNext(e->{
//			 System.out.println(e+", Thread:"+Thread.currentThread().getName());
//		})
//		.subscribeOn(Schedulers.computation())
//		.publish()
//		.subscribe();
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
//		.subscribe(e->{
//			 System.out.println("Hello " + e + "!");
//		})
//		//分组
//		.groupBy(key -> key.substring(0, 1))
//		.subscribe(e->{
//			e.toList().subscribe(str->{
//				System.out.println("K:" + e.getKey() + ", str:"+str+ "!");
//			});
//		})
		//另一种流处理
//		.flatMap(e->{
//			return Observable.just(commands.get(e));
//		})
//		.doOnNext(e->{
//			 System.out.println(e);
//		})
//		.subscribe()
		;
	}
	
//	public static void main(String[] args){
//		String newStr = strHandler("我是函数型接口",(str)->str.substring(2,5));
//		System.out.println(newStr);
//		}
//
//	public static String strHandler(String str, Function<String, String> fun) {
//		return fun.apply(str);
//	}

}
