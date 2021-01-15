package org.coral.server.game.module.hero.domain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

public class HeroDomain {
	
	/**
	 * 武将数量最大限制, 虽然不限制, 单位了避免问题,这里限制总数量
	 */
	private static final int LIMIT = 999;
	
	private static final Logger log = LoggerFactory.getLogger(HeroDomain.class);
	
	private final Map<Long, Hero> heroMap;
	
	public HeroDomain() {
		this.heroMap = Maps.newHashMap();
	}
	
	public void init(Collection<Hero> heros) {}
	
	/**
	 * 获取武将列表, 通过配置id
	 * @return
	 */
	public Collection<Hero> getHerosByConfigId(int configId){
		return heroMap.values().stream()
		.filter(hero -> hero.getConfigId() == configId)
		.collect(Collectors.toList());
	}
	
	/**
	 *	检测是否增加武将
	 * @return 数量小于最大限制, 则表示可以增加武将
	 */
	public boolean checkAdd(int count) {
		return (heroMap.keySet().size() + count) > LIMIT;
	}
	
	/**
	 *	检测武将是否满足
	 * @return 
	 */
	public boolean checkEnough(int configId, int count) {
		return getHerosByConfigId(configId).size() >= count;
	}
	
	/**
	 *	 奖励一个武将, 增加进武将库
	 * @param configId
	 * @param count
	 */
	public void reward(long playerId, int configId, int count) {
		//	生成一个武将,需要唯一id
		Hero hero = Hero.create(playerId, configId);
		heroMap.put(hero.getId(), hero);
	}
	
}
