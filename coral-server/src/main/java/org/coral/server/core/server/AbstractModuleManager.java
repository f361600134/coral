package org.coral.server.core.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

public abstract class AbstractModuleManager<T extends IModuleDomain<? extends BasePo>> implements IModuleManager<T>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired protected DataProcessorAsyn process;
	
	/**玩家域缓存*/
	protected final Map<Long, T> domains;
	
	protected Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public AbstractModuleManager() {
		this.domains = Maps.newConcurrentMap();
		try {
			Type superClass = getClass().getGenericSuperclass();
			this.clazz = (Class<T>)(((ParameterizedType) superClass).getActualTypeArguments()[0]);
		} catch (Exception e) {
			logger.error("AbstractModuleDomain error", e);
		}
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T getFromDb(long playerId) {
		try {
			T domain = clazz.newInstance();
			List list = process.selectByIndex(domain.getBasePoClazz(), new Object[] {playerId});
			domain.initData(list);
			return domain;
		}catch (Exception e) {
			logger.error("getFromDb error", e);
		}
		return null;
	}
	
}
