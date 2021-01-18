package org.coral.server.battle;

import org.coral.server.battle.domain.Battle;
import org.coral.server.game.module.battle.domain.BattleTeam;

public class TestBattle {
	
	public static void main(String[] args) {
		int battleType = 1;
		BattleTeam attackTeam = new BattleTeam();
		BattleTeam defenceTeam = new BattleTeam();
		int maxRounds = 5;
		Battle battle = Battle.create(battleType, attackTeam, defenceTeam, maxRounds);
		battle.start();
	}

}
