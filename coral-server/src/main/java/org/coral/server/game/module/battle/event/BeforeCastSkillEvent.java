package org.coral.server.game.module.battle.event;

import org.coral.server.core.event.BaseEvent;
import org.coral.server.game.module.battle.domain.BattleSkill;

/**
 * 施放技能事件
 *
 * @author Klass
 * @date 2020/9/17 10:19
 */
public class BeforeCastSkillEvent extends BaseEvent {

    private BattleSkill skill;

    public BeforeCastSkillEvent(BattleSkill skill) {
        this.skill = skill;
    }

    public BattleSkill getSkill() {
        return skill;
    }

}
