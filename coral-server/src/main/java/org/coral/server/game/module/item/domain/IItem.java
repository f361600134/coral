package org.coral.server.game.module.item.domain;

import org.coral.server.core.server.IPersistence;
import org.coral.server.game.data.proto.PBBag;

/**
 * 物品抽象类
 * @author Jeremy
 */
public interface IItem extends IPersistence{

	/**
	 * 获取唯一id
	 */
	long getItemId();
	
	/**
	 * 获取配置id
	 * @return
	 */
	int getConfigId();
	
	/**
	 * 获取所属玩家id
	 * @return
	 */
	long getPlayerId();
	
	/**
	 * 增加数量, 返回最新数量
	 * @return
	 */
	public int getCount();

	default public int getQuality(){
		return 0;
	}
	
	/**
	 * 增加数量, 返回最新数量
	 * @return
	 */
	default public int addCount(int value) {
		return 0;
	}
	
	/**
	 * 扣除数量, 返回最新数量
	 * @return
	 */
	default public int deductCount(int value) {
		return 0;
	}
	
	/**
	 * 判断是否属于指定类型物品
	 * @return
	 */
	default public boolean isType(int type) {
		return ((getConfigId() / 100000) == type);
	}
	
	/**
	 * 转proto对象
	 * @return 数量为0. 不下发
	 */
	default public PBBag.ItemInfo toProto() {
		if(getCount() <= 0) return null;
		PBBag.ItemInfo.Builder builder = PBBag.ItemInfo.newBuilder();
		builder.setGuid(getItemId());
		builder.setNum(getCount());
		builder.setConfigId(getConfigId());
		return builder.build();
	}
	
}
