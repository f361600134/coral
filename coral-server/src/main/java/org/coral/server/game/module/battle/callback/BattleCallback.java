package org.coral.server.game.module.battle.callback;

import org.coral.server.game.module.battle.domain.Battle;

/**
 * 战斗回调
 *
 * @author Klass
 * @date 2020/8/12 17:43
 */
public interface BattleCallback {

    /**
     * 战斗开始前执行
     *
     * @param battle
     */
    default void onBeforeBattle(Battle battle) {

    }

    /**
     * 战斗结束后执行
     *
     * @param battle
     */
    void onAfterBattle(Battle battle);

}
