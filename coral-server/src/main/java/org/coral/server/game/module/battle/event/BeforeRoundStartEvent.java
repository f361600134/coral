package org.coral.server.game.module.battle.event;

import org.coral.server.core.event.BaseEvent;
import org.coral.server.game.module.battle.domain.Battle;

/**
 * 回合开始前事件
 */
public class BeforeRoundStartEvent extends BaseEvent {

    private Battle battle;

    public BeforeRoundStartEvent(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

}
