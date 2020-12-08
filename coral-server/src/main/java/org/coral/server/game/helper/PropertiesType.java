package org.coral.server.game.helper;

import java.util.Map;

import org.coral.server.game.module.player.domain.Player;

public enum PropertiesType {

	/** 为止物品 */
	NONE(0),
	/** 元宝 */
	Ingot(100001),
	/** 铜币 */
	Copper(100002),
	/** 主角经验 */
	Exp(100003),
	/** 英雄经验 */
	ExpHero(100004),
	/** 召唤积分 */
	SummonIntegral(100005),
	/** 友情点 */
	FriendShipPoint(100006),
	/** 先知结晶 */
	ProphetCrystal(100007),
	/** 星命卡牌 */
	ProphetCard(100008),
	/** 远航情报 */
	Information(100009),
	/** 熔炼值 */
	Melting(100010),
	/** 探宝积分 */
	TreasureIntegral(100011),
	/** 公会贡献 */
	GuildContribution(100012),
	/** 神格 */
	Godhood(100013),
	/** 日常活跃度 */
	ActiveDay(100014),
	/** 周常活跃度 */
	ActiveWeek(100015),
	/** 烈阳晶石 */
	Pyroxene(100016),
	/** 竞技声望 */
	ArenaPrestige(100017),
	/** 竞技积分 */
	ArenaIntegral(100018),
	/** 征战勋章 */
	WarMedal(100019),
	/** 荣誉勋章 */
	HonorMedal(100020),
	/** 皮肤碎片 */
	SkinDebris(100021),
	/** 神装碎片 */
	GodDebris(100022),
	/** 冠军赛竞猜币 */
	ChampionshipCoin(100024),
	/** 冠军赛积分 */
	ChampionshipIntegral(100025),
	/** 生命药剂 */
	DivineHealth(100026),
	/** 驱散药剂 */
	DivineExorcism(100027),
	/** 秘宝钥匙 */
	DivineSecretKey(100028),
	/** 冠军赛筹码 */
	ChampionChip(100024),
	/** vip经验 */
	VipExp(100023),
	;

	private final int type;

	/**
	 * 资源类型 ResourceType
	 * 
	 * @param subType
	 *            子类型
	 */
	private PropertiesType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	/**
	 * 增加属性, 可正可负
	 * @param player
	 * @param added  
	 * @return void  
	 * @date 2020年9月7日下午2:59:24
	 */
	public void add(Player player, int added) {
		Map<Integer, Integer> propertiesaMap = player.getPropertieMap();
		int value = propertiesaMap.getOrDefault(getType(), 0);
		propertiesaMap.put(getType(), value+added);
	}
	
	/**
	 * 检测属性是否充足,必须为整数
	 * @param player
	 * @param value
	 * @return  
	 * @return boolean  
	 * @date 2020年9月7日下午2:59:51
	 */
	public boolean check(Player player, int value) {
		Map<Integer, Integer> propertiesaMap = player.getPropertieMap();
		return propertiesaMap.getOrDefault(getType(), 0) >= value;
	}
	
	/**
	 * 获取指定类型的属性枚举
	 * @param configId
	 * @return  
	 * @return PropertiesType  
	 * @date 2020年9月7日下午3:22:49
	 */
	public static PropertiesType getType(int configId) {
		for (PropertiesType pType : values()) {
			if (pType.getType() == configId) {
				return pType;
			}
		}
		return NONE;
	}
}
