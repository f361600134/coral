package org.coral.server.game.module.mission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.coral.server.game.data.proto.PBBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 抽象任务域, 实现任务业务
 * @author Jeremy
 *
 */
public class MissionHelper implements IMissionHelper {

	private static final Logger log = LoggerFactory.getLogger(MissionHelper.class);

	protected long playerId;

	private Map<Integer, EntityMission> missions;

	private Map<Integer, List<Integer>> missionConfigs = Maps.newHashMap();

	public MissionHelper() {
		this.missions = Maps.newHashMap();
		this.missionConfigs = Maps.newHashMap();
	}

	public void init(long playerId, Map<Integer,EntityMission> missions) {
		this.playerId = playerId;
		this.missions = missions;
		this.missionConfigs = Maps.newHashMap();
		for (EntityMission EntityMission : missions.values()) {
			//
			if(this.missionConfigs.containsKey(EntityMission.getCompleteType())){
				this.missionConfigs.get(EntityMission.getCompleteType()).add(EntityMission.getConfigId());
			}else{
				List<Integer> list = Lists.newArrayList();
				list.add(EntityMission.getConfigId());
				this.missionConfigs.put(EntityMission.getCompleteType(),list);
			}
		}
	}
	public EntityMission getMission(int configId) {
		return missions.get(configId);
	}

	public Map<Integer, EntityMission> getMissions() {
		return missions;
	}

	/**
	 * 任务进度
	 */
	public boolean progressMission(int progressDelta, EntityMission mission) {
		if(mission==null || mission.isActived() || mission.isRewarded())
			return false;

		int progress = mission.getProgress();
		progress += progressDelta;
		progress = Math.min(progress, mission.getCompleteValue());
		mission.setProgress(progress);

		if(mission.isNone() && this.isMissionCanComplete(mission.getConfigId()))
		{//状态为未完成且当前可以完成
			mission.setState(EntityMission.STATE_ACTIVED);
//			updateMissions.add(mission.getConfigId());
		}
		return true;
	}
	/**
	 * 任务是否满足完成条件
	 */
	@JSONField(serialize=false)
	private boolean isMissionCanComplete(int missionId)
	{
		EntityMission mission = this.missions.get(missionId);
		if(mission==null)
			return false;
		if(mission.getCompleteType()== MissionEnum.TYPE_DEFAULT.getType()
				&& mission.getProgress()<mission.getCompleteValue())
			mission.setProgress(mission.getCompleteValue());	//无完成条件的,直接完成

		return mission.getProgress()>=mission.getCompleteValue();
	}


	/**
	 * 判断全部是否领取, 全部领取则可以激活
	 * @return
	 */
	public boolean isAllReward() {
		for (EntityMission mission : missions.values()) {
			if (!mission.isRewarded()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否领取, 全部领取则可以激活
	 * @param configId
	 * @return
	 */
	public boolean isRewarded(int configId) {
		EntityMission mission = getMission(configId);
		return mission == null ? false : mission.isRewarded();
	}

	/**
	 * 判断是否激活
	 * @param configId
	 * @return
	 */
	public boolean isActived(int configId) {
		EntityMission mission = getMission(configId);
		return mission == null ? false : mission.isActived();
	}


	/////////////////////////////默认实现////////////////////

	public String toJson() {
		return JSON.toJSONString(missions);
	}

	public Collection<PBBag.PBMissionInfo> toProto() {
		List<PBBag.PBMissionInfo> colls = new ArrayList<PBBag.PBMissionInfo>();
		for (EntityMission EntityMission : missions.values()) {
			colls.add(EntityMission.toProto());
		}
		return colls;
	}

	/**
	 * 登陆：1
	 */
	@Override
	public boolean onLogin(long playerId, int loginDays) {
		try {
			//计算活动第几天
			boolean chg = false;
			//获取登陆任务
			List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_LOGIN.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission == null || mission.getCompleteValue() != loginDays)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onLogin error, e:", e);
			return false;
		}
	}

	/**
	 * 副本通关
	 */
	@Override
	public boolean onChapterPass(long playerId, int layer) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_CHAPTER_PASS.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() > layer)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onChapterPass error, e:", e);
			return false;
		}
	}

	/**
	 * 当玩家升级
	 * @param playerId
	 * @param level 等级
	 * @return
	 */
	public boolean onPlayerLevelUp(long playerId, int level) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_PLAYER_LEVEL.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() > level)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onPlayerLevelUp error, e:", e);
			return false;
		}
	}

	/**
	 * 当武将升级
	 */
	@Override
	public boolean onHeroLevelUp(long playerId, int level) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_HERO_LEVEL.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() > level)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onHeroLevelUp error, e:", e);
			return false;
		}
	}

	/**
	 * 献祭英雄
	 * @param playerId
	 * @param count
	 * @return
	 */
	@Override
	public boolean onDecomposeHero(long playerId, int count) {
		boolean chg = false;
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_HERO_DECOMPOSE.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/**
	 * 当高级召唤
	 */
	@Override
	public boolean onCallHigh(long playerId, int count) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_CALL_HIGH.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() != 0)
						continue;
					chg = progressMission(count, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onCallHigh error, e:", e);
			return false;
		}
	}

	/**
	 * 当競技場挑戰
	 */
	@Override
	public boolean onArenaFight(long playerId, int count) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_ARENA_FIGHT.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() != 0)
						continue;
					chg = progressMission(count, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onArenaFight error, e:", e);
			return false;
		}
	}

	/**
	 * 接取远航任务
	 */
	@Override
	public boolean onSailMission(long playerId, int count) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_SAIL_MISSION.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null)
						continue;
					chg = progressMission(count, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onSailMission error, e:", e);
			return false;
		}
	}

	/**
	 * 当快速作战
	 */
	@Override
	public boolean onChapterQuick(long playerId, int count) {
		boolean chg = false;
		//获取登陆任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_CHAPTER_QUICK.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteCondition() != 0)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/**
	 * 精灵商店购买
	 */
	@Override
	public boolean onShopSpiritBuy(long playerId, int shopType) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_SHOP_SPIRIT_BUY.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() != shopType)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onShopSpiritBuy error, e:", e);
			return false;
		}
	}

	/**
	 *参与公会副本
	 */
	@Override
	public boolean onFamilyChapter(long playerId, int count) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_CHAPTER.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() != 0)
						continue;
					chg = progressMission(count, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onFamilyChapter error, e:", e);
			return false;
		}
	}

	/**
	 *试练塔达到x层
	 */
	@Override
	public boolean onTowerPass(long playerId, int layer) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_TOWER_PASS.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() != layer)
						continue;
					chg = progressMission(layer, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onFamilyChapter error, e:", e);
			return false;
		}
	}

	/**
	 *战力达到x
	 */
	@Override
	public boolean onPower(long playerId, int power) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_POWER.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteValue() != 0)
						continue;
					chg = progressMission(power, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onFamilyChapter error, e:", e);
			return false;
		}
	}

	/**
	 *合成五星英雄
	 */
	@Override
	public boolean onHeroCompose(long playerId, int star) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_HERO_COMPOSE.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteValue() != star)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onFamilyChapter error, e:", e);
			return false;
		}
	}

	/**
	 *段位赛达到王者
	 */
	@Override
	public boolean onCompetitionKing(long playerId, int rank) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_COMPETITION_SUPER_ORDER.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteValue() != rank)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onFamilyChapter error, e:", e);
			return false;
		}
	}

	/**
	 *拥有x个x星武将
	 */
	@Override
	public boolean onHeroHave(long playerId, int star) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_HERO_HAVE.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteCondition() != star)
						continue;
					chg = progressMission(1, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onFamilyChapter error, e:", e);
			return false;
		}
	}

	/**
	 * 获得*件*品质装备
	 * @param playerId
	 * @param count
	 * @return
	 */
	@Override
	public boolean onEquipAward(long playerId, int quality, int count) {
		boolean chg = false;
		//获取登陆任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_EQUIP_GET.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteCondition() != quality)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/***
	 * 远航任务
	 * @param playerId
	 * @param color
	 * @return
	 */
	@Override
	public boolean onSailTask(long playerId, int color) {
		boolean chg = false;
		//获取登陆任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_SAIL_MISSION1.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteCondition() != color)
					continue;
				chg = progressMission(1, mission) || chg;
			}
		}
		return chg;
	}

	/**
	 *先知召唤
	 */
	@Override
	public boolean onCallProphet(long playerId, int count) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_CALL_PROPHET.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission == null)
						continue;
					chg = progressMission(count, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onFamilyChapter error, e:", e);
			return false;
		}
	}

	/**
	 *竞技场排名
	 */
	@Override
	public boolean onArenaRank(long playerId, int rank) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_ARENA_RANK.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteValue() != 0)
						continue;
					chg = progressMission(rank, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onArenaRank error, e:", e);
			return false;
		}
	}

	/**
	 * 竞技场积分
	 */
	@Override
	public boolean onArenaIntegral(long playerId, int integral) {
		try {
			boolean chg = false;
			//获取登陆任务
			Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_ARENA_INTEGRAL.getType());
			if(configs != null && !configs.isEmpty())
			{//有任务可以判断完成或记录进度
				for(Integer configId : configs){
					EntityMission mission = missions.get(configId);
					if(mission==null || mission.getCompleteValue() != 0)
						continue;
					chg = progressMission(integral, mission) || chg;
				}
			}
			return chg;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("onArenaIntegral error, e:", e);
			return false;
		}
	}

	/**赠送友情点*/
	@Override
	public boolean onFriendPoint(long playerId, int count) {
		boolean chg = false;
		//获取任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_FRIEND.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/**符文合成*/
	@Override
	public boolean onRuneCompose(long playerId, int count) {
		boolean chg = false;
		//获取任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_RUNE_COMPOSE.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}
	/**所有召唤*/
	@Override
	public boolean onCallAll(long playerId, int count) {
		boolean chg = false;
		//获取任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_CALL_ALL.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/**公会捐献*/
	@Override
	public boolean onGuildDonate(long playerId, int count) {
		boolean chg = false;
		//获取任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_DONATE.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteValue() != 0)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/**参加日常副本*/
	@Override
	public boolean onJoinDailyDungeon(long playerId, int count) {
		boolean chg = false;
		//获取任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_DAILY_MISSION.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}
	/**参与试练塔*/
	@Override
	public boolean onJoinTowerFight(long playerId, int count) {
		boolean chg = false;
		//获取任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_TOWER_DAILY.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission == null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	@Override
	public boolean onJoinEndlessFight(long playerId, int count) {
		return false;
	}

	/**合成装备*/
	@Override
	public boolean onEquipCompose(long playerId, int count) {
		boolean chg = false;
		//获取任务
		Collection<Integer> configs = missionConfigs.get(MissionEnum.TYPE_EQUIP_COMPOSE.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/**完成公会技能升级次数*/
	@Override
	public boolean onUpgradeGuildSkill(long playerId, int count) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_SKILL_UPLEVEL.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteValue() <= count)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}
	/**公会商店购买*/
	@Override
	public boolean onGuildShopBuy(long playerId, int shopType) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_SHOP_BUY.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
//				if(mission==null || mission.getCompleteCondition() != shopType)
				if(mission==null)
					continue;
				chg = progressMission(1, mission) || chg;
			}
		}
		return chg;
	}
	/***公会捐献金币*/
	@Override
	public boolean onGuildDonateGold(long playerId, int count) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_DONATE_GOLD.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteValue() != count)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}
	/**公会捐献钻石*/
	@Override
	public boolean onGuildDonateIngot(long playerId, int count) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_DONATE_INGOT.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteValue() != count)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}
	/**公会捐献至尊钻石*/
	@Override
	public boolean onGuildDonateMaxIngot(long playerId, int count) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_DONATE_MAXINGOT.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null || mission.getCompleteValue() != count)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}
	/**公会红包*/
	@Override
	public boolean onGuildRedEnvelope(long playerId, int count) {
		return false;
	}
	/**公会副本*/
	@Override
	public boolean onGuildDungeon(long playerId, int count) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_GUILD_DUNGEON_ADDBUFF.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}
	/**精灵商店刷新*/
	@Override
	public boolean onShopFlush(long playerId, int count) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_SHOP_SPIRIT_FRESHEN.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission==null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	/**高级幸运转盘*/
	@Override
	public boolean onTreasureHigh(long playerId, int count) {
		boolean chg = false;
		//获取任务
		List<Integer> configs = missionConfigs.get(MissionEnum.TYPE_TREASURE.getType());
		if(configs != null && !configs.isEmpty())
		{//有任务可以判断完成或记录进度
			for(Integer configId : configs){
				EntityMission mission = missions.get(configId);
				if(mission == null)
					continue;
				chg = progressMission(count, mission) || chg;
			}
		}
		return chg;
	}

	public Map<Integer, List<Integer>> getMissionConfigs() {
		return missionConfigs;
	}

	public void setMissionConfigs(Map<Integer, List<Integer>> missionConfigs) {
		this.missionConfigs = missionConfigs;
	}
}
