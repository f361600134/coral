package org.coral.server.game.module.battle.event;

import org.coral.server.core.event.BaseEvent;
import org.coral.server.game.module.battle.domain.BattleEntity;

/**
 * 战斗实体行动前事件
 *
 * @author Klass
 * @date 2020/9/10 13:55
 */
public class BeforeBattleEntityActionEvent extends BaseEvent {

    private BattleEntity battleEntity;

    public BeforeBattleEntityActionEvent(BattleEntity battleEntity) {
        this.battleEntity = battleEntity;
    }

    public BattleEntity getBattleEntity() {
        return battleEntity;
    }

}
