package org.coral.server.game.module.item.manager;

import java.util.List;
import java.util.Map;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.module.item.domain.Item;
import org.coral.server.game.module.item.domain.ItemDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * 数据管理类, 之所以放在Manager内,是以后如果增加缓存机制, 直接实现缓存接口即可
 * @author Jeremy
 *
 */
@Component
public class ItemManager {
	
	@Autowired private DataProcessorAsyn process;
	
	/**玩家背包缓存*/
	private final static Map<Long, ItemDomain> PLAYER_DOMAINS = Maps.newConcurrentMap();
	
	/**
	 * 获取玩家背包
	 */
	public ItemDomain getDomain(long playerId) {
		ItemDomain domain = PLAYER_DOMAINS.get(playerId);
		if (domain == null) {
			List<Item> items = process.selectByIndex(Item.class, new Object[] {playerId});
			domain = new ItemDomain();
			domain.initItemBag(items);
			PLAYER_DOMAINS.put(playerId, domain);
		}
		return domain;
	}

}
