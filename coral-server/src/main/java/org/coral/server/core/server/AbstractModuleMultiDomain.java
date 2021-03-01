package org.coral.server.core.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.coral.orm.core.base.BasePo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 域内为一对多
 * 
 * @author Jeremy
 * @param <T>
 */
public abstract class AbstractModuleMultiDomain<T extends BasePo> implements IModuleDomain<T> {

	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	private Class<T> basePoClazz;
	protected Map<Long, T> beanMap;

	@SuppressWarnings("unchecked")
	public AbstractModuleMultiDomain() {
		this.beanMap = Maps.newHashMap();
		try {
			Type superClass = getClass().getGenericSuperclass();
			this.basePoClazz = (Class<T>)(((ParameterizedType) superClass).getActualTypeArguments()[0]);
		} catch (Exception e) {
			logger.error("AbstractModuleDomain error", e);
		}
	}

	
	/**
	 * 获取域内的实体map
	 * 
	 * @return
	 */
	public Map<Long, T> getBeanMap() {
		return beanMap;
	}

	/**
	 * 获取域内的实体map
	 * 
	 * @return
	 */
	public T getBean(long key) {
		return beanMap.get(key);
	}

	/**
	 * 初始化数据
	 * 
	 * @param itemList
	 */
	public void initData(List<T> itemList) {
		itemList.forEach(e -> {
			beanMap.put((long) e.key(), e);
		});
	}
	
	@Override
	public Class<T> getBasePoClazz() {
		return basePoClazz;
	}

}
