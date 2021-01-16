package org.coral.server.game.module.hero.service;

import org.coral.server.game.helper.ResourceType;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.module.hero.domain.HeroDomain;
import org.coral.server.game.module.hero.manager.HeroManager;
import org.coral.server.game.module.resource.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
<<<<<<< HEAD
public class HeroService {
	
	
=======
public class HeroService implements IHeroService, IResourceService{
	
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
	}

	@Override
	public void cost(long playerId, Integer configId, Integer value, NatureEnum nEnum) {
		// TODO Auto-generated method stub
		
	}
>>>>>>> refs/remotes/origin/master

}
