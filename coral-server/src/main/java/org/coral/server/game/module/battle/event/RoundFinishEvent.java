package org.coral.server.game.module.battle.event;

import org.coral.server.core.event.BaseEvent;
import org.coral.server.game.module.battle.domain.Battle;

/**
 * 回合结束事件
 */
public class RoundFinishEvent extends BaseEvent {

    private Battle battle;

    public RoundFinishEvent(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

}
