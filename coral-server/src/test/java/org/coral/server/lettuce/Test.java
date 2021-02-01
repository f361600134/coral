package org.coral.server.lettuce;

import java.time.Duration;
import java.util.Map;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class Test {
	
	public static void main(String[] args) {
		RedisURI uri = RedisURI.create("redis://139.9.44.104:6379");
		uri.setPassword("Jeremy2oe5.");
		uri.setTimeout(Duration.ofHours(2));//	设置超时时间
//		RedisURI uri = RedisURI.builder().withHost("139.9.44.104").withPort(6379).build();
		RedisClient redisClient = RedisClient.create(uri); 
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> redisCommands = connection.sync();
		
		redisCommands.set("key", "Hello, redis");
		String value = redisCommands.get("key");
		System.out.println("value:"+value);// output: Hello, redis
		
		
		redisCommands.hset("recordName", "FirstName", "John");
		redisCommands.hset("recordName", "LastName", "Smith");
		Map<String, String> record = redisCommands.hgetall("recordName");
		System.out.println(record);// output: {FirstName":"John", "LastName":"Smith"}
		
		//final 
		connection.close();
	    redisClient.shutdown();
		
	}

}
