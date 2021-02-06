package org.coral.server.lettuce;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.coral.server.game.module.user.Stu;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * 使用lettuce操作对象测试,练练手
 * @author Jeremy
 */
public class RedisObjTest {
	
	RedisClient redisClient;
	RedisCommands<String, String> redisCommands;
	RedisTemplate<String, Serializable> redisTemplate;
	StatefulRedisConnection<String, String> connection;
	
	@org.junit.Before
	public void before() {
		RedisURI uri = RedisURI.create("redis://139.9.44.104:6379");
		uri.setPassword("Jeremy2oe5.");
		redisClient = RedisClient.create(uri); 
		connection = redisClient.connect();
		redisCommands = connection.sync();
		
		redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setConnectionFactory(new LettuceConnectionFactory());
	}
	
	@org.junit.Test
	public void test() {
		Stu stu = new Stu(1, "aa");
		redisTemplate.opsForValue().set("Account_Test", stu);
		
	}
	
	@org.junit.After
	public void after() {
		//final 
//		connection.close();
//	    redisClient.shutdown();
	}
	
}
