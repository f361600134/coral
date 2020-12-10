package org.coral.server.game.module.item.domain;

import org.coral.orm.core.ProcessorProxy;
import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.base.ItemPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
* @author Jeremy
*/
@Repository
public class Item extends ItemPo implements IItem{
	private final static Logger log = LoggerFactory.getLogger(Item.class);

	public Item() {

	}
	
	/**
	 * 创建一个道具对象
	 */
	public static Item create(long playerId, int configId, int count) {
		Item item = new Item();
		item.setConfigId(configId);
		item.setCount(count);
		item.setPlayerId(playerId);
		return item;
	}
	
	/**
	 * 增加数量
	 */
	@Override
	public int addCount(int value) {
		if(value <= 0) {
			log.info("addCount, the value must be greater than 0, value:"+value);
			return getCount();
		}
		int expect = getCount() + value;
		this.setCount(expect);
		return expect;
	}
	
	/**
	 * 减少数量
	 */
	@Override
	public int deductCount(int value) {
		if(value <= 0) {
			log.info("deductCount, the value must be greater than 0, value:"+value);
			return getCount();
		}
		int expect = getCount() - value;
		this.setCount(expect);
		return expect;
	}

	@Override
	public void save0() {
		ProcessorProxy processorProxy = SpringContextHolder.getInstance().getBean(ProcessorProxy.class);
		processorProxy.insert(this);
	}

	@Override
	public void update0() {
		ProcessorProxy processorProxy = SpringContextHolder.getInstance().getBean(ProcessorProxy.class);
		processorProxy.update(this);
	}

	@Override
	public void remove0() {
		ProcessorProxy processorProxy = SpringContextHolder.getInstance().getBean(ProcessorProxy.class);
		processorProxy.delete(this);
	}

}
