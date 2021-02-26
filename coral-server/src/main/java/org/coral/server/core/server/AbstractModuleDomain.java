package org.coral.server.core.server;

import org.coral.server.core.annotation.NotUse;

/**
 * 这里的ModuleDomain, 封装Pojo对象, 那么所有玩家的Pojo对象, 就只有一条数据.
 * 这样好处便是, 对于系统内的所有模块数据, 统一获取.
 *  但是带来的问题便是, 对于超复杂对象, 一条数据,过于庞大
 * @author Jeremy
 * @param <T>
 */
@NotUse
public class AbstractModuleDomain<T> implements IModuleDomain<T>{
	
	public Class<T> clazz;

	public Class<T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

}
