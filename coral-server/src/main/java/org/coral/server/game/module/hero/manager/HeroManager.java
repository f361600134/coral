package org.coral.server.game.module.hero.manager;

import java.util.List;
import java.util.Map;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.module.hero.domain.Hero;
import org.coral.server.game.module.hero.domain.HeroDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

@Component
public class HeroManager {
	
	@Autowired private DataProcessorAsyn process;
	
	/**玩家武将缓存*/
	private final static Map<Long, HeroDomain> PLAYER_DOMAINS = Maps.newConcurrentMap();
	
	/**
	 * 获取玩家背包
	 */
	public HeroDomain getDomain(long playerId) {
		HeroDomain domain = PLAYER_DOMAINS.get(playerId);
		if (domain == null) {
			List<Hero> heros = process.selectByIndex(Hero.class, new Object[] {playerId});
			domain = new HeroDomain();
			domain.init(heros);
			PLAYER_DOMAINS.put(playerId, domain);
		}
		return domain;
	}
	
}
