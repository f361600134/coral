package org.coral.server.battle;

import org.coral.server.battle.domain.Battle;
import org.coral.server.battle.domain.BattleHero2;
import org.coral.server.game.helper.config.PropertyLoader;
import org.coral.server.game.module.battle.domain.BattleTeam;
import org.coral.server.game.module.hero.domain.Hero;

public class TestBattle {
	
	public static void main(String[] args) {
		//战斗依赖
		try {
			PropertyLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int battleType = 1;
		BattleTeam attackTeam = createAttack();
		BattleTeam defenceTeam = createDefence();
		int maxRounds = 10;
		Battle battle = Battle.create(battleType, attackTeam, defenceTeam, maxRounds);
		battle.start();
	}
	
	/**
	 *	构建攻击方
	 * @return
	 */
	public static BattleTeam createAttack() {
		BattleTeam team = new BattleTeam();
		team.addBattleEntity(BattleHero2.create(team, new Hero(1, 1, 90001)));
		team.addBattleEntity(BattleHero2.create(team, new Hero(1, 2, 90002)));
		team.addBattleEntity(BattleHero2.create(team, new Hero(1, 3, 90003)));
		return team;
	}
	
	/**
	 * 	构建防守方
	 * @return
	 */
	public static BattleTeam createDefence() {
		BattleTeam team = new BattleTeam();
		team.addBattleEntity(BattleHero2.create(team, new Hero(2, 1, 90001)));
		team.addBattleEntity(BattleHero2.create(team, new Hero(2, 2, 90002)));
		team.addBattleEntity(BattleHero2.create(team, new Hero(2, 3, 90003)));
		return team;
	}
	
}
