package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigItem;
import org.coral.server.utils.FileUtilitys;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigItemMgr {

    private static final Log LOGGER = LogFactory.getLog(ConfigItemMgr.class);
	
	private static String filename = ServerConstant.JsonPath+"ConfigItem.json";
	
	private volatile static Map<Integer, ConfigItem> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigItem> list = JSON.parseArray(content, ConfigItem.class);
		
		Map<Integer, ConfigItem> temps = Maps.newHashMap();
		for(ConfigItem config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigItem getConfig(int id) {
		ConfigItem data = maps.get(id);
		if(data==null)
		{
			LOGGER.error(String.format("shit! ce hua config error in ConfigItem! fuck him! id=%d", id));
		}
		return data;
	}
	
	/////////UserDefine Begin///////////
	
	public static final int COMPOSE = 3;
	public static final int DECOMPOSE = 4;
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
//	public static boolean isHeroPatch(int configId) {
//		return ConfigHeroPatchMgr.exist(configId);
//	}
//	
//	public static boolean isGodEquip(int configId) {
//		return ConfigGodEquipMgr.exist(configId);
//	}
//	
//	public static boolean isEquip(int configId) {
//		return ConfigEquipMgr.exist(configId);
//	}
	
	public static boolean isItem(int configId) {
		return ConfigItemMgr.exist(configId);
	}
	
//	public static boolean isPlayerProperty(int configId) {
//		return ConfigPlayerPropertyMgr.exist(configId);
//	}
//	
//	public static boolean isHero(int configId) {
//		return ConfigHeroMgr.exist(configId);
//	}
//	
//	public static boolean isRune(int configId) {
//		return ConfigRuneMgr.exist(configId);
//	}
	
//	/**
//	 * 是否可以添加的物品
//	 * @param itemId
//	 * @return
//	 */
//	public static boolean canAddItem(int configId) {
//		//玩家属性
//		if(isPlayerProperty(configId))
//			return true;
//		//普通道具或碎片
//		if (isItem(configId) || isHeroPatch(configId) || isEquip(configId)) {
//			return true;
//		}
//		//神装
//		if (isGodEquip(configId)) {
//			return true;
//		}
//		//符文库
//		if (isRune(configId)) {
//			return true;
//		}
//		//英雄
//		if (isHero(configId)) {
//			return true;
//		}
//		return false;
//	}
	
//	/**
//	 * 通过名字获取配置ID
//	 * @param name
//	 * @return
//	 */
//	public static int getConfigId(String name) {
//		if(StringUtils.isBlank(name))
//			return 0;
//		for(ConfigItem data:maps.values()) {
//			if(name.equals(data.getName()))
//				return data.getID();
//		}
//		return 0;
//	}
//	/**
//	 * 获取物品简要信息
//	 * @return
//	 */
//	public static List<PropsTypeEnumVO> getSimpleInfo() {
//		List<PropsTypeEnumVO> list = new ArrayList<PropsTypeEnumVO>();
//		for (ConfigItem data : maps.values()) {
//			PropsTypeEnumVO vo = new PropsTypeEnumVO();
//			vo.setName(data.getName());
//			vo.setType(data.getID());
//			list.add(vo);
//		}
//		return list;
//	}
//	
//	/**
//	 * 通过物品名称获取id
//	 * @param name
//	 * @return
//	 */
//	public static int getItemConfigId(String name) {
//		int configId=0;
//		//玩家属性特殊, 不需要返回道具
//		configId=ConfigPlayerPropertyMgr.getConfigId(name);
//		if(configId>0)
//			return configId;
//		//普通道具或碎片
//		configId=ConfigItemMgr.getConfigId(name);
//		if(configId>0)
//			return configId;
//		configId=ConfigHeroPatchMgr.getConfigId(name);
//		if(configId>0)
//			return configId;
//		configId=ConfigEquipMgr.getConfigId(name);
//		if(configId>0)
//			return configId;
//		//神装库加入神装
//		configId=ConfigGodEquipMgr.getConfigId(name);
//		if(configId>0)
//			return configId;
//		//符文库加入碎片
//		configId=ConfigRuneMgr.getConfigId(name);
//		if(configId>0)
//			return configId;
//		//英雄
//		configId=ConfigHeroMgr.getConfigId(name);
//		return configId;
//	}
//	
//	/**
//	 * 获取物品名
//	 * @param configId
//	 * @return
//	 */
//	public static String getItemName(int configId) {
//		//玩家属性特殊, 不需要返回道具
//		if(ConfigItemMgr.isPlayerProperty(configId))
//			return ConfigPlayerPropertyMgr.getConfig(configId).getName();
//		//普通道具或碎片
//		if (ConfigItemMgr.isItem(configId)){
//			return ConfigItemMgr.getConfig(configId).getName();
//		}
//		if(ConfigItemMgr.isHeroPatch(configId)) {
//			return ConfigHeroPatchMgr.getConfig(configId).getName();
//		}
//		if(ConfigItemMgr.isEquip(configId)) {
//			return ConfigEquipMgr.getConfig(configId).getName();
//		}
//		//神装库加入神装
//		if (ConfigItemMgr.isGodEquip(configId)) {
//			return ConfigGodEquipMgr.getConfig(configId).getName();
//		}
//		//符文库加入碎片
//		if (ConfigItemMgr.isRune(configId)) {
//			return ConfigRuneMgr.getConfig(configId).getName();
//		}
//		//英雄
//		if (ConfigItemMgr.isHero(configId)) {
//			return ConfigHeroMgr.getConfig(configId).getName();
//		}
//		return "UNKNOWN";
//	}

	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	public static  Map<Integer, ConfigItem> maps(){
		return maps;
	}
	/////////UserDefine End/////////////
	
}
