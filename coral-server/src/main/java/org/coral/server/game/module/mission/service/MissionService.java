package org.coral.server.game.module.mission.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.orm.core.DataProcessorAsyn;
import org.coral.server.game.module.mission.domain.MainMission;
import org.coral.server.game.module.mission.domain.MissionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 
 * @author Jeremy
 *
 */
@Service
public class MissionService implements IMissionService{
	
	private static final Logger log = LogManager.getLogger(MissionService.class);
	
	/**玩家任务缓存*/
	private final static Map<Long, MissionDomain> PLAYER_DOMAINS = Maps.newConcurrentMap();
	
	@Autowired private DataProcessorAsyn process;
	
	/**
	 * 获取玩家背包
	 */
	public MissionDomain getDomain(long playerId) {
		MissionDomain domain = PLAYER_DOMAINS.get(playerId);
		if (domain == null) {
			List<MainMission> mission = process.selectByIndex(MainMission.class, new Object[] {playerId}, new Object[] {playerId});
			domain = new MissionDomain();
			PLAYER_DOMAINS.put(playerId, domain);
		}
		return domain;
	}

}
