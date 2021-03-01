package org.coral.server.core.server;

import java.util.List;

public interface IModuleDomain<T> {
	
	public Class<T> getBasePoClazz();
	
	public void initData(List<T> v);
	
	default public T get() {
		return null;
	}
	
}
