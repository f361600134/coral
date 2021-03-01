package org.coral.server.game.module.hero.manager;

import org.coral.server.core.server.AbstractModuleManager;
import org.coral.server.game.module.hero.domain.HeroDomain;
import org.springframework.stereotype.Component;

@Component
public class HeroManager extends AbstractModuleManager<HeroDomain>{
	
//	@Override
//	public HeroDomain getFromDb(long playerId) {
//		HeroDomain domain = new HeroDomain();
//		List<Hero> heros = process.selectByIndex(Hero.class, new Object[] {playerId});
//		domain.init(heros);
//		return domain;
//	}
	
}
