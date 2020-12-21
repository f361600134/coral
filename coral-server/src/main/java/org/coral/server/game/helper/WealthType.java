package org.coral.server.game.helper;

import java.util.Map;

import org.coral.server.game.module.player.domain.Player;

public enum WealthType {

	/** 元宝 */
	Ingot(ResourceType.Property, 100001),
	/** 铜币 */
	Copper(ResourceType.Property, 100002),
	/** 主角经验 */
	Exp(ResourceType.Property, 100003),
	/** 英雄经验 */
	ExpHero(ResourceType.Property, 100004),
	/** 召唤积分 */
	SummonIntegral(ResourceType.Property, 100005),
	/** 友情点 */
	FriendShipPoint(ResourceType.Property, 100006),
	/** 先知结晶 */
	ProphetCrystal(ResourceType.Property, 100007),
	/** 星命卡牌 */
	ProphetCard(ResourceType.Property, 100008),
	/** 远航情报 */
	Information(ResourceType.Property, 100009),
	/** 熔炼值 */
	Melting(ResourceType.Property, 100010),
	/** 探宝积分 */
	TreasureIntegral(ResourceType.Property, 100011),
	/** 公会贡献 */
	GuildContribution(ResourceType.Property, 100012),
	/** 神格 */
	Godhood(ResourceType.Property, 100013),
	/** 日常活跃度 */
	ActiveDay(ResourceType.Property, 100014),
	/** 周常活跃度 */
	ActiveWeek(ResourceType.Property, 100015),
	/** 烈阳晶石 */
	Pyroxene(ResourceType.Property, 100016),
	/** 竞技声望 */
	ArenaPrestige(ResourceType.Property, 100017),
	/** 竞技积分 */
	ArenaIntegral(ResourceType.Property, 100018),
	/** 征战勋章 */
	WarMedal(ResourceType.Property, 100019),
	/** 荣誉勋章 */
	HonorMedal(ResourceType.Property, 100020),
	/** 皮肤碎片 */
	SkinDebris(ResourceType.Property, 100021),
	/** 神装碎片 */
	GodDebris(ResourceType.Property, 100022),
	/** 冠军赛竞猜币 */
	ChampionshipCoin(ResourceType.Property, 100024),
	/** 冠军赛积分 */
	ChampionshipIntegral(ResourceType.Property, 100025),
	/** 生命药剂 */
	DivineHealth(ResourceType.Property, 100026),
	/** 驱散药剂 */
	DivineExorcism(ResourceType.Property, 100027),
	/** 秘宝钥匙 */
	DivineSecretKey(ResourceType.Property, 100028),
	/** 冠军赛筹码 */
	ChampionChip(ResourceType.Property, 100024),
	/** vip经验 */
	VipExp(ResourceType.Property, 100023),
	Item(ResourceType.Item, 0),
	Equip(ResourceType.Equip, 0),
	GodEquip(ResourceType.GodEquip, 0),
	HeroPatch(ResourceType.HeroPatch, 0),
	Rune(ResourceType.Rune, 0),
	Spirit(ResourceType.Spirit, 0),
	Hero(ResourceType.Hero, 0),
	;

	private final ResourceType superType;
	// private final int superType;
	private final int subType;

	/**
	 * 资源类型 ResourceType
	 * 
	 * @param superType
	 *            父类型
	 * @param subType
	 *            子类型
	 */
	private WealthType(ResourceType superType, int subType) {
		this.superType = superType;
		this.subType = subType;
	}

	public ResourceType getSuperType() {
		return superType;
	}

	public int getSubType() {
		return subType;
	}
	
	public void add(Player player, int added) {
//		Map<Integer, Integer> propertiesaMap = player.getPropertieMap();
//		int value = propertiesaMap.getOrDefault(getSubType(), 0);
//		propertiesaMap.put(getSubType(), value+added);
		player.addProperties(getSubType(), added);
	}
	
	public boolean check(Player player, int value) {
//		Map<Integer, Integer> propertiesaMap = player.getPropertieMap();
//		return propertiesaMap.getOrDefault(getSubType(), 0) >= value;
		return player.checkProperties(getSubType(), value);
	}
	
}
