package org.coral.server.game.module.battle.assist;

public class BattleDefine {
	
	/**
	 * 攻击方
	 */
	public static byte ATTACK_SIDE = 1;

	/**
	 * 防御方
	 */
	public static byte DEFENCE_SIDE = 2;
	
	/**
	 * 战斗实体类型
	 */
	public interface BattleEntityType {
		/**
		 * 英雄
		 */
		byte HERO = 1;

		/**
		 * 怪物
		 */
		byte MONSTER = 2;

		/**
		 * 精灵
		 */
		byte SPIRIT = 3;

		/**
		 * 神器
		 */
		byte ARTIFACT = 4;
	}

}
