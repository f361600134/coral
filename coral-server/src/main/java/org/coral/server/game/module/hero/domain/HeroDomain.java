package org.coral.server.game.module.hero.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.coral.server.core.server.AbstractModuleMultiDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeroDomain extends AbstractModuleMultiDomain<Hero>{
	
	/**
	 * 武将数量最大限制, 虽然不限制, 单位了避免问题,这里限制总数量
	 */
	private static final int LIMIT = 999;
	
	private static final Logger log = LoggerFactory.getLogger(HeroDomain.class);
	
	
	public HeroDomain() {
	}
	
	/**
	 * 获取武将列表, 通过配置id
	 * @return
	 */
	public Collection<Hero> getHerosByConfigId(int configId){
		return beanMap.values().stream()
		.filter(hero -> hero.getConfigId() == configId)
		.collect(Collectors.toList());
	}
	
	/**
	 *	检测是否增加武将
	 * @return 数量小于最大限制, 则表示可以增加武将
	 */
	public boolean checkAdd(int count) {
		return (beanMap.keySet().size() + count) > LIMIT;
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
		Hero hero = null;
		//	生成一个武将,需要唯一id
		for (int i = 0; i < count; i++) {
			hero = Hero.create(playerId, configId);
			beanMap.put(hero.getId(), hero);
		}
	}
	
	/**
	 *	根据配置消耗指定数量的武将, 一般来说不建议直接通过配置id直接扣除指定数量武将
	 *	所有扣除操作, 必须由玩家选择指定武将去做扣除, 为了兼容
	 * @param configId
	 * @param count
	 */
	public void cost(int configId, int count) {
//		Iterator<Entry<Long, Hero>> iterator = heroMap.entrySet().iterator();
//		while(iterator.hasNext()) {
//			Entry<Long, Hero> entry = iterator.next();
//			if (entry.getValue().getConfigId() != configId) {
//				continue;
//			}
//			iterator.remove();
//			count --;
//			if (count == 0) {
//				break;
//			}
//		}
		throw new UnsupportedOperationException("武将删除操作, 必须由玩家指定id的武将, 才支持删除");
	}
	
	/**
	 *	消耗消耗指定id的武将
	 * @param configId
	 * @param count
	 */
	public void cost(long id) {
		beanMap.remove(id);
	}
	
	/**
	 *	消耗消耗指定id的武将
	 * @param configId
	 * @param count
	 */
	public Hero getHero(long id) {
		return beanMap.get(id);
	}

}
