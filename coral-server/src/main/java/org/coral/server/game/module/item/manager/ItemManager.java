package org.coral.server.game.module.item.manager;

import java.util.List;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.core.server.AbstractModuleManager;
import org.coral.server.game.module.item.domain.Item;
import org.coral.server.game.module.item.domain.ItemDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据管理类, 之所以放在Manager内,是以后如果增加缓存机制, 直接实现缓存接口即可
 * @author Jeremy
 *
 */
@Component
public class ItemManager extends AbstractModuleManager<ItemDomain>{
	
	@Autowired private DataProcessorAsyn process;
	
	@Override
	public ItemDomain getFromDb(long playerId) {
		ItemDomain domain = new ItemDomain();
		List<Item> items = process.selectByIndex(Item.class, new Object[] {playerId});
		domain.initItemBag(items);
		return domain;
	}
	
}
