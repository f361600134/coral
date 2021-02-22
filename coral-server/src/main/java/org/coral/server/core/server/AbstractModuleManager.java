package org.coral.server.core.server;

import java.util.Map;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

public abstract class AbstractModuleManager<T> implements IModuleManager<T>{
	
	@Autowired protected DataProcessorAsyn process;
	
	/**玩家域缓存*/
	protected final Map<Long, T> domains;
	
	public AbstractModuleManager() {
		this.domains = Maps.newConcurrentMap();
	}
	
	/**
	 * 获取玩家背包
	 */
	public T getDomain(long playerId) {
		T domain = domains.get(playerId);
		if (domain == null) {
			domain = getFromDb(playerId);
			domains.put(playerId, domain);
		}
		return domain;
	}
	
	@Override
	public void remove(long playerId) {
		domains.remove(playerId);
	}
	
	/**
	 * 这里的getFromDB方法, 可以通过获取到domain反射获取pojo对象, 然后set到domain内
	 * 所以
	 * @param playerId
	 * @return
	 */
	public abstract T getFromDb(long playerId);
	
}
