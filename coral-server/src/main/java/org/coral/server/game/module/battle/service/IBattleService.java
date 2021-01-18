package org.coral.server.game.module.battle.service;

public interface IBattleService {

	/**
	 * 	开始挑战
	 * @param attackId 攻击者id
	 * @param defencerId 防守者id
	 */
	public void fight(long attackId, long defencerId);
	
	
}
