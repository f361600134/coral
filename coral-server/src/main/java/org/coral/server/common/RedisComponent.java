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

@Configuration
public class RedisComponent {

	private static final Logger log = LoggerFactory.getLogger(RedisComponent.class);
	
	@Autowired
	private Map<String, BasePo> basePoMap;
	
	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;
	
	
	@Order(1)
	@Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
		log.info("RedisComponent loading...host:{}, port:{}", connectionFactory.getHostName(), connectionFactory.getPort());
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericFastJsonRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
	
	@Order(10)
	@Bean
    public RedisProcessor redisProcessor() {
		log.info("=======>RedisProcessor loading...");
        return new RedisProcessor(basePoMap, redisTemplate);
    }
	
}
