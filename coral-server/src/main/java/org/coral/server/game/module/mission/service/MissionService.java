package org.coral.server.game.module.mission.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.module.mission.domain.MainMissionDomain;
import org.coral.server.game.module.mission.type.MainMission;
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
	private final static Map<Long, MainMissionDomain> PLAYER_DOMAINS = Maps.newConcurrentMap();
	
	@Autowired private DataProcessorAsyn process;
	
	/**
	 * 获取玩家背包
	 */
	public MainMissionDomain getDomain(long playerId) {
		MainMissionDomain domain = PLAYER_DOMAINS.get(playerId);
		if (domain == null) {
			//List<MainMission> missions = process.selectByIndex(MainMission.class, new Object[] {playerId});
//			domain = new MissionDomain();
//			PLAYER_DOMAINS.put(playerId, domain);
		}
		return domain;
	}

}
