package org.coral.server.game.module.item.event;

import org.coral.server.core.event.EventSubscribe;
import org.coral.server.game.module.item.service.ItemService;
import org.coral.server.game.module.player.event.PlayerAfterLoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.common.eventbus.Subscribe;

@EventSubscribe
public class ItemObserver {

    @Autowired
    ItemService itemService;

    /**
     * 登录事件
     * @param event
     */
    @Subscribe
    public void onAfterLogin(PlayerAfterLoginEvent event) {
        itemService.onLogin(event.getPlayerId());
    }


}
