package org.coral.server.lettuce;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.coral.orm.core.redis.RedisProcessor;
import org.coral.server.game.module.user.Stu;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

/**
 * 
 * @author Jeremy
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisObjTest extends TestCase{ 
	
	
	@Autowired
	private RedisTemplate<String, Serializable> template;
	@Autowired
	private RedisProcessor redisProcessor;
	
	@org.junit.Before
	public void before() {
        System.out.println("=================before========================");
	}
	
	@org.junit.Test
	public void test() {
		System.out.println("===================test1=======================");
//		Stu stu = new Stu(1, "aa");
//		redisTemplate.boundValueOps("Account_Test").set(stu);
//		ValueOperations<String, BasePo> operations= redisTemplate.opsForValue();
//		operations.set("fdd2", stu);
//		boolean exists=redisTemplate.hasKey("fdd2");
//        System.out.println("redis是否存在相应的key"+exists);
//        Stu getUser = (Stu)redisTemplate.opsForValue().get("fdd2");
//        System.out.println("从redis数据库获取的user:"+getUser.toString());
//        System.out.println("===================test2=======================");
//		HashOperations<String, Integer, Stu> operations= redisTemplate.opsForHash();
//		operations.put("Account_Test", stu.getId(), stu);
//		redisTemplate.opsForList().leftPush(Stu.ID, new Stu(1, "aa"));
//		redisTemplate.opsForList().leftPush(Stu.ID, new Stu(2, "bb"));
//		redisTemplate.opsForList().leftPush(Stu.ID, new Stu(3, "cc"));
//		redisProcessor.add(new Stu(1, "a112aA"));
//		redisProcessor.add(new Stu(2, "bb112B"));
//		redisProcessor.add(new Stu(3, "c133cC"));
		Stu stu = new Stu(1, "aaa");
		template.opsForHash().put(stu.poName(), stu.cacheId(), stu);
		
		stu = new Stu(2, "bbb");
		template.opsForHash().put(stu.poName(), stu.cacheId(), stu);
		
		stu = new Stu(3, "ccc");
		template.opsForHash().put(stu.poName(), stu.cacheId(), stu);
		
		template.opsForValue().set("key", "value", 1, TimeUnit.MINUTES);
		
	}
	
	@org.junit.After
	public void after() {
		System.out.println("=================after========================");
//		Stu stu = redisProcessor.get(Stu.class, "1");
//		stu.setName("update");
//		System.out.println(stu);
		//final 
//		connection.close();
//	    redisClient.shutdown();
	}
	
}
