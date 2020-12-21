package org.coral.server.game.module.item.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 物品域
 * @auth Jeremy
 * @date 2020年12月17日下午10:02:51
 */
public class ItemDomain {
	

	private static final Logger log = LoggerFactory.getLogger(ItemDomain.class);
	
	//所有的物品 key:itemId(guid)
	private final Map<Long, IItem> itemMap;
	
	//更新内容
	private List<IItem> updateItemList;
	private List<IItem> deleteItemList;
	
	public ItemDomain() {
		itemMap = Maps.newHashMap();
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
	 * 初始化背包
	 */
	public void initItemBag(List<Item> itemList) {
		// 初始化背包
		for (Item item : itemList) {
			itemMap.put(item.getItemId(), item);
		}
	}
	
	/**
	 * 是否可以加入背包
	 * @return true表示可以加入背包, false表示背包满,不可加入背包
	 */
	public boolean canAdd(long playerId, int configId, int count) {
//		if (ConfigItem.isHero(configId)) {//武将需要判断武将库是否可以加入新武将
//			//TODO return Context.getKnightService().canAddHero(playerId, count);
//			return false;//默认不做限制
//		}
		return true;//默认不做限制
	}
	
//	/**
//	 * 判断属性/道具/装备/神装/英雄碎片/符文是否足够
//	 * @param playerId 玩家id
//	 * @param configId 配置id
//	 * @param count 数量
//	 * @return boolean true数量充足, false数量不足.
//	 */
//	public boolean enough(long playerId, int configId, int count) {
//		//数值校验
//		if (count <= 0) {
//			log.info("enough error, the count[{}] of configId[{}] is less than 0.", count, configId);
//			return false;
//		}
//		if (ConfigItem.isPlayerProperty(configId)) {
//			return Context.getResourceAssist().check(playerId, configId, count);
//		}else if (ConfigItem.isItem(configId) || ConfigItem.isHeroPatch(configId) || ConfigItem.isEquip(configId)) {
//			//背包加入普通道具
//			IItem item = getItemByConfigId(configId);
//			//return item == null ? false : (item.getCount() >= count);
//            return item != null && (item.getCount() >= count);
//		}
//		else if (ConfigItem.isGodEquip(configId)) {
//			//神装库加入神装
//			List<IItem> items = getItemsByConfigId(configId);
//			return items.size()>=count;
//		}
//		else if (ConfigItem.isRune(configId)) {
//			//符文库加入碎片
//			List<IItem> items = getItemsByConfigId(configId);
//			return items.size()>=count;
//		}
//		return false;
//	}
	
//	/**
//	 * 添加属性/道具/装备/神装/英雄碎片/符文
//	 * @param playerId 玩家id
//	 * @param configId 配置id
//	 * @param count 消耗数量
//	 * @param nEnum 资源消耗枚举
//	 * @param logDesc 补充信息
//	 * @return List<IItem> 物品列表
//	 */
//	public List<IItem> addItem(long playerId, int configId, int count, NatureEnum nEnum, String logDesc) {
//		if (count <= 0) {
//			return null;
//		}
//		if (ConfigItem.isPlayerProperty(configId)) {
//			//玩家属性特殊, 不需要返回道具
//			Context.getResourceAssist().reward(playerId, configId, count, nEnum, logDesc);
//			//Context.getPlayerService().addProperty(playerId, configId, count, nEnum, logDesc);
//		} else if (ConfigItem.isItem(configId) || ConfigItem.isHeroPatch(configId) || ConfigItem.isEquip(configId)) {//普通道具或碎片
//			//背包加入普通道具
//			try {
//				IItem item = getItemByConfigId(configId);
//				if(item == null) {//没有此物品,创建
//					item = Item.create(playerId, configId, count);
//					itemMap.put(item.getItemId(), item);
//					item.save0();
//				}else {//有此物品,增加数量
//					item.addCount(count);
//					item.update0();
//				}
//				updateItemList.add(item);
//				PlayerLog.obtainItem(playerId, nEnum, logDesc, item.getItemId(), configId, count);
//				if(ConfigItem.isEquip(configId)) {
//					//TODO 获得装备任务检测
//					//TaskConType.checkTaskEquip(playerId,configId,count);
//				}
//				return Lists.newArrayList(item);
//			} catch (Exception e) {
//				log.error("addItem error", e);
//			}
//		}
//		else if (ConfigItem.isGodEquip(configId)) {
//			//背包加入神装
//			List<IItem> items = Lists.newArrayList();
//			for (int i = 0; i < count; i++) {
//				IItem item = GodEquip.create(playerId, configId, 1);
//				itemMap.put(item.getItemId(), item);
////				Context.getDataSyncService().commitAddSync(item);
//				item.save0();
//				updateItemList.add(item);
//				PlayerLog.obtainItem(playerId, nEnum, logDesc, item.getItemId(), configId, 1);
//				items.add(item);
//			}
//			return items;
//		}else if (ConfigItem.isRune(configId)) {
//			//TODO
//		}
//		else if (ConfigItem.isHero(configId)) {
//			//英雄
//			Player player = Context.getPlayerService().getPlayer(playerId);
//			List<IItem> items = Lists.newArrayList();
//			for (int i = 0; i < count; i++) {
//				IItem item = Context.getKnightService().addOtherKnight(player, configId, nEnum);
//				items.add(item);
//				PlayerLog.obtainItem(playerId, nEnum, logDesc,item.getItemId(), configId, 1);
//			}
//			return items;
//		}
//		return null;
//	}
	
//	/**
//	 * 根据唯一id, 减少 属性/道具/装备/神装/英雄碎片/符文
//	 * @param playerId 玩家id
//	 * @param configId 配置id
//	 * @param count 消耗数量
//	 * @param nEnum 资源消耗枚举
//	 * @param logDesc 补充信息
//	 * @return 执行结果
//	 */
//	public boolean deductItem(long playerId, int configId, int count, NatureEnum nEnum, String logDesc) {
//		if(count<=0) return false;
//		if (ConfigItem.isPlayerProperty(configId)) {
//			//return Context.getPlayerService().deductProperty(playerId, configId, count, nEnum, logDesc);
//            return Context.getResourceAssist().cost(playerId, configId, count, nEnum, logDesc);
//		}else if (ConfigItem.isItem(configId) || ConfigItem.isHeroPatch(configId) || ConfigItem.isEquip(configId)) {
//			//背包减少普通道具
//			IItem item = getItemByConfigId(configId);
//			deductItem(playerId,item,count,nEnum,logDesc);
//		}
//		else if (ConfigItem.isGodEquip(configId)) {
//			//TODO somthing...
//		}
//		else if (ConfigItem.isRune(configId)) {
//			//TODO somthing...
//		}
//		return true;
//	}
//	/**
//	 * 背包减少普通道具
//	 * @param playerId 玩家id
//	 * @param item     扣除物品
//	 * @param count    扣除数量
//	 * @param nEnum    使用来源
//	 * @param logDesc  其他描述
//	 * @return 扣除结果
//	 */
//	public boolean deductItem(long playerId,IItem item,int count, NatureEnum nEnum, String logDesc) {
//		try {
//			if (item != null) {
//				int curCount = item.deductCount(count);
////				Context.getDataSyncService().commitSync((Item)item);
//				item.update0();
//				if (curCount > 0) 
//					updateItemList.add(item);
//				else {
//					//已删除物品移除缓存
//					itemMap.remove(item.getItemId());
//					deleteItemList.add(item);
//				}
//				//记录日志
//				PlayerLog.obtainItem(playerId, nEnum, logDesc, item.getItemId(), item.getConfigId(), count);
//			}
//		} catch (Exception e) {
//			log.error("deductItem error", e);
//			return false;
//		}
//		return true;
//	}
	/**
	 * 通过唯一id获取物品
	 * @param itemId 道具唯一id
	 * @return 获取道具
	 */
	public IItem getItem(long itemId) {
		return itemMap.get(itemId);
	}
	
	/**
	 * 通过配置id获取到物品
	 * @return IItem 道具实体, 无则返回null
	 */
	private IItem getItemByConfigId(int configId) {
		for (IItem item : itemMap.values()) {
			if (item.getConfigId() == configId) {
				return item;
			}
		}
		return null;
	}
	/**
	 * 通过配置id获取到物品
     * @param configId 道具配置id
	 * @return 道具列表
	 */
    private List<IItem> getItemsByConfigId(int configId) {
		List<IItem> items = Lists.newArrayList();
		for (IItem item : itemMap.values()) {
			if (item.getConfigId() == configId) {
				items.add(item);
			}
		}
		return items;
	}
	/**
	 * 通过类型获取物品列表
	 * @param type 道具类型
	 * @return 道具列表
	 */
	public Collection<IItem> getItemsByType(int type) {
		List<IItem> items = Lists.newArrayList();
//			Iterator<Entry<Long, IItem>> iter = itemMap.entrySet().iterator();
//			while (iter.hasNext()) {
//				Entry<Long, IItem> entry = iter.next();
//				IItem item = entry.getValue();
		for (IItem item : itemMap.values()) {
			if (item.isType(type)) {
				items.add(item);
			}
		}
		return items;
	}
	
	/**
	 * 获得玩家所有道具
	 */
	public Collection<IItem> getAllItems(){
		return itemMap.values();
	}

}
