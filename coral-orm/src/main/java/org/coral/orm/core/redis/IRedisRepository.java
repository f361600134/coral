package org.coral.orm.core.redis;

import org.coral.orm.core.base.BasePo;

public interface IRedisRepository<T extends BasePo> {
	
	public void add(T t);
	
	public T get(String key);
	
}
