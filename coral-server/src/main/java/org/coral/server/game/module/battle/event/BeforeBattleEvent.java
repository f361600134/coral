package org.coral.server.game.module.battle.event;

import org.coral.server.core.event.BaseEvent;
import org.coral.server.game.module.battle.domain.Battle;

/**
 * 战斗开始前事件
 *
 * @author Klass
 * @date 2020/9/15 19:28
 */
public class BeforeBattleEvent extends BaseEvent {

    private Battle battle;

    public BeforeBattleEvent(Battle battle) {
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }

	@Override
	public String getEventId() {
		// TODO Auto-generated method stub
		return null;
	}

}
