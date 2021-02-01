package org.coral.server.game.module.hero.service;

import org.coral.server.game.module.hero.domain.Hero;

public interface IHeroService {
	
	/**
	 * 通过武将id获取武将
	 * @param heroId
	 * @return
	 */
	public Hero getHero(long playerId, long heroId);

}
