package com.maya.game.modules.maill.event;

import com.maya.common.observer.Observer;
import com.maya.common.observer.Subscribe;
import com.maya.game.modules.halidom.service.HalidomService;
import com.maya.game.modules.maill.service.MailService;
import com.maya.game.modules.player.event.AfterLoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailObserver implements Observer {

    @Autowired
    private MailService service;

    /**
     * 登录事件
     * @param event
     */
    @Subscribe
    public void onAfterLogin(AfterLoginEvent event) {
        long playerId = event.getPlayer().getId();
        service.onLogin(playerId);
    }


}
