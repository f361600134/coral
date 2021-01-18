package org.coral.server.game.module.battle.report;

import java.util.List;

import org.coral.server.game.module.battle.domain.BattleTeam;

import com.google.common.collect.Lists;

/**
 * 战报
 *
 */
public class BattleReport {

    private int id;

    private int battleType;
    
    private int totalRound;

    private BattleTeam defenceTeam;

    private BattleTeam attackTeam;

    private List<BattleRound> rounds = Lists.newArrayList();

    public BattleReport() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("战斗ID：%d", id));
        sb.append("battleType="+battleType+"\n");
        sb.append(String.format("，总回合数：%d\n", totalRound));
        sb.append("攻击方：\n");
        sb.append(attackTeam.toString() + "\n");
        sb.append("防御方：\n");
        sb.append(defenceTeam.toString() + "\n");
        sb.append("回合数据：\n");
        for (BattleRound round : rounds) {
            sb.append(round.toString() + "\n");
        }
        return sb.toString();
    }

	public int getBattleType() {
		return battleType;
	}

	public void setBattleType(int battleType) {
		this.battleType = battleType;
	}

	public BattleReport(int id,int battleType) {
        this.id = id;
        this.battleType = battleType;
    }

    public void addBattleRound(BattleRound round) {
        this.rounds.add(round);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalRound() {
        return totalRound;
    }

    public void setTotalRound(int totalRound) {
        this.totalRound = totalRound;
    }

    public BattleTeam getDefenceTeam() {
        return defenceTeam;
    }

    public void setDefenceTeam(BattleTeam defenceTeam) {
        this.defenceTeam = defenceTeam;
    }

    public BattleTeam getAttackTeam() {
        return attackTeam;
    }

    public void setAttackTeam(BattleTeam attackTeam) {
        this.attackTeam = attackTeam;
    }

    public List<BattleRound> getRounds() {
        return rounds;
    }

    public void setRounds(List<BattleRound> rounds) {
        this.rounds = rounds;
    }

}
