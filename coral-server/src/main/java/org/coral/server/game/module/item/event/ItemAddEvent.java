package org.coral.server.game.module.item.event;

import org.coral.server.core.event.EventBase;

/**
 * 新增物品事件
 */
public class ItemAddEvent extends EventBase {

    private final int configId; //物品id
    private final int count;    //物品数量
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
    
    public static void main(String[] args) {
    	ItemAddEvent event = ItemAddEvent.create(1, 1, 1, 1);
    	System.out.println(event.name());
    	System.out.println(event.name);
	}
}
