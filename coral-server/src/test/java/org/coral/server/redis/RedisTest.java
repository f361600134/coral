package org.coral.server.redis;

import java.util.concurrent.TimeUnit;

import org.coral.server.game.module.user.Stu;
import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;

public class RedisTest {
	
	RedissonClient redisson = null;
	
	@org.junit.Before
	public void before() {
		// 1. Create config object
		Config config = new Config();
		//config.useClusterServers().addNodeAddress("redis://139.9.44.104:6379");
		config.useSingleServer()
		.setAddress("redis://139.9.44.104:6379")
		.setPassword("Jeremy2oe5.")
		.setClientName("GATE")
		.setConnectionMinimumIdleSize(24)//	最小空闲连接数,按需要配置,默认24个
		.setIdleConnectionTimeout(3000)//	空闲连接超时时间,在这个这个超时时间没有使用过,会被从连接池内移除掉.默认10000ms
		.setConnectTimeout(3000)//	超时时间,默认10000ms
		.setRetryAttempts(3)//	重试次数,在指定次数无法向redis server发送数据,会抛出异常,默认3
		.setRetryInterval(1500)//	重试时间间隔,默认1500ms
		.setPingConnectionInterval(30000)//PING连接间隔,心跳间隔时长
		.setKeepAlive(true)//是否保持tcp连接存活状态,默认false
		.setTimeout(3000)
		;
		redisson = Redisson.create(config);
	}
	
	
	@org.junit.Test
	public void test() {
		String key = "myMap";
		String aa = "aa";
		String bb = "bb";
		
		//	新增数据
//		RMap<String, Stu> map = redisson.getMap(key, new JsonJacksonCodec());
		RLocalCachedMap<String, Stu> map = redisson.getLocalCachedMap(key,  
				new StringCodec(), LocalCachedMapOptions.defaults());
		
		System.out.println(map.get(aa));
		Stu stua = Stu.create(aa);
		stua.setName("bb");
		map.put(key, stua);
		
		/*
		 * key->old key, value->new value, apply->compare oldValue and 
		 * new value, and return the new value after modify..
		 */
//		Stu ret = map.merge(aa, stua, (oldValue, value)->{
//			if (oldValue == null) {
//				return null;
//			}
//			if (value == null) {
//				return oldValue;
//			}
//			if (!oldValue.getName().equals(value.getName())) {
//				oldValue.setName(value.getName());
//			}
//			return value;//new value
//		});
		
//		System.out.println(ret == stua);
	}
	
	
//	private void print(RMap<String, Stu> map) {
//		if (map.isEmpty()) {
//			Stu stu = Stu.create();
//			map.put(stu.getName(), stu);
//			System.out.println("不存在对象, 创建"+stu);
//		}else {
//			Stu stu = map.get("aa");
//			System.out.println("存在对象, 输出"+stu);
//			if (stu != null) {
//				int age = stu.getAge();
//				age ++;
//				stu.setAge(age);
//				map.put(stu.getName(), stu);
//			}
////			map.remove("aa");
//		}
//	}
	
	@org.junit.After
	public void after() {
		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		redisson.shutdown();
	}
	

}
