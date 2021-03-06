package org.coral.server.game.module.item.service;

import java.util.Collection;
import java.util.List;

import org.coral.server.game.helper.ResourceType;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.module.item.domain.IItem;
import org.coral.server.game.module.item.domain.ItemDomain;
import org.coral.server.game.module.item.manager.ItemManager;
import org.coral.server.game.module.item.proto.AckBagListResp;
import org.coral.server.game.module.item.proto.AckDeleteBagResp;
import org.coral.server.game.module.item.proto.AckUpdateBagResp;
import org.coral.server.game.module.player.service.IPlayerService;
import org.coral.server.game.module.resource.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * 资源服务
 * @author Jeremy
 */
@Service
public class ItemService implements IItemService, IResourceService{
	
	private static final Logger log = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired private IPlayerService playerService;
	
	@Autowired private ItemManager itemManager;

	/**
	 * 当登陆成功,下发物品列表
	 */
	public void onLogin(long playerId) {
		ItemDomain domain = itemManager.getDomain(playerId);
		Collection<IItem> items = domain.getAllItems();
		//登陆成功,下发背包信息
		AckBagListResp ack = AckBagListResp.newInstance();
		ack.addItem(items);
		playerService.sendMessage(playerId, ack);
	}
	
	/**
	 * 当玩家离线,移除掉道具模块数据
	 * @param playerId
	 */
	public void onLogout(long playerId) {
		itemManager.remove(playerId);
	}
	
	// 推送更新物品列表至前端
	public void responseUpdateItemList(long playerId, List<IItem> itemList) {
		// 更新物品
		if (!itemList.isEmpty()) {
			AckUpdateBagResp ack = AckUpdateBagResp.newInstance();
			ack.addItem(itemList);
			playerService.sendMessage(playerId, ack);
		}
	}
	//推送删除物品列表至前端
	public void responseDeleteItemList(long playerId, List<IItem> itemList){
		//更新物品
		if (!itemList.isEmpty()) {
			AckDeleteBagResp ack = AckDeleteBagResp.newInstance();
			for (IItem item : itemList) {
				ack.addItemId(item.getItemId());
			}
			playerService.sendMessage(playerId, ack);
			itemList.clear();
		}
	}

	@Override
	public int resType() {
		return ResourceType.Item.getType();
	}
	
	@Override
	public boolean checkAdd(long playerId, Integer configId, Integer value) {
		ItemDomain domain = itemManager.getDomain(playerId);
		if (domain == null) return false;
		return domain.checkAdd(playerId, configId, value);
	}
	
	@Override
	public boolean checkEnough(long playerId, Integer configId, Integer value) {
		ItemDomain domain = itemManager.getDomain(playerId);
		if (domain == null) return false;
		return domain.checkEnough(playerId, configId, value);
	}

	@Override
	public void reward(long playerId, Integer configId, Integer value, NatureEnum nEnum) {
		ItemDomain domain = itemManager.getDomain(playerId);
		if (domain == null)	return;
		//背包加入普通道具
		domain.addItem(playerId, configId, value, nEnum);
	}

	@Override
	public void cost(long playerId, Integer configId, Integer value, NatureEnum nEnum) {
		ItemDomain domain = itemManager.getDomain(playerId);
		if (domain == null)	return;
		domain.deductItemByConfigId(playerId, configId, value, nEnum);
	}
	
	@Override
	public void cost(long playerId, Long id, NatureEnum nEnum) {
		ItemDomain domain = itemManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		/*
		 * 道具默认有数量概念, 唯一的物品数量默认为1.
		 * 所以唯一道具,跟普通道具在扣除上面,逻辑相同,当数量为0则清除.
		 * 这里参数就默认扣除未1
		 */
		domain.deductItemById(playerId, id, 1, nEnum);
	}
	
	/**
	 * 通过类型获取物品列表
	 * @param type 道具类型
	 * @return 道具列表
	 */
	public Collection<IItem> getItemsByType(long playerId, int type) {
		ItemDomain domain = itemManager.getDomain(playerId);
		if (domain == null) {
			return null;
		}
		List<IItem> result = Lists.newArrayList();
		Collection<IItem> items = domain.getAllItems();
		for (IItem item : items) {
			if (item.isType(type)) {
				result.add(item);
			}
		}
		return result;
	}

//	public List<IItem> addNewItem(long playerId, Map<Integer, Integer> items, NatureEnum nEnum, String logDesc) {
//		List<IItem> ret = Lists.newArrayList();
//		for (Entry<Integer, Integer> entry : items.entrySet()) {
//			List<IItem> its = addItem(playerId, entry.getKey(), entry.getValue(), nEnum, logDesc);
//			if (its != null) {
//				ret.addAll(its);
//			}
//		}
//		responseUpdateItemList(playerId, ret);
//		return ret;
//	}
//	/**
//	 * 操作物品
//	 * 
//	 */
//	public int operateItem(long playerId, int opType, long itemId, int num) {
//		if(opType == ConfigItem.COMPOSE) {//合成操作
//			return compose(playerId, itemId, num);
//		}else if(opType == ConfigItem.DECOMPOSE){//分解出售操作
//			return deCompose(playerId, itemId, num);
//		}
//		return 0;
//	}

//	private int compose(long playerId, long itemId, int num) {
//		IItem item = getItem(playerId, itemId);
//		if(item == null) {
//			return ConfigTipsMgr.Bag_202;//不存在道具
//		}
//		ConfigItemCompose config = ConfigManager.get(ConfigItemCompose.class, item.getConfigId());
//		if(config == null) {
//			return ConfigTipsMgr.Bag_203;//不支持该操作
//		}
//		//消耗道具
//		Map<Integer, Integer> costMap = Maps.newHashMap(config.getCostItemMap());
//		costMap.put(config.getID(), config.getNeedNum() * num);
//		if (!enoughItem(playerId, costMap)) {
//			return ConfigTipsMgr.Bag_204;//道具不足
//		}
//		//扣除物品, 增加奖励
//		this.deductItem(playerId, costMap, NatureEnum.BAG_COMPOSE, "");
//		
//		Map<Integer, Integer> rewardMap = new HashMap<Integer, Integer>();
//		for (int i = 0; i < num; i++) {
//			int configId = config.getRewardItemByRate();
//			int curNum = rewardMap.getOrDefault(configId, 0);
//			rewardMap.put(configId, curNum+1);
//		}
//		List<IItem> items = this.addNewItem(playerId, rewardMap, NatureEnum.BAG_COMPOSE, "");
//		//提示弹窗
//		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(items);
////		SendMessageUtil.sendResponse(playerId, resp);
//		PlayerHelper.sendMessage(playerId, resp);
//		return 0;
//	}
//	
//	/**
//	 * 分解, 出售操作
//	 * @param playerId
//	 * @param itemId
//	 * @param num
//	 * @return
//	 */
//	private int deCompose(long playerId, long itemId, int num) {
//		IItem item = getItem(playerId, itemId);
//		if(item == null) {
//			return ConfigTipsMgr.Bag_202;//不存在道具
//		}
//		ConfigItemDecompose config = ConfigManager.get(ConfigItemDecompose.class, item.getConfigId());
//		if(config == null) {
//			return ConfigTipsMgr.Bag_203;//不支持该操作
//		}
//		Map<Integer, Integer> costMap = Maps.newHashMap();
//		costMap.put(config.getID(), num);
//		if (!enoughItem(playerId, costMap)) {
//			return ConfigTipsMgr.Bag_204;//道具不足
//		}
//		Map<Integer, Integer> rewardMap = Maps.newHashMap();
//		for (Integer key : config.getRewardItemMap().keySet()) {
//			rewardMap.put(key, config.getRewardItemMap().get(key) * num);
//		}
//		this.deductItem(playerId, costMap, NatureEnum.BAG_DECOMPOSE, "");
//		this.addNewItem(playerId, rewardMap, NatureEnum.BAG_DECOMPOSE, "");
//		//提示弹窗
//		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(rewardMap);
////		SendMessageUtil.sendResponse(playerId, resp);
//		PlayerHelper.sendMessage(playerId, resp);
//		return 0;
//	}
//	
//	/**
//	 * 购买次数
//	 */
//	public int buyCount(long playerId, int type) {
//		FrequencyEnum fenum = FrequencyEnum.getEnum(type);
//		if (fenum == null) {
//			return ConfigTipsMgr.Bag_205;
//		}
//		boolean bool = fenum.checkUseCount(playerId);
//		if (!bool) {
//			return ConfigTipsMgr.Bag_207;
//		}
//		int useCount = fenum.getUseCount(playerId);
//		ConfigFrequency config = ConfigFrequency.getConfig(type, 1);
//		if (config == null) {
//			return ConfigTipsMgr.Bag_205;
//		}
//		bool = this.enoughItem(playerId, config.getConsumeMap());
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;
//		}
//		//扣除消耗
//		this.deductItem(playerId, config.getConsumeMap(), NatureEnum.BAG_BUYCOUNT, StringUtils.toLogString("type", type));
//		//增加次数
//		//playerData.alterDayInitData(fenum.getBuyCountKey(),1);
//		fenum.addUseCount(playerId);
//		return 0;
//	}
//	
//	/**
//	* 请求一键出售,此功能仅用于装备
//	* @param playerId
//	* @param req 请求消息体
//	* @param ack 返回消息
//	* @author Jeremy
//	*/
//	public int reqQuickSellItemEquip(long playerId, PBBag.ReqQuickSellItemEquip req, AckQuickSellItemEquipResp ack){
//		List<IItem> iItems = getItems(playerId);
//		//筛选符合要求的装备
//		Map<Integer, Integer> sellMap = Maps.newHashMap();
//		Map<Integer, Integer> rewardMap = Maps.newHashMap();
//		//获取到所有装备
//		Collection<IItem> items = getItemsByType(playerId, PropsSuperTypeEnum.Equip.getType());
//		for (IItem item : items) {
//			ConfigEquip config =ConfigManager.get(ConfigEquip.class,item.getConfigId());
//			ConfigItemDecompose configDecompose = ConfigManager.get(ConfigItemDecompose.class, item.getConfigId());
//			if (config == null || configDecompose == null) {//表示物品配置错误或者物品无法出售
//				continue;
//			}
//			else if (req.getQualitysList().contains(config.getQuality())//星级或品质复合要求
//					|| req.getStarsList().contains(config.getStar())) {
//				sellMap.put(item.getConfigId(), item.getCount());
//				CollectionUtil.mergeToMap(configDecompose.getRewardItemMap(), rewardMap);
//			}
//		}
//		if (sellMap.isEmpty()) {
//			return ConfigTipsMgr.Bag_206;
//		}
//		//出售物品
//		this.deductItem(playerId, sellMap, NatureEnum.BAG_QUICKSELLEQUIP, "");
//		this.addNewItem(playerId, rewardMap, NatureEnum.BAG_DECOMPOSE, "");
//		//提示弹窗
//		//SendMessageUtil.sendResponse(playerId, AckRewardsResp.newInstance().addAllRewards(rewardMap));
//		PlayerHelper.sendMessage(playerId, AckRewardsResp.newInstance().addAllRewards(rewardMap));
//		//更新道具
//		//this.responseDeleteItemList(playerId, bag);
//		return 0;
//	}

//	/**
//	 * 背包使用礼包
//	 */
//	public int reqBagUsePackage(long playerId, PBBag.ReqBagUsePackage req, AckBagUsePackageResp resp) {
//		int configId = req.getItemId();
//		int num = req.getNum();
//		ConfigBagOperation config = ConfigManager.get(ConfigBagOperation.class,configId);
//		if (config == null) {
//			return ConfigTipsMgr.Bag_202;
//		}
//		if (config.getType() == ConfigBagOperation.OPTTYPE_RANDOM) {//随机
//			//随机操作
//			return this.usePackageRandom(playerId, configId, num);
//		}else if (config.getType() == ConfigBagOperation.OPTTYPE_CHOOSE) {//选择一件道具
//			// 选择操作
//			return this.usePackageChoose(playerId, configId, num, req.getMaterialIdList());
//		}else if (config.getType() == ConfigBagOperation.OPTTYPE_ALL) {//所有道具
//			//所有道具
//			return this.usePackageAll(playerId, configId, num);
//		}
//		return 0;
//	}
//	
//	/**
//	 * 使用随机礼包
//	 * @param playerId
//	 * @param configId
//	 * @param num
//	 * @return
//	 */
//	private int usePackageRandom(long playerId, int configId, int num) {
//		ConfigBagOperation config = ConfigManager.get(ConfigBagOperation.class,configId);
//		//消耗确认
//		Map<Integer, Integer> costItem = Maps.newHashMap();
//		costItem.put(configId, num);
//		boolean bool = this.enoughAndDeductItem(playerId, costItem, 
//				NatureEnum.BAG_USEPACKAGE_RANDOM, StringUtils.toLogString("configId", configId, "num", num));
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;//消耗物品不足
//		}
//		Map<Integer, Integer> rewardMap = Maps.newHashMap();
//		for (int i = 0; i < num; i++) {
//			GoodCountData configData = config.getRandomMaterialByRate();
//			int count = rewardMap.getOrDefault(configData.getId(), 0);
//			rewardMap.put(configData.getId(), count+configData.getCount());
//		}
//		this.addNewItem(playerId, rewardMap, NatureEnum.BAG_USEPACKAGE_RANDOM, StringUtils.toLogString("configId", configId, "num", num));
//		//提示弹窗
//		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(rewardMap);
//		SendMessageUtil.sendResponse(playerId, resp);
//		return 0;
//	}
//	
//	/**
//	 * 使用选择礼包
//	 * @param playerId
//	 * @param configId
//	 * @param num
//	 * @param chooseIds
//	 * @return
//	 */
//	private int usePackageChoose(long playerId, int configId, int num, List<Integer> chooseIds) {
//		ConfigBagOperation config = ConfigManager.get(ConfigBagOperation.class,configId);
//		if (config.getSelectNumber() != chooseIds.size()) {
//			return ConfigTipsMgr.Bag_209; //数量不一致
//		}
//		for (Integer chooseId : chooseIds) {
//			if (!config.getMaterialMap().containsKey(chooseId)) {
//				return ConfigTipsMgr.Bag_208; //选择道具不存在
//			}
//		}
//		Map<Integer, Integer> costItem = Maps.newHashMap();
//		costItem.put(configId, num);
//		boolean bool = this.enoughAndDeductItem(playerId, costItem, 
//				NatureEnum.BAG_USEPACKAGE_CHOOSE, StringUtils.toLogString("configId", configId, "num", num));
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;//消耗物品不足
//		}
//		Map<Integer, Integer> rewardMap = Maps.newHashMap();
//		for (Integer chooseId : chooseIds) {
//			int count = config.getMaterialMap().get(chooseId);
//			rewardMap.put(chooseId, count * num);
//		}
//		this.addNewItem(playerId, rewardMap, NatureEnum.BAG_USEPACKAGE_CHOOSE, StringUtils.toLogString("configId", configId, "num", num));
//		//提示弹窗
//		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(rewardMap);
//		SendMessageUtil.sendResponse(playerId, resp);
//		return 0;
//	}
//	
//	/**
//	 * 使用礼包-获取礼包内所有道具
//	 * @return
//	 */
//	private int usePackageAll(long playerId, int configId, int num) {
//		Map<Integer, Integer> costItem = Maps.newHashMap();
//		costItem.put(configId, num);
//		boolean bool = this.enoughAndDeductItem(playerId, costItem, 
//				NatureEnum.BAG_USEPACKAGE_ALL, StringUtils.toLogString("configId", configId, "num", num));
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;
//		}
//		
//		Map<Integer, Integer> rewardMap = Maps.newHashMap();
//		ConfigBagOperation config = ConfigManager.get(ConfigBagOperation.class,configId);
//		
//		for (Integer rewardId : config.getMaterialMap().keySet()) {
//			int count =  config.getMaterialMap().get(rewardId);
//			rewardMap.put(rewardId, count * num);
//		}
//		this.addNewItem(playerId, rewardMap, NatureEnum.BAG_USEPACKAGE_ALL, StringUtils.toLogString("configId", configId, "num", num));
//		//提示弹窗
//		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(rewardMap);
//		SendMessageUtil.sendResponse(playerId, resp);
//		return 0;
//	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	
//	/**
//	 * 是否能加入背包
//	 * @param playerId
//	 * @param items
//	 * @return
//	 */
//	public boolean canAdd(long playerId, Map<Integer, Integer> items) {
//		for (Entry<Integer, Integer> entry : items.entrySet()) {
//			boolean bool = canAdd(playerId, entry.getKey(), entry.getValue());
//			if (!bool) return bool;
//		}
//		return false;
//	}
//
//	/**
//	 * 是否可以加入背包
//	 * @return true表示可以加入背包, false表示背包满,不可加入背包
//	 */
//	public boolean canAdd(long playerId, int configId, int count) {
//		if (ConfigItem.isHero(configId)) {//武将需要判断武将库是否可以加入新武将
//			HeroService heroService = InstanceFactory.getBean(HeroService.class);
//			return heroService.canAddHero(playerId, count);
//		}
//		return true;//默认不做限制
//	}
//	
//	
//	//背包对外接口
//	public boolean enoughItem(long playerId, Map<Integer, Integer> items) {
//		for (Entry<Integer, Integer> entry : items.entrySet()) {
//			boolean bool = enough(playerId, entry.getKey(), entry.getValue());
//			if (!bool) return false;
//		}
//		return true;
//	}
//
//	/**
//	 * 判断属性/道具/装备/神装/英雄碎片/符文是否足够
//	 * @param playerId 玩家id
//	 * @param configId 配置id
//	 * @param count 数量
//	 * @return boolean true数量充足, false数量不足.
//	 */
//	private boolean enough(long playerId, int configId, int count) {
//		//数值校验
//		if (count <= 0) {
//			return false;
//		}
//		if (ConfigItem.isPlayerProperty(configId)) {
//			return Context.getResourceAssist().check(playerId, configId, count);
//		}else if (ConfigItem.isItem(configId) || ConfigItem.isHeroPatch(configId) || ConfigItem.isEquip(configId)) {
//			//背包加入普通道具
//			IItem item = getItemByConfigId(playerId, configId);
//			//return item == null ? false : (item.getCount() >= count);
//			return item != null && (item.getCount() >= count);
//		}
//		else if (ConfigItem.isGodEquip(configId)) {
//			//神装库加入神装
//			List<IItem> items = getItemsByConfigId(playerId, configId);
//			return items.size()>=count;
//		}
//		else if (ConfigItem.isRune(configId)) {
//			//符文库加入碎片
//			List<IItem> items = getItemsByConfigId(playerId, configId);
//			return items.size()>=count;
//		}
//		return false;
//	}
//
//	/**
//	 * 通过配置id获取到物品
//	 * @param configId 道具配置id
//	 * @return 道具列表
//	 */
//	private List<IItem> getItemsByConfigId(long playerId, int configId) {
//		List<IItem> items = Lists.newArrayList();
//		for (IItem item : getItems(playerId)) {
//			if (item.getConfigId() == configId) {
//				items.add(item);
//			}
//		}
//		return items;
//	}
//
//	/**
//	 * 通过配置id获取到物品
//	 * @return IItem 道具实体, 无则返回null
//	 */
//	public IItem getItemByConfigId(long playerId, int configId) {
//		for (IItem item : getItems(playerId)) {
//			if (item.getConfigId() == configId) {
//				return item;
//			}
//		}
//		return null;
//	}
//
//
//	/**
//	 * 添加属性/道具/装备/神装/英雄碎片/符文
//	 * @param playerId 玩家id
//	 * @param configId 配置id
//	 * @param count 消耗数量
//	 * @param nEnum 资源消耗枚举
//	 * @param logDesc 补充信息
//	 * @return List<IItem> 物品列表
//	 */
//	private List<IItem> addItem(long playerId, int configId, int count, NatureEnum nEnum, String logDesc) {
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
//				IItem item = getItemByConfigId(playerId, configId);
//				if(item == null) {//没有此物品,创建
//					item = Item.create(playerId, configId, count);
////					itemMap.put(item.getItemId(), item);
////					Context.getDataSyncService().commitAddSync((Item)item);
//					item.save0();
//				}else {//有此物品,增加数量
//					item.addCount(count);
////					Context.getDataSyncService().commitSync((Item)item);
//					item.update0();
//				}
//				PlayerLog.obtainItem(playerId, nEnum, logDesc, item.getItemId(), configId, count);
//				if(ConfigItem.isEquip(configId)) {
//					//获得装备任务检测
////					TaskConType.checkTaskEquip(playerId,configId,count);
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
////				itemMap.put(item.getItemId(), item);
////				Context.getDataSyncService().commitAddSync(item);
//				item.save0();
//				PlayerLog.obtainItem(playerId, nEnum, logDesc, item.getItemId(), configId, 1);
//				items.add(item);
//			}
//			return items;
//		}else if (ConfigItem.isRune(configId)) {
//			//背包加入符文
//			List<IItem> items = Lists.newArrayList();
//			for (int i = 0; i < count; i++) {
//				Rune item = Rune.create(playerId, configId);
////				item.save0();
//				process.insert(item);
//				PlayerLog.obtainItem(playerId, nEnum, logDesc, item.getItemId(), configId, 1);
//				items.add(item);
//				ConfigRune config= ConfigManager.get(ConfigRune.class, configId);
////				TaskConType.checkTaskRune(playerId,config.getQuality());
//			}
//			return items;
//		}
//		else if (ConfigItem.isHero(configId)) {
//			//英雄
//			Player player = Context.getPlayerService().getPlayer(playerId);
//			List<IItem> items = Lists.newArrayList();
//			for (int i = 0; i < count; i++) {
//				HeroService heroService = InstanceFactory.getBean(HeroService.class);
//				IItem item = heroService.addHero(player, configId, nEnum);
//				items.add(item);
//				PlayerLog.obtainItem(playerId, nEnum, logDesc,item.getItemId(), configId, 1);
//			}
//			return items;
//		}
//		return null;
//	}
//
//	public List<IItem> addNewItem(Player player, List<Wealth> wealth, NatureEnum nEnum) {
//		Map<Integer, Integer> items = wealth.stream().collect(Collectors.toMap(Wealth::getConfigId, Wealth::getNum));
//		return addNewItem(player.getId(), items, nEnum, nEnum.getDesc());
//		
//	}
//	
//	/**
//	 * 根据配置id删除指定道具
//	 * @param playerId
//	 * @param items
//	 * @param nEnum
//	 * 
//	 * 
//	 * @param logDesc
//	 * @return
//	 */
//	public boolean deductItem(long playerId, Map<Integer, Integer> items, NatureEnum nEnum, String logDesc) {
//		List<IItem> itemList = Lists.newArrayList();
//		for (Entry<Integer, Integer> entry : items.entrySet()) {
//			IItem item = deductItem(playerId, entry.getKey(), entry.getValue(), nEnum, logDesc);
//			if (item != null) {
//				itemList.add(item);
//			}
//		}
//		sendItemChanged(playerId, itemList);
//		return true;
//	}
//
//	public void sendItemChanged(long playerId, List<IItem> itemList) {
//		if (itemList.isEmpty()) {
//			return;
//		}
//		List<IItem> updateItems = Lists.newArrayList();
//		List<IItem> deleteItems = Lists.newArrayList();
//		for (IItem item : itemList) {
//			if (item.getCount() > 0) {
//				updateItems.add(item);
//				continue;
//			}
//			deleteItems.add(item);
//		}
//		responseUpdateItemList(playerId, updateItems);
//		responseDeleteItemList(playerId, deleteItems);
//	}
//
//	/**
//	 * 根据唯一id, 减少 属性/道具/装备/神装/英雄碎片/符文
//	 * @param playerId 玩家id
//	 * @param configId 配置id
//	 * @param count 消耗数量
//	 * @param nEnum 资源消耗枚举
//	 * @param logDesc 补充信息
//	 * @return 执行结果
//	 */
//	private IItem deductItem(long playerId, int configId, int count, NatureEnum nEnum, String logDesc) {
//		if (count <= 0) {
//			return null;
//		}
//		if (ConfigItem.isPlayerProperty(configId)) {
//			//return Context.getPlayerService().deductProperty(playerId, configId, count, nEnum, logDesc);
//			Context.getResourceAssist().cost(playerId, configId, count, nEnum, logDesc);
//		}else if (ConfigItem.isItem(configId) || ConfigItem.isHeroPatch(configId) || ConfigItem.isEquip(configId)) {
//			//背包减少普通道具
//			IItem item = getItemByConfigId(playerId, configId);
//			deductItem(playerId,item,count,nEnum,logDesc);
//			return item;
//		}
//		else if (ConfigItem.isGodEquip(configId)) {
//			//TODO somthing...
//		}
//		else if (ConfigItem.isRune(configId)) {
//			//TODO somthing...
//		}
//		return null;
//	}
//
//	/**
//	 * 背包减少普通道具
//	 * @param playerId 玩家id
//	 * @param item     扣除物品
//	 * @param count    扣除数量
//	 * @param nEnum    使用来源
//	 * @param logDesc  其他描述
//	 * @return 扣除结果
//	 */
//	public boolean deductItem(long playerId, IItem item,int count, NatureEnum nEnum, String logDesc) {
//		if (item != null) {
//			int curCount = item.deductCount(count);
////				Context.getDataSyncService().commitSync((Item)item);
//			item.update0();
////			if (curCount > 0) {
////				updateItemList.add(item);
////			} else {
////				deleteItemList.add(item);
////			}
//			//记录日志
////			PlayerLog.obtainItem(playerId, nEnum, logDesc, item.getItemId(), item.getConfigId(), count);
//		}
//		return true;
//	}
//	
//	/**
//	 * 根据唯一id删除物品
//	 * @param playerId
//	 * @param itemId
//	 * @param nEnum
//	 * @param logDesc
//	 * @return
//	 */
//	public boolean deductItem(long playerId, long itemId, NatureEnum nEnum, String logDesc) {
//		return deductItem(playerId, Lists.newArrayList(itemId), nEnum, logDesc);
//	}
//	
//	/***
//	 * 根据唯一id列表删除道具
//	 * @param playerId
//	 * @param items 道具ID
//	 * @param nEnum
//	 * @param logDesc
//	 * @return
//	 */
//	public boolean deductItem(long playerId, List<Long> items, NatureEnum nEnum, String logDesc) {
//		List<IItem> removeItems = Lists.newArrayList();
//		for (long itemId : items) {
//			IItem item = getItem(playerId, itemId);
//			removeItems.add(item);
//		}
//		return deductItem(playerId, removeItems, nEnum);
//	}
//
//	public boolean deductItem(long playerId, List<IItem> items, NatureEnum nEnum) {
//		for (IItem item : items) {
//			deductItem(playerId, item, item.getCount(), nEnum, nEnum.getDesc());
//		}
//		sendItemChanged(playerId, items);
//		return true;
//	}
//	
//	/**
//	 * 根据唯一id删除指定道具
//	 * @param playerId 玩家id
//	 * @param itemId 道具id
//	 * @param count 数量
//	 * @param nEnum 资源枚举用于日志
//	 * @param logDesc 其他额外描述
//	 * @return
//	 */
//	public boolean deductItem(long playerId, long itemId, int count, NatureEnum nEnum, String logDesc) {
//		IItem item = getItem(playerId, itemId);
//		if (item == null) {
//			return false;
//		}
//		deductItem(playerId, item.getConfigId(), count, nEnum, logDesc);
//		List<IItem> updateItems = Lists.newArrayList();
//		List<IItem> deleteItems = Lists.newArrayList();
//		if (item.getCount() > 0) {
//			updateItems.add(item);
//		} else {
//			deleteItems.add(item);
//		}
//		responseUpdateItemList(playerId, updateItems);
//		responseDeleteItemList(playerId, deleteItems);
//		return true;
//	}
//
//	public IItem getItem(long playerId, long itemId) {
//		List<IItem> items = getItems(playerId);
//		for (IItem item : items) {
//			if (item.getItemId() == itemId) {
//				return item;
//			}
//		}
//		return null;
//	}
//	/**
//	 * 根据类型获取物品列表
//	 * @param playerId
//	 * @param type
//	 * @return
//	 */
//	public Collection<IItem> getItemsByType(long playerId, PropsSuperTypeEnum type) {
//		return getItemsByType(playerId, type.getType());
//	}
//
//	/**
//	 * 获取装备
//	 *
//	 * @param player
//	 * @param configId
//	 * @return
//	 */
//	public Optional<IItem> getEquip(Player player, int configId) {
//		Collection<IItem> equips = getItemsByType(player.getId(), PropsSuperTypeEnum.Equip);
//		return equips.stream().filter(e -> e.getConfigId() == configId).findAny();
//	}
//
//	/**
//	 * 判断玩家道具是否充足,充足则扣除道具
//	 * @param playerId 玩家id
//	 * @param costMap 需要扣除的道具
//	 * @param nEnum 资源类型
//	 * @param desc 描述
//	 */
//	public boolean enoughAndDeductItem(long playerId, Map<Integer,Integer> costMap, NatureEnum nEnum, String desc){
//		if (enoughItem(playerId, costMap)){
//			return deductItem(playerId, costMap, nEnum, desc);
//		}
//		return false;
//	}
//
//	public boolean enoughAndDeductItem(long playerId, Map<Integer,Integer> costMap, NatureEnum nEnum) {
//		return enoughAndDeductItem(playerId, costMap, nEnum, nEnum.getDesc());
//	}
	
}
