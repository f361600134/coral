package org.coral.server.game.module.item.domain;

import java.util.Collection;
import java.util.List;

import org.coral.server.core.server.AbstractModuleMultiDomain;
import org.coral.server.game.helper.log.NatureEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 物品域
 * 1. 根据物品唯一id获取到物品
 * 2. 根据物品配置id获取到物品
 * @auth Jeremy
 * @date 2020年12月17日下午10:02:51
 */
public class ItemDomainPlus extends AbstractModuleMultiDomain<Item>{
	
	private static final Logger log = LoggerFactory.getLogger(ItemDomainPlus.class);
	
	/**
	 * 物品占用数量最大限制, 一般来说不用限制, 但是限制一下避免出现意外情况导致的数据问题
	 */
	private static final int LIMIT = 9999;
	
	//更新内容
	private List<IItem> updateItemList;
	private List<IItem> deleteItemList;
	
	public ItemDomainPlus() {
		updateItemList = Lists.newArrayList();
		deleteItemList = Lists.newArrayList();
	}
	
	public List<IItem> getUpdateItemList() {
		return updateItemList;
	}
	
	public List<IItem> getDeleteItemList() {
		return deleteItemList;
	}

	/**
	 * 判断物品是否可以添加
	 * @param playerId
	 * @param configId
	 * @param count
	 * @return
	 */
	public boolean checkAdd(long playerId, int configId, int count) {
		IItem item = getItemByConfigId(configId);
		if(item == null) {
			return true; //	表示背包没有此物品,可以添加
		}
		return (item.getCount() + count) > LIMIT;
	}
	
	/**
	 * 判断物品数量是否充裕
	 * @param playerId
	 * @param configId
	 * @param count
	 * @return
	 */
	public boolean checkEnough(long playerId, int configId, int count) {
		IItem item = getItemByConfigId(configId);
		return item != null && (item.getCount() >= count);
	}
	
	/**
	 * 	新增物品,返回所有物品
	 * @param playerId 玩家id
	 * @param configId 道具id
	 * @param count 数量
	 * @param nEnum 途径
	 * @return  
	 * @return List<IItem>  
	 * @date 2021年2月5日上午12:04:08
	 */
	public List<IItem> addItem(long playerId, int configId, int count, NatureEnum nEnum){
		//背包加入普通道具
		try {
			Item item = getItemByConfigId(configId);
			if (count <= 0) return Lists.newArrayList(item);
			if(item == null) {//没有此物品,创建
				item = Item.create(playerId, configId, count);
				beanMap.put(item.getItemId(), item);
				item.save();
			}else {
				//有此物品,增加数量
				item.addCount(count);
				item.update();
			}
			updateItemList.add(item);
			return Lists.newArrayList(item);
		} catch (Exception e) {
			log.error("addItem error", e);
		}
		return null;
	}
	
	/**
	 * 背包减少普通道具
	 * @param playerId
	 * @param configId
	 * @param count
	 * @param nEnum
	 * @return
	 */
	public boolean deductItemByConfigId(long playerId, int configId, int count, NatureEnum nEnum) {
		//背包减少普通道具
		IItem item = getItemByConfigId(configId);
		return deductItem(item, count);
	}
	
	/**
	 * 背包减少普通道具, 扣除钱都应判断物品是否存在
	 * @param playerId
	 * @param configId
	 * @param count
	 * @param nEnum
	 * @return
	 */
	public boolean deductItemById(long playerId, long id, int count, NatureEnum nEnum) {
		//背包减少普通道具
		//IItem item = getDomainMap().get(id);
		IItem item = getBean(id);
		return deductItem(item, count);
	}
	
	/**
	 * 减少物品数量,物品数量小于等于0时,删掉此物品
	 * @param item
	 * @param count
	 * @return  
	 * @return boolean  
	 * @date 2021年1月16日下午5:48:46
	 */
	private boolean deductItem(IItem item, int count) {
		try {
			if (item != null) {
				int curCount = item.deductCount(count);
				item.update();
				if (curCount > 0) 
					updateItemList.add(item);
				else {
					//已删除物品移除缓存
					beanMap.remove(item.getItemId());
					deleteItemList.add(item);
				}
			}
			return true;
		} catch (Exception e) {
			log.error("deductItem error", e);
			return false;
		}
	}
	
	/**
	 * 通过配置id获取到物品
	 * @return IItem 道具实体, 无则返回null
	 */
	private Item getItemByConfigId(int configId) {
		for (Item item : beanMap.values()) {
			if (item.getConfigId() == configId) {
				return item;
			}
		}
		return null;
	}
	
	/**
	 * 获得玩家所有道具
	 */
	public Collection<Item> getAllItems(){
		return beanMap.values();
	}
	
}




