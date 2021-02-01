package org.coral.server.redis;

import org.coral.server.game.module.user.Stu;
import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import ch.qos.logback.core.net.server.Client;

public class Test {
	
	public static void main(String[] args) {
		// 1. Create config object
		Config config = new Config();
//		config.useClusterServers().addNodeAddress("redis://139.9.44.104:6379");
		config.useSingleServer()
		.setAddress("redis://139.9.44.104:6379")
		.setPassword("Jeremy2oe5.")
		;
		
		RedissonClient redisson = Redisson.create(config);
		
		//RedissonReactiveClient redissonReactive = Redisson.createReactive(config);
		
//		String name = "stu";
//		RBucket<Stu> bucket = redisson.getBucket(name);
//		Stu stu = bucket.get();
//		System.out.println(stu);
//		bucket.set(Stu.create());
//		stu = bucket.get();
//		System.out.println(stu);
		String key = "myMap";
//		// 3. Get Redis based implementation of java.util.concurrent.ConcurrentMap
//		RMap<String, Stu> map = redisson.getMap(key);
		RMap<String, Stu> map = redisson.getLocalCachedMap(key, LocalCachedMapOptions.defaults());
		Stu stu = map.get("aa");
		stu.setAge(1000000);
//		Stu stu = Stu.create();
//		map.put(stu.getName(), stu);
		map.put("aa", stu);

//		// 4. Set value to redis db.
//		RBucket<RMap<String, Stu>> bucket = redisson.getBucket(key);
//		bucket.set(map);
		
		redisson.shutdown();
		
		// 5.output value
		
	}
	
}
