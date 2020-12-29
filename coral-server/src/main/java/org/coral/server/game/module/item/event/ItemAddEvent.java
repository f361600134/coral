package org.coral.server.game.module.item.event;

import org.coral.server.core.event.PlayerEventBase;

/**
 * 新增物品事件
 */
public class ItemAddEvent extends PlayerEventBase {
	
	public static String ID = ItemAddEvent.class.getSimpleName();

    private final int configId; //物品id
    private int count;    //物品数量
    private final int quality;  //物品品质

    private ItemAddEvent(long playerId, int configId, int count, int quality) {
    	super(playerId);
        this.configId = configId;
        this.count = count;
        this.quality = quality;
    }

    public static ItemAddEvent create(long playerId, int configId, int count, int quality){
        return new ItemAddEvent(playerId, configId, count, quality);
    }

    public int getConfigId() {
        return configId;
    }

    public int getCount() {
        return count;
    }

    public int getQuality() {
        return quality;
    }
    
    public void setCount(int count) {
		this.count = count;
	}
    
}
