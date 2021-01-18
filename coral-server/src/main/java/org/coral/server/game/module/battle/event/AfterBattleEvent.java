package org.coral.server.game.module.battle.event;

import org.coral.server.core.event.BaseEvent;
import org.coral.server.game.module.battle.domain.Battle;

/**
 * @author Klass
 * @date 2020/11/2 16:49
 */
public class AfterBattleEvent extends BaseEvent {

    private Battle battle;

    public AfterBattleEvent(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

}
