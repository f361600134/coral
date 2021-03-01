package org.coral.server.core.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 这里的ModuleDomain, 封装Pojo对象, 那么所有玩家的Pojo对象, 就只有一条数据.
 * 这样好处便是, 对于系统内的所有模块数据, 统一获取.
 *  但是带来的问题便是, 对于超复杂对象, 一条数据,过于庞大
 * @author Jeremy
 * @param <T>
 */
public abstract class AbstractModuleDomain<T> implements IModuleDomain<T>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired protected DataProcessorAsyn process;
	
	private Class<T> basePoClazz;
	
	protected T bean;
	
	@SuppressWarnings("unchecked")
	public AbstractModuleDomain() {
		try {
			Type superClass = getClass().getGenericSuperclass();
			this.basePoClazz = (Class<T>)(((ParameterizedType) superClass).getActualTypeArguments()[0]);
		} catch (Exception e) {
			logger.error("AbstractModuleDomain error", e);
		}
	}
	
	@Override
	public Class<T> getBasePoClazz() {
		return basePoClazz;
	}

	/**
	 * 初始化数据, 对于单条数据, 直接以此条数据作为bean对象
	 * 如果数据为空, 则初始化新的一条数据
	 */
	@Override
	public void initData(List<T> v) {
		if (v == null || v.isEmpty()) {
			try {
				this.bean = basePoClazz.newInstance();
			} catch (Exception e) {
				logger.error("AbstractModuleDomain initData error", e);
			} 
		}
		else if (v.size() == 1) {
			this.bean = v.get(0);
		}
		else {
			logger.info("AbstractModuleDomain initData has an error, the v.size > 1");
		}
	}
	
}
