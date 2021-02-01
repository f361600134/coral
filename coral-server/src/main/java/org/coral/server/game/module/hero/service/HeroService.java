package org.coral.server.game.module.hero.service;

import java.util.Collection;

import org.coral.server.game.helper.ResourceType;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.module.hero.domain.Hero;
import org.coral.server.game.module.hero.domain.HeroDomain;
import org.coral.server.game.module.hero.manager.HeroManager;
import org.coral.server.game.module.resource.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService implements IHeroService, IResourceService{
	
	private static final Logger log = LoggerFactory.getLogger(HeroService.class);
	
	@Autowired private HeroManager heroManager;

	@Override
	public int resType() {
		return ResourceType.Hero.getType();
	}

	@Override
	public boolean checkAdd(long playerId, Integer configId, Integer count) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return false;
		}
		return domain.checkAdd(count);
	}

	@Override
	public boolean checkEnough(long playerId, Integer configId, Integer count) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return false;
		}
		return domain.checkEnough(configId, count);
	}

	@Override
	public void reward(long playerId, Integer configId, Integer count, NatureEnum nEnum) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		domain.reward(playerId, configId, count);
		Collection<Hero> heros = domain.getHerosByConfigId(configId);
		log.info("=======>heros:{}", heros);
	}

	@Override
	public void cost(long playerId, Integer configId, Integer count, NatureEnum nEnum) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		domain.cost(configId, count);
	}

	@Override
	public void cost(long playerId, Long uniqueId, NatureEnum nEnum) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return;
		}
		domain.cost(uniqueId);
	}

	@Override
	public Hero getHero(long playerId, long heroId) {
		HeroDomain domain = heroManager.getDomain(playerId);
		if (domain == null) {
			return null;
		}
		return domain.getHero(heroId);
	}

}
