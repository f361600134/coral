package org.coral.server.lettuce;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

import org.objectweb.asm.TypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import io.lettuce.core.codec.RedisCodec;

public class DefaultCodec<K, V> implements RedisCodec<K, V> {

	private final Class<K> keyClazz;
	private final Class<V> valueClazz;

	@SuppressWarnings("unchecked")
	public DefaultCodec() {
		Type superClass = getClass().getGenericSuperclass();
		ParameterizedType type = ((ParameterizedType) superClass);
		keyClazz = (Class<K>) type.getActualTypeArguments()[0];
		valueClazz = (Class<V>) type.getActualTypeArguments()[1];
	}

	@Override
	public K decodeKey(ByteBuffer bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V decodeValue(ByteBuffer bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer encodeKey(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteBuffer encodeValue(V value) {
		// TODO Auto-generated method stub
		return null;
	}

	private static class DefaultStringCodec {

	}

}