package org.coral.server.core.server;

import java.util.Map;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

public abstract class AbstractModuleManager<T> implements IModuleManager<T>{
	
	@Autowired protected DataProcessorAsyn process;
	
	/**玩家任务缓存*/
	protected final Map<Long, T> domains;
	
	public AbstractModuleManager() {
		this.domains = Maps.newConcurrentMap();
	}
	
	@Override
	public void remove(long playerId) {
		domains.remove(playerId);
	}
}
