package org.coral.server.game.module.item.manager;

import java.util.ArrayList;
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
	
	/**
	 * 获取玩家背包
	 */
	public ItemDomain getDomain(long playerId) {
		ItemDomain domain = domains.get(playerId);
		if (domain == null) {
			List<Item> items = process.selectByIndex(Item.class, new Object[] {playerId});
			domain = new ItemDomain();
			domain.initItemBag(initItemsList(items));
			domains.put(playerId, domain);
		}
		return domain;
	}
	
	/**
	 * 初始化道具列表
	 * @param itemList
	 * @return
	 */
	public List<Item> initItemsList(List<Item> itemList) {
		List<Item> result = new ArrayList<Item>();
		for (Item item : itemList) {
			if (item == null)
				continue;
			Item temp = initItem(item);
			if (temp == null) {
				continue;
			}
			result.add(temp);
		}
		// 删除非法数据
		return result;
	}
	
	/**
	 * 初始化单个道具
	 * 
	 * @param item
	 * @return
	 */
	public Item initItem(Item item) {
		if (item != null) {
			//TODO
		}
		return item;
	}

}
