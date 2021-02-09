package org.coral.server.common;

import java.io.Serializable;
import java.util.Map;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.redis.RedisProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

//默认不开启redis
//@Configuration
public class RedisComponent {

	private static final Logger log = LoggerFactory.getLogger(RedisComponent.class);
	
	@Autowired
	private Map<String, BasePo> basePoMap;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Bean
	@Order(1)
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
		log.info("RedisComponent loading...host:{}, port:{}", connectionFactory.getHostName(), connectionFactory.getPort());
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
        
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericFastJsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
	
	@Bean
	@Order(10)
    public RedisProcessor redisProcessor() {
		log.info("=======>RedisProcessor loading...");
        return new RedisProcessor(basePoMap, redisTemplate);
    }
	
}
