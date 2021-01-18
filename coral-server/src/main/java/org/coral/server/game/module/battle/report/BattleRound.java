package org.coral.server.game.module.battle.report;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

/**
 *  回合数据
 * @author Jeremy
 *
 */
public class BattleRound {

    private int round;

    private List<BattleAction> actions = Lists.newArrayList();

    public BattleRound() {

    }

    public BattleRound(int round) {
        this.round = round;
    }

    public BattleAction getLastBattleAction() {
        return Iterables.getLast(actions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("当前回合：" + round + "\n");
        for (BattleAction action : actions) {
            sb.append(action.toString() + "\n");
        }
        return sb.toString();
    }

    public void addAction(BattleAction action) {
        this.actions.add(action);
    }

    public int getRound() {
        return round;
    }

    public void setRound(byte round) {
        this.round = round;
    }

    public List<BattleAction> getActions() {
        return actions;
    }

    public void setActions(List<BattleAction> actions) {
        this.actions = actions;
    }

}
