package org.coral.server.game.helper;

/**
 * 资源类型枚举
 * @auth Jeremy
 * @date 2020年8月20日下午5:49:39
 */
public enum ResourceType {
	
	/** 属性 */
	Property(1),
	/** 道具 */
	Item(2),	
	/** 装备 */
	Equip(3),	
	/** 神装 */
	GodEquip(4),
	/** 英雄碎片 */
	HeroPatch(5),
	/** 符文 */
	Rune(6),
	/** 精灵 */
	Spirit(7),
	/** 英雄 */
	Hero(9),
	;
	private int type;
	private ResourceType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	

}
