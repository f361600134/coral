package org.coral.server.game.module.mission.domain;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.game.module.mission.type.IMission;

public class MainMissionDomain {
	
	private static final Logger log = LogManager.getLogger(MainMissionDomain.class);

	//主线任务
	private Map<Integer, IMission> missions;
	
	
	
	
//	private DbMissionTotal dbInfo;
	
//	private List<Mission> updateMissions;
//	
//	//锦囊任务
//	private Map<Integer, StrategyMission> strategyMissions;
//
//	//天赋任务
//	private Map<Integer, TalentMission> talentMissions;
//	
//	public MissionDomain(PlayerContext player) {
//		this.player = player;
//		this.updateMissions = Lists.newArrayList();
//	}
//	
//	/**
//	 * 首次创建时初始化
//	 */
//	public void initOnCreate() {
//		if(this.dbInfo==null)
//			this.dbInfo = this.createDbMissionTotal();
//		
//		//普通任务
//		if(this.normalMissions==null)
//			this.normalMissions = Maps.newHashMap();
//		Collection<ConfigMission> missions = ConfigMissionMgr.getAllMissions();
//		for(ConfigMission config : missions)
//		{
//			if(this.normalMissions.containsKey(config.getID()))
//				continue;
//			Mission mission = new Mission(config.getID());
//			this.normalMissions.put(config.getID(), mission);
//			this.recvMission(config);
//		}
//		//锦囊任务
//		if(this.strategyMissions==null)
//			this.strategyMissions = Maps.newHashMap();
//		List<Integer> mids = ConfigStrategyMgr.getMissionList();
//		for(Integer mid : mids)
//		{
//			if(this.strategyMissions.containsKey(mid))
//				continue;
//			StrategyMission mission = new StrategyMission(mid);
//			this.strategyMissions.put(mid, mission);
//			ConfigStrategy config = ConfigStrategyMgr.getConfig(mid);
//			this.recvStrategyMission(config);
//		}
//		//天赋任务
//		if(this.talentMissions==null)
//			this.talentMissions = Maps.newHashMap();
//		Collection<ConfigTalentTask> talentMissions = ConfigTalentTaskMgr.getMissionList();
//		for(ConfigTalentTask config : talentMissions)
//		{
//			if(this.talentMissions.containsKey(config.getID()))
//				continue;
//			TalentMission mission = new TalentMission(config.getID());
//			this.talentMissions.put(config.getID(), mission);
//			this.recvTalentMission(config);
//		}
//	}
//	/**
//	 * 非首次创建时初始化
//	 */
//	public void initFromDb(DbMissionTotal dbInfo) {
//		this.dbInfo = dbInfo;
//		
//		//普通任务
//		if(this.normalMissions==null)
//			this.normalMissions = Maps.newHashMap();
//		if(dbInfo!=null && StringUtilitys.isNotNullOrZeroLenght(dbInfo.getNormal()))
//		{
//			List<Mission> missions = JSONObject.parseObject(dbInfo.getNormal(), new TypeReference<List<Mission>>(){});
//			for(Mission mission : missions)
//			{
//				mission.init();
//				this.normalMissions.put(mission.getId(), mission);
//				
//				ConfigMission config = ConfigMissionMgr.getConfig(mission.getId());
//				if(mission.isNoRecv() && config.getDuration()==2)
//					this.recvMission(config);		//重新接取每日任务
//			}
//		}
//		//锦囊任务
//		if(this.strategyMissions==null)
//			this.strategyMissions = Maps.newHashMap();
//		if(dbInfo!=null && StringUtilitys.isNotNullOrZeroLenght(dbInfo.getStrategy()))
//		{
//			List<StrategyMission> missions = JSONObject.parseObject(dbInfo.getStrategy(), new TypeReference<List<StrategyMission>>(){});
//			for(StrategyMission mission : missions)
//				this.strategyMissions.put(mission.getId(), mission);
//		}
//		
//		//天赋任务
//		if(this.talentMissions==null)
//			this.talentMissions = Maps.newHashMap();
//		if(dbInfo!=null && StringUtilitys.isNotNullOrZeroLenght(dbInfo.getTalent()))
//		{
//			List<TalentMission> missions = JSONObject.parseObject(dbInfo.getTalent(), new TypeReference<List<TalentMission>>(){});
//			for(TalentMission mission : missions)
//				this.talentMissions.put(mission.getId(), mission);
//		}
//		
//		//后续版本新增的
//		this.initOnCreate();
//	}
//	
//	private DbMissionTotal createDbMissionTotal() {
//		DbMissionTotal info = new DbMissionTotal();
//		info.setPlayerId(this.player.getId());
//		info.setNormal("");
//		info.setStrategy("");
//		info.setTalent("");
//		return info;
//	}
//	
//	public void save(MongoDbDao mongoDao) {
//		try {
//			String str = "";
//			if(!this.normalMissions.isEmpty())
//			{
//				List<Mission> all = Lists.newArrayList();
//				for(Mission mission : this.normalMissions.values())
//				{
//					if(mission.isNoRecv() && mission.getProgress()==0)
//						continue;
//					all.add(mission);
//				}
//				str = JSONObject.toJSONString(all);
//			}
//			this.dbInfo.setNormal(str);
//		} catch (Exception e) {
//			LOGGER.info("save normal error: {}", e);
//		}
//		
//		try {
//			String str = "";
//			if(!this.strategyMissions.isEmpty())
//			{
//				List<StrategyMission> all = Lists.newArrayList(this.strategyMissions.values());
//				str = JSONObject.toJSONString(all);
//			}
//			this.dbInfo.setStrategy(str);
//		} catch (Exception e) {
//			LOGGER.info("save strategy error: {}", e);
//		}
//		
//		try {
//			String str = "";
//			if(!this.talentMissions.isEmpty())
//			{
//				List<TalentMission> all = Lists.newArrayList(this.talentMissions.values());
//				str = JSONObject.toJSONString(all);
//			}
//			this.dbInfo.setTalent(str);
//		} catch (Exception e) {
//			LOGGER.info("save talent error: {}", e);
//		}
//		
//		if(dbInfo.getObjectId()==null)
//		{
//			dbInfo.createObjectId();
//			mongoDao.insertOne(DbMissionTotal.TableName, dbInfo.toDocument());
//		}
//		else
//			mongoDao.updateById(DbMissionTotal.TableName, dbInfo.getObjectId(), dbInfo.toDocument());
//		MissionRedisUtil.setInfo(this.dbInfo, player.getPlayer().getRedisInfo());
//	}
//	
//	public List<Mission> getAllMissions(){
//		List<Mission> results = Lists.newArrayList(this.normalMissions.values());
//		return results;
//	}
//	
//	public PlayerContext getPlayer() {
//		return player;
//	}
//
//	public List<Mission> getUpdateMissions() {
//		return updateMissions;
//	}
//	
//	/**
//	 * 任务是否满足接取条件
//	 */
//	private boolean isMissionCanRecv(ConfigMission config)
//	{
//		//主公等级要求
//		if(this.player.getLevel()<config.getLvlLimit())
//			return false;
//		//任务要求
//		if(config.getMissionLimit()>0)
//		{
//			Mission mission = this.normalMissions.get(config.getMissionLimit());
//			if(mission==null || !mission.isCompleted())
//				return false;
//		}
//		//vip等级要求
//		if(config.getOtherLimitType()==1 && !config.getOtherLimitValue().isEmpty())
//		{
//			if(this.player.getPlayer().getDbPlayer().getVip()<config.getOtherLimitValue().get(0))
//				return false;
//		}
//		//月卡要求
//		else if(config.getOtherLimitType()==2)
//		{
//			if(!this.player.getPlayer().isMonthCard())
//				return false;
//		}
//		//时间段要求
//		else if(config.getOtherLimitType()==3 && config.getOtherLimitValue().size()==2)
//		{
//			int hour = ServerData.CurHour();
//			if(hour<config.getOtherLimitValue().get(0) || hour>=config.getOtherLimitValue().get(1))
//				return false;
//		}
//		//至尊卡要求
//		else if(config.getOtherLimitType()==4)
//		{
//			if(!this.player.getPlayer().isSupreme())
//				return false;
//		}
//		else if(config.getOtherLimitType()>0)
//			return false;
//		
//		return true;
//	}
//	/**
//	 * 任务是否满足完成条件
//	 */
//	private boolean isMissionCanComplete(int missionId)
//	{
//		Mission mission = this.normalMissions.get(missionId);
//		if(mission==null)
//			return false;
//		ConfigMission config = ConfigMissionMgr.getConfig(missionId);
//		if(config==null)
//			return false;
//		if(config.getCompleteType()==0 
//				&& mission.getProgress()<config.getCompleteTotal())
//			mission.setProgress(config.getCompleteTotal());	//无完成条件的,直接完成
//		
//		return mission.getProgress()>=config.getCompleteTotal();
//	}
//	
//	/**
//	 * 重置每日任务
//	 */
//	public void resetDailyMission() {
//		List<ConfigMission> dailyMissions = ConfigMissionMgr.getDailyMissions();
//		for(ConfigMission config : dailyMissions)
//		{
//			Mission mission = this.normalMissions.get(config.getID());
//			if(mission==null || mission.isNoRecv())
//				continue;
//			resetMission(mission);
//			this.updateMissions.add(mission);
//		}
//	}
//	public void resetMission(Mission mission) {
//		mission.reset();
//		ConfigMission config = ConfigMissionMgr.getConfig(mission.getId());
//		this.recvMission(config);
//	}
//	/**
//	 * 检查未接取的任务
//	 */
//	public boolean checkNoRecvMissions() {
//		boolean chg = false;
//		for(int missionId : this.normalMissions.keySet())
//		{
//			if(checkNoRecvMission(missionId))
//				chg = true;
//		}
//		return chg;
//	}
//	/**
//	 * 检查未接取的任务
//	 */
//	public boolean checkNoRecvMission(int missionId) {
//		Mission mission = this.normalMissions.get(missionId);
//		if(mission==null || !mission.isNoRecv())
//			return false;
//		ConfigMission config = ConfigMissionMgr.getConfig(mission.getId());
//		if(this.recvMission(config))
//		{
//			this.updateMissions.add(mission);
//			return true;
//		}
//		return false;
//	}
//	/**
//	 * 接收任务
//	 */
//	public boolean recvMission(ConfigMission config) {
//		Mission mission = this.normalMissions.get(config.getID());
//		if(mission==null || !mission.isNoRecv())
//			return false;
//		if(!this.isMissionCanRecv(config))
//			return false;
//		
//		int state = this.isMissionCanComplete(config.getID())? 
//						ConfigMissionMgr.State_NoReward : ConfigMissionMgr.State_NoComplete;
//		mission.setRecvTime(new Date());
//		mission.setState(state);
//		return true;
//	}
//	/**
//	 * 关闭未完成并获取奖励的任务
//	 */
//	public boolean closeMission(int missionId) {
//		Mission mission = this.normalMissions.get(missionId);
//		if(mission==null || mission.isCompleted())
//			return false;
//		mission.setState(ConfigMissionMgr.State_NoRecv);
//		mission.setProgress(0);
//		this.updateMissions.add(mission);
//		return true;
//	}
//	
//	/**
//	 * 任务进度
//	 */
//	public boolean progressMission(int progressDelta, int missionId) {
//		Mission mission = this.normalMissions.get(missionId);
//		if(mission==null || mission.isNoReward() || mission.isCompleted())
//			return false;
//		ConfigMission config = ConfigMissionMgr.getConfig(missionId);
//		if(config==null)
//			return false;
//		
//		int progress = mission.getProgress();
//		progress += progressDelta;
//		progress = Math.min(progress, config.getCompleteTotal());
//		mission.setProgress(progress);
//		
//		if(mission.isNoComplete() && this.isMissionCanComplete(mission.getId()))
//		{//状态为未完成且当前可以完成
//			mission.setState(ConfigMissionMgr.State_NoReward);
//			//记录日志
//			PlayerLog.logMission(player.getPlayer(), NatureEnum.MissionFinish, "", "", missionId, config.getName());
//		}
//		this.updateMissions.add(mission);
//		return true;
//	}
//	/**
//	 * 领取任务奖励
//	 */
//	public int rewardMission(int missionId, AckMissionRewardResp ack) {
//		try {
//			Mission mission = this.normalMissions.get(missionId);
//			if(mission==null)
//				return 1723;
//			
//			ConfigMission config = ConfigMissionMgr.getConfig(missionId);
//			if(config==null)
//				return 1723;
//			
//			if (!mission.isNoReward()) 
//				return 1724;
//			
//			//记录
//			mission.setState(ConfigMissionMgr.State_Completed);
//			//奖励
//			IBagService bagService = SpringContextHolder.getInstance().getBean(IBagService.class);
//			bagService.addNewGood(this.player.getId(), config.getRewardMap(), NatureEnum.Mission);
//			ack.addRewardInfo(config.getRewardMap());
//			this.updateMissions.add(mission);
//			//是否有下一条任务
//			int effectMissionId = ConfigMissionMgr.getMissionRecvEffects(missionId);
//			if(effectMissionId>0)
//			{
//				Mission effectMission = this.normalMissions.get(effectMissionId);
//				if(effectMission!=null && effectMission.isNoRecv())
//				{
//					ConfigMission effectConfig = ConfigMissionMgr.getConfig(effectMissionId);
//					if(this.recvMission(effectConfig))
//						this.updateMissions.add(effectMission);
//				}
//			}
//			//发送任务完成事件
//			GameEventBus.instance().post(MissionFinishEvent.create(player.getId(), missionId, config.getType()));
//			//记录日志
//			PlayerLog.logMission(player.getPlayer(), NatureEnum.Mission, "", SourceNature.itemMapToStr(config.getRewardMap()), missionId, config.getName());
//			return 0;
//		} catch (Exception e) {
//			LOGGER.error("rewardMission error, missionId:{}, e:{}", missionId, e);
//			return 1721;
//		}
//	}
//
//	/**
//	 * 一键领取任务奖励
//	 */
//	public int rewardAkeyMission(AckMissionRewardResp ack) {
//		try {
//			//是否有任务, 是否操作过
//			boolean has = true, opt = false;
//			while (has) {
//				has = false;
//				for (Mission mission : getAllMissions()) {
//					if (!mission.isNoReward()) 
//						continue;
//					int ret = rewardMission(mission.getId(), ack);
//					if (ret == 0) {//但凡有完成任务, 则设置has为true, 继续循环
//						has = true;
//						opt = true;
//					}
//				}
//			}
//			return opt ? 0 : 1725;
//		} catch (Exception e) {
//			LOGGER.error("rewardMission error,e:", e);
//			return 1721;
//		}
//	}
//	
//	public DbMissionTotal getDbInfo() {
//		return dbInfo;
//	}
//
//
//	///////////////////////// 锦囊任务 ////////////////////
//	/**
//	 * 获取所有锦囊任务
//	 */
//	public List<StrategyMission> getAllStrategys(){
//		return Lists.newArrayList(this.strategyMissions.values());
//	}
//	
//	/**
//	 * 接收锦囊任务
//	 */
//	public boolean recvStrategyMission(ConfigStrategy config) {
//		StrategyMission mission = this.strategyMissions.get(config.getID());
//		if(mission==null || !mission.isNoRecv())
//			return false;
//		if(!this.isStrategyCanRecv(config))
//			return false;
//		
//		int state = this.isStrategyCanComplete(config.getID())? 
//						ConfigMissionMgr.State_NoReward : ConfigMissionMgr.State_NoComplete;
//		mission.setState(state);
//		return true;
//	}
//	
//	/**
//	 * 任务是否满足接取条件
//	 */
//	private boolean isStrategyCanRecv(ConfigStrategy config)
//	{
//		//主公等级要求
//		if(!config.getOpenLv().isEmpty())
//		{
//			List<Integer> openLvs = config.getOpenLv().get(0);
//			if(openLvs.get(0)==1)
//			{
//				if(this.player.getLevel()<openLvs.get(1))
//					return false;
//			}
//		}		
//		return true;
//	}
//	
//	/**
//	 * 任务是否满足完成条件
//	 */
//	private boolean isStrategyCanComplete(int missionId)
//	{
//		StrategyMission mission = this.strategyMissions.get(missionId);
//		if(mission==null)
//			return false;
//		ConfigStrategy config = ConfigStrategyMgr.getConfig(missionId);
//		if(config==null)
//			return false;
//		if(config.getCompleteType()==0 
//				&& mission.getProgress()<config.getCompleteTotal())
//			mission.setProgress(config.getCompleteTotal());	//无完成条件的,直接完成
//		return mission.getProgress()>=config.getCompleteTotal();
//	}
//	
//	/**
//	 * 领取任务奖励
//	 */
//	public int rewardStrategy(int missionId, AckStrategyMissionRewardResp ack) {
//		try {
//			StrategyMission mission = this.strategyMissions.get(missionId);
//			if(mission==null)
//				return 1723;
//			
//			ConfigStrategy config = ConfigStrategyMgr.getConfig(missionId);
//			if(config==null)
//				return 1723;
//			
//			if (!mission.isNoReward()) 
//				return 1724;
//			
//			//记录
//			mission.setState(ConfigMissionMgr.State_Completed);
//			//奖励
//			if(!config.getReward().isEmpty())
//			{
//				IBagService bagService = SpringContextHolder.getInstance().getBean(IBagService.class);
//				bagService.addNewGood(this.player.getId(), config.getReward().get(0), config.getReward().get(1), NatureEnum.StrategyMission);
//				ack.addRewardInfo(config.getReward().get(0), config.getReward().get(1));
//				//记录日志
//				PlayerLog.logMission(player.getPlayer(), NatureEnum.StrategyMission, "", SourceNature.itemToStr(config.getReward().get(0), config.getReward().get(1)), missionId, config.getName());
//			}
//			return 0;
//		} catch (Exception e) {
//			LOGGER.error("rewardStrategy error, missionId:{}, e:{}", missionId, e);
//			return 1721;
//		}
//	}
//	
//	/**
//	 * 检查未接取的任务
//	 */
//	public boolean checkNoRecvStrategys() {
//		boolean chg = false;
//		for(int missionId : this.strategyMissions.keySet())
//		{
//			if(checkNoRecvStrategys(missionId))
//				chg = true;
//		}
//		return chg;
//	}
//	/**
//	 * 检查未接取的任务
//	 */
//	private boolean checkNoRecvStrategys(int missionId) {
//		StrategyMission mission = this.strategyMissions.get(missionId);
//		if(mission==null || !mission.isNoRecv())
//			return false;
//		ConfigStrategy config = ConfigStrategyMgr.getConfig(mission.getId());
//		if(this.recvStrategyMission(config))
//			return true;
//		return false;
//	}
//
//	/**
//	 * 任务进度
//	 * @return 状态是否改变为已完成
//	 */
//	public boolean progressStrategy(int progressDelta, int missionId) {
//		StrategyMission mission = this.strategyMissions.get(missionId);
//		if(mission==null || mission.isNoReward() || mission.isCompleted())
//			return false;
//		ConfigStrategy config = ConfigStrategyMgr.getConfig(missionId);
//		if(config==null)
//			return false;
//		
//		int progress = mission.getProgress();
//		progress += progressDelta;
//		progress = Math.min(progress, config.getCompleteTotal());
//		mission.setProgress(progress);
//		
//		boolean chg = false;
//		if(mission.isNoComplete() && this.isStrategyCanComplete(mission.getId()))
//		{//状态为未完成且当前可以完成
//			mission.setState(ConfigMissionMgr.State_NoReward);
//			chg = true;
//			//记录日志
//			PlayerLog.logMission(player.getPlayer(), NatureEnum.MissionFinish, "", "", missionId, config.getName());
//		}
//		return chg;
//	}
//	
//
//	///////////////////////// 天赋任务 ////////////////////
//	/**
//	 * 获取所有天赋任务
//	 */
//	public List<TalentMission> getAllTalents(){
//		return Lists.newArrayList(this.talentMissions.values());
//	}
//	
//	/**
//	 * 接收天赋任务
//	 */
//	public boolean recvTalentMission(ConfigTalentTask config) {
//		TalentMission mission = this.talentMissions.get(config.getID());
//		if(mission==null || !mission.isNoRecv())
//			return false;
//		if(!this.isTalentCanRecv(config))
//			return false;
//		
//		int state = this.isTalentCanComplete(config.getID())? 
//						ConfigMissionMgr.State_NoReward : ConfigMissionMgr.State_NoComplete;
//		mission.setState(state);
//		return true;
//	}
//	
//	/**
//	 * 任务是否满足接取条件
//	 */
//	private boolean isTalentCanRecv(ConfigTalentTask config)
//	{
//		//主公等级要求
//		if(config.getNeedLevel()>0)
//		{
//			if(this.player.getLevel()<config.getNeedLevel())
//				return false;
//		}
//		//任务要求
//		if(config.getMissionLimit()>0)
//		{
//			TalentMission mission = this.talentMissions.get(config.getMissionLimit());
//			if(mission==null || !mission.isCompleted())
//				return false;
//		}
//		return true;
//	}
//	
//	/**
//	 * 任务是否满足完成条件
//	 */
//	private boolean isTalentCanComplete(int missionId)
//	{
//		TalentMission mission = this.talentMissions.get(missionId);
//		if(mission==null)
//			return false;
//		ConfigTalentTask config = ConfigTalentTaskMgr.getConfig(missionId);
//		if(config==null)
//			return false;
//		
//		IBagService bagSer = SpringContextHolder.getInstance().getBean(IBagService.class);
//		boolean completed = bagSer.existSymbols(this.player.getId(), config.getCompleteQuality(), config.getCompleteStar(), config.getCompleteNum(), false);
//		return completed;
//	}
//	
//	/**
//	 * 领取任务奖励
//	 */
//	public int rewardTalent(int missionId, AckTalentMissionRewardResp ack) {
//		try {
//			TalentMission mission = this.talentMissions.get(missionId);
//			if(mission==null)
//				return 1723;
//			
//			ConfigTalentTask config = ConfigTalentTaskMgr.getConfig(missionId);
//			if(config==null)
//				return 1723;
//			
//			if (!mission.isNoReward()) 
//				return 1724;
//			
//			//记录
//			mission.setState(ConfigMissionMgr.State_Completed);
//			//奖励
//			if(!config.getReward().isEmpty())
//			{
//				IBagService bagService = SpringContextHolder.getInstance().getBean(IBagService.class);
//				bagService.addNewGood(this.player.getId(), config.getRewardMap(), NatureEnum.TalentMission);
//				ack.addRewardInfo(config.getRewardMap());
//				//记录日志
//				PlayerLog.logMission(player.getPlayer(), NatureEnum.TalentMission, "", SourceNature.itemMapToStr(config.getRewardMap()), missionId, config.getName());
//			}
//			
//			//是否有下一条任务
//			int effectMissionId = ConfigTalentTaskMgr.getMissionRecvEffects(missionId);
//			if(effectMissionId>0)
//			{
//				TalentMission effectMission = this.talentMissions.get(effectMissionId);
//				if(effectMission!=null && effectMission.isNoRecv())
//				{
//					ConfigTalentTask effectConfig = ConfigTalentTaskMgr.getConfig(effectMissionId);
//					this.recvTalentMission(effectConfig);
//				}
//			}
//			return 0;
//		} catch (Exception e) {
//			LOGGER.error("rewardTalent error, missionId:{}, e:{}", missionId, e);
////			e.printStackTrace();
//			return 1721;
//		}
//	}
//	
//	/**
//	 * 检查未接取的任务
//	 */
//	public boolean checkNoRecvTalents() {
//		boolean chg = false;
//		for(int missionId : this.talentMissions.keySet())
//		{
//			if(checkNoRecvTalents(missionId))
//				chg = true;
//		}
//		return chg;
//	}
//	/**
//	 * 检查未接取的任务
//	 */
//	private boolean checkNoRecvTalents(int missionId) {
//		TalentMission mission = this.talentMissions.get(missionId);
//		if(mission==null || !mission.isNoRecv())
//			return false;
//		ConfigTalentTask config = ConfigTalentTaskMgr.getConfig(mission.getId());
//		if(this.recvTalentMission(config))
//			return true;
//		return false;
//	}
//
//	/**
//	 * 处理任务，若任务能完成，则改为完成未领取状态
//	 * @return 状态是否改变为已完成
//	 */
//	public boolean progressTalent(int missionId) {
//		TalentMission mission = this.talentMissions.get(missionId);
//		if(mission==null || mission.isNoReward() || mission.isCompleted())
//			return false;
//		ConfigTalentTask config = ConfigTalentTaskMgr.getConfig(missionId);
//		if(config==null)
//			return false;
//		
//		boolean chg = false;
//		if(mission.isNoComplete() && this.isTalentCanComplete(mission.getId()))
//		{//状态为未完成且当前可以完成
//			mission.setProgress(1);
//			mission.setState(ConfigMissionMgr.State_NoReward);
//			chg = true;
//			//记录日志
//			PlayerLog.logMission(player.getPlayer(), NatureEnum.TalentMissionFinish, "", "", missionId, config.getName());
//		}
//		return chg;
//	}
//	
//	public TalentMission getTalent(int missionId) {
//		return this.talentMissions.get(missionId);
//	}
	
}
