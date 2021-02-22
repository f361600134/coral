package org.coral.server.core.server;

import org.coral.orm.core.base.IBasePo;
import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.helper.context.SpringContextHolder;

/**
 * 	常用持久化接口
 */
public interface IPersistence extends IBasePo{
	
	/**
	 * 保存
	 * @param po
	 */
	default public void save() {
		DataProcessorAsyn processorAsyn = SpringContextHolder.getInstance().getBean(DataProcessorAsyn.class);
		processorAsyn.insert(this);
	}
	
	/**
	 * 修改
	 * @param po
	 */
	default public void update() {
		DataProcessorAsyn processorAsyn = SpringContextHolder.getInstance().getBean(DataProcessorAsyn.class);
		processorAsyn.update(this);
	}
	
	/**
	 * 删除
	 */
	default public void delete() {
		DataProcessorAsyn processorAsyn = SpringContextHolder.getInstance().getBean(DataProcessorAsyn.class);
		processorAsyn.delete(this);
	}

}
