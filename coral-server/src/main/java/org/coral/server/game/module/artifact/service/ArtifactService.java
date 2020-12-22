package org.coral.server.game.module.artifact.service;

import com.google.common.collect.Maps;

import org.coral.server.game.module.artifact.domain.Artifact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;


/**
 * Artifact控制器
 */
@Service
public class ArtifactService {
	
	private static final Logger log = LoggerFactory.getLogger(ArtifactService.class);
	
	public Artifact getArtifact(long playerId, int configId) {
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			if(artifact.getConfigId() == configId)
//				return artifact;
//		}
		return null;
//		return artifacts.stream().filter(e -> e.getConfigId() == configId).findAny()
//				.orElseThrow(() -> new IllegalArgumentException(String.format("No such artifact:%d", configId)));
	}
	
	/***
	 * 根据id获取Artifact列表
	 * @param playerId
	 */
	public Collection<Artifact> getArtifacts(long playerId) {
//		Collection<Artifact> artifacts = Artifact.load(Artifact.class, "playerId", playerId);
//		if(artifacts.size() == 0){
//			Collection<ConfigArtifact> configs = ConfigManager.get(ConfigArtifact.class);
//			for (ConfigArtifact config : configs) {
////				Artifact artifact = Artifact.create(playerId, config.getID());
////				if(config.getID() == ConfigArtifact.ARTIFACT_INIT){
////					artifact.setState(ConfigArtifact.ARTIFACT_STATE_NOACTIVITE);
////				}
//				Artifact artifact = Artifact.loadOrCreate(Artifact.class, Artifact.incrementId(Artifact.class), e->{
//					Artifact.create(e,playerId, config.getID());
//					if(config.getID() == ConfigArtifact.ARTIFACT_INIT){
//						e.setState(ConfigArtifact.ARTIFACT_STATE_NOACTIVITE
//						);
//					}
//					//测试数据
//					e.setState(ConfigArtifact.ARTIFACT_STATE_ACTIVITE);
//				});
////				artifact.save();
//			}
//		}
//		return Artifact.load(Artifact.class, "playerId", playerId);
		return null;
	}
	
	/**
	 * 登陆
	 */
	public void onLogin(long playerId) {
		this.responseArtifactInfo(playerId);
	}
	
	/**
	 * 当通关时
	 * @param playerId
	 * @param layer
	 */
	public void onChapterPass(long playerId, int layer) {
		try {
//			boolean bool = true;
//			Collection<Artifact> artifacts = getArtifacts(playerId);
//			for (Artifact artifact : artifacts) {
//				bool = artifact.getArtifactMissionHelper().onChapterPass(playerId, layer) || bool;//达成任务
//				if (bool) {
//					artifact.update();
//				}
//			}
//			if (bool) responseArtifactInfo(playerId);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("onChapterPass error, playerId:{}", playerId);
			log.error("onChapterPass error, e:", e);
		}
	}
	
	/**
	 * 当玩家升级
	 * @param playerId
	 * @param level 等級
	 */
	public void onPlayerlevelUp(long playerId, int level) {
		try {
//			boolean bool = true;
//			Collection<Artifact> artifacts = getArtifacts(playerId);
//			for (Artifact artifact : artifacts) {
//				bool = artifact.getArtifactMissionHelper().onPlayerLevelUp(playerId, level) || bool;//达成任务
//				if (bool) {
//					artifact.update();
//				}
//			}
//			if (bool) responseArtifactInfo(playerId);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("onPlayerlevelUp error, playerId:{}", playerId);
			log.error("onPlayerlevelUp error, e:", e);
		}
	}
	
	/**
	 * 当武将升级
	 * @param playerId 玩家id
	 * @param level 英雄等級
	 */
	public void onHeroLevelUp(long playerId, int level) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onHeroLevelUp(playerId, level) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) responseArtifactInfo(playerId);
	}
	
	/**
	 * 当高级召唤
	 * @param playerId 玩家id
	 * @param count 次數
	 */
	public void onCallHigh(long playerId, int count) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onCallHigh(playerId, count) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) responseArtifactInfo(playerId);
	}
	
	/**
	 *当競技場挑戰
	 * @param playerId 玩家id
	 * @param count 次數
	 */
	public void onArenaFight(long playerId, int count) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onArenaFight(playerId, count) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 * 当远航任务完成
	 * @param playerId 玩家id
	 * @param count 遠航次數
	 */
	public void onSailMission(long playerId, int count) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onSailMission(playerId, count) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 * 当快速作战
	 * @param playerId
	 * @param count 次數
	 */
	public void onChapterQuick(long playerId, int count) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onChapterQuick(playerId, count) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 * 精灵商店购买
	 * @param playerId 玩家id
	 * @param shopType 商店类型
	 */
	public void onShopSpiritBuy(long playerId, int shopType) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onShopSpiritBuy(playerId, shopType) || bool;//达成任务
//			if (bool){
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 * 参与公会副本
	 * TODO 
	 * @param playerId 玩家id
	 * @param count 公会副本次数
	 */
	public void onFamilyChapter(long playerId, int count) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onFamilyChapter(playerId, count) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 * 试练塔达到x层
	 * @param playerId 玩家id
	 * @param layer 试练塔层数
	 */
	
	public void onTowerPass(long playerId, int layer) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onTowerPass(playerId, layer) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 * 战力达到x
	 * @param playerId
	 * @param power
	 */
	public void onPower(long playerId, int power) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onPower(playerId, power) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) responseArtifactInfo(playerId);
	}
	
	/**
	 * 合成五星英雄
	 * @param playerId
	 * @param star
	 */
	public void onHeroCompose(long playerId, int star) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onHeroCompose(playerId, star) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) responseArtifactInfo(playerId);
	}
	
	/**
	 * 段位赛达到王者
	 * @param playerId
	 * @param rank
	 */
	public void onCompetitionKing(long playerId, int rank) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onCompetitionKing(playerId, rank) || bool;//达成任务
//			if (bool){
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 *拥有x个x星武将
	 * @param playerId
	 * @param star
	 */
	public void onHeroHave(long playerId, int star) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onHeroHave(playerId, star) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	/**
	 *先知召唤x
	 * @param playerId 玩家ID
	 * @param count 次数
	 */
	public void onCallProphet(long playerId, int count) {
//		boolean bool = true;
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			bool = artifact.getArtifactMissionHelper().onCallProphet(playerId, count) || bool;//达成任务
//			if (bool) {
//				artifact.update();
//			}
//		}
//		if (bool) {
//			responseArtifactInfo(playerId);
//		}
	}
	
	
	/**
	 * 更新信息
	 */
	private void responseArtifactInfo(long playerId) {
//		AckArtifactListResp resp = AckArtifactListResp.newInstance();
//		Collection<Artifact> artifacts = getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			playerId = artifact.getPlayerId();
//			resp.addArtifactlist(artifact.toProto());
//		}
//		SendMessageUtil.sendResponse(playerId, resp);
	}
	
//	/**
//	 * 更新信息
//	 */
//	public void responseArtifactInfo1(ArtifactDomain domain) {
//		Collection<Artifact> artifacts = domain.getAll();
//		try {
//			AckArtifactListResp resp = AckArtifactListResp.newInstance();
//			for (Artifact artifact : artifacts) {
////				artifact.getArtifactMissionHelper().
//				resp.addArtifactlist(artifact.toProto());
//			}
//			SendMessageUtil.sendResponse(domain.getPlayerId(), resp);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("responseArtifactInfo error, playerId:{}", domain.getPlayerId());
//			log.error("responseArtifactInfo error, e:", e);
//		}
//	}
	
	/////////////业务逻辑//////////////////
	
	
//	/**
//	* 请求神器信息
//	* @param playerId
//	* @param req
//	* @author Jeremy
//	*/
//	public int reqArtifactList(long playerId, PBArtifact.ReqArtifactList req){
//		this.responseArtifactInfo(playerId);
//		return 0;
//	}
	
//	/**
//	* 请求神器操作
//	* @param playerId 玩家id
//	* @param req
//	* @param ack
//	* @author Jeremy
//	*/
//	public int reqArtifactOpt(long playerId, PBArtifact.ReqArtifactOpt req, AckArtifactOptResp ack){
//		int optType = req.getOptType();
//		int configId = req.getConfigId();
//		int code = 0;
//		if (optType == ArtifactEnum.UPGRADE.getType()) {//升级操作
//			code = doUpgrade(playerId, configId);
//		}else if (optType == ArtifactEnum.REFINE.getType()) {
//			code = doRefine(playerId, configId);
//		}else if (optType == ArtifactEnum.SKILLUP.getType()) {
//			code = doSkillUp(playerId, configId);
//		}else if (optType == ArtifactEnum.RECAST.getType()) {
//			code = doRecast(playerId, configId);
//		}else if (optType == ArtifactEnum.UPGRADE_ONEKEY.getType()) {
//			code = doUpgradeOneKey(playerId, configId);
//		}
//		if (code == 0) {
//			this.responseArtifactInfo(playerId);
//		}
//		return code;
//	}

//	/**
//	 * 处理一键升级
//	 * 波波的话: 1次就够了 我们要做到真正的一键升级
//	 * 前端只请求一次, 神器升级成功后, 返回至前端.
//	 *
//	 * 一键升级实现方式: 默认升级十次, 计数器为0时, 再增加十次,直到升级不成功.
//	 * @param configId 神器配置id
//	 * @return 错误码
//	 */
//	public int doUpgradeOneKey(long playerId, int configId) {
//		Artifact artifact = getArtifacts(playerId).stream().filter(e -> e.getConfigId() == configId).findAny().get();
//		if (artifact == null) {
//			return ConfigTipsMgr.Artifact_731;
//		}
//		if (!artifact.isActivite()) {
//			return ConfigTipsMgr.Artifact_739;//未激活
//		}
//		/*
//		 * expire:预期次数, count:计数器, code 错误码
//		 * 如果计算器递减到0, 则程序视为升级成功, 重置计数器, 继续升级.
//		 * 如果第一次升级失败, 则返回错误码. 只要成功, 则不返回错误码.
//		 */
//		final int expire = 10;
//		int count = expire, code = 0;
//		while (count > 0) {
//			code = onUpgrade(artifact, configId);
//			if (code != 0) break;
//			if (--count == 0) count += (expire -1);
//		}
//		//如果第一次升级不成功, 返回第一次不成功的错误码
//		return count == expire ? code : 0;
//	}

//	/**
//	 * 重铸
//	 * @param configId 神器配置id
//	 * @return 错误码
//	 */
//	private int doRecast(long playerId, int configId) {
//		Artifact artifact = getArtifact(playerId, configId);
//		if (!artifact.isActivite()) {
//			return ConfigTipsMgr.Artifact_739;//未激活
//		}
//		int level = artifact.getLevel();
//		if (level <= 1) {
//			return ConfigTipsMgr.Artifact_736;
//		}
//		Map<Integer, Integer> materialHM = artifact.getUsedMaterialHM();
//		//移除掉金币
//		materialHM.remove(ResourceType.Copper.getClientType());
//		artifact.onRecast();
//		Context.getItemService().addNewItem(level, materialHM, NatureEnum.ARTIFACT_RECAST, StringUtils.toLogString("configId", configId));
//		artifact.update();
//		return 0;
//	}

//	/**
//	 * 处理技能升级
//	 * @param configId 神器配置id
//	 * @return 错误码
//	 */
//	private int doSkillUp(long playerId, int configId) {
//		Artifact artifact = getArtifact(playerId, configId);
//		if (!artifact.isActivite()) {
//			return ConfigTipsMgr.Artifact_739;//未激活
//		}
//		int level = artifact.getLevel();
//		int skillLevel =  artifact.getSkillLv();
//		//判断等级限制
//		ConfigArtifact artifactConfig = ConfigManager.get(ConfigArtifact.class,configId);
//		if (artifactConfig == null) {
//			return ConfigTipsMgr.Artifact_732;
//		}
//		if (skillLevel >= artifactConfig.getMaxSkillLevel()) {
//			return ConfigTipsMgr.Artifact_733;//已经到最大等级不可升级
//		}
//
//		//判断升级消耗
//		ConfigArtifactSkill artifactSkillConfig = ConfigArtifactSkill.getConfig(configId, skillLevel);
//		if (artifactSkillConfig == null) {
//			return ConfigTipsMgr.Artifact_732;
//		}
//		if (artifactSkillConfig.getNextID() == 0) {//表示最高级
//			return ConfigTipsMgr.Artifact_733;//已经到最大等级不可升级
//		}
//		if (level < artifactSkillConfig.getNeedLevel()) {
//			return ConfigTipsMgr.Artifact_734;//需要提升等级
//		}
//
//		boolean bool = Context.getItemService().enoughAndDeductItem(playerId,
//				artifactSkillConfig.getNeedGoodsMap(), NatureEnum.ARTIFACT_SKILLUP,
//				StringUtils.toLogString("configId", configId, "skillLevel", skillLevel));
//
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;//道具不足
//		}
//		//记录材料
//		artifact.setSkillLv(skillLevel + 1);
//		artifact.recordUsedMaterialHM(artifactSkillConfig.getNeedGoodsMap());
//		artifact.update();
//		EventPublisher.publishEvent0(new ArtifactSkillUpgradeEvent(artifact));
//		return 0;
//	}

//	/**
//	 * 处理精炼
//	 * @param configId 神器配置id
//	 * @return 错误码
//	 */
//	private int doRefine(long playerId, int configId) {
//		Artifact artifact = getArtifact(playerId, configId);
//		if (!artifact.isActivite()) {
//			return ConfigTipsMgr.Artifact_739;//未激活
//		}
//		int level = artifact.getLevel();
//		int rflevel =  artifact.getRefineLv();
//		//判断等级限制
//		ConfigArtifact artifactConfig = ConfigManager.get(ConfigArtifact.class,configId);
//		if (artifactConfig == null) {
//			return ConfigTipsMgr.Artifact_732;
//		}
//		if (rflevel >= artifactConfig.getMaxRefineLevel()) {
//			return ConfigTipsMgr.Artifact_733;//已经到最大等级不可升级
//		}
//		//判断升级消耗
//		ConfigArtifactRefine artifactRefineConfig = ConfigArtifactRefine.getConfig(configId, rflevel);
//		if (artifactRefineConfig == null) {
//			return ConfigTipsMgr.Artifact_732;
//		}
//		if (artifactRefineConfig.getNextId() == 0) {//表示最高级
//			return ConfigTipsMgr.Artifact_733;//已经到最大等级不可升级
//		}
//		if (level < artifactRefineConfig.getNeedLevel()) {
//			return ConfigTipsMgr.Artifact_735;//需要提升等级技能升级
//		}
//
//		boolean bool = Context.getItemService().enoughAndDeductItem(playerId,
//				artifactRefineConfig.getExpendMap(), NatureEnum.ARTIFACT_REFINE,
//				StringUtils.toLogString("configId", configId, "rflevel", rflevel));
//
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;//道具不足
//		}
//		//记录材料
//		artifact.setRefineLv(rflevel + 1);
//		artifact.recordUsedMaterialHM(artifactRefineConfig.getExpendMap());
//		artifact.update();
//		EventPublisher.publishEvent0(new ArtifactRefineEvent(artifact));
//		return 0;
//	}

//	/**
//	 * 处理升级
//	 * @param configId 神器配置id
//	 * @return 错误码
//	 */
//	private int doUpgrade(long playerId, int configId) {
//		Artifact artifact = getArtifact(playerId, configId);
//		if (!artifact.isActivite()) {
//			return ConfigTipsMgr.Artifact_739;//未激活
//		}
//		return onUpgrade(artifact, configId);
//	}
//
//	private int onUpgrade(Artifact artifact, int configId) {
//		int level =  artifact.getLevel();
//		//判断等级限制
//		ConfigArtifact artifactConfig = ConfigManager.get(ConfigArtifact.class,configId);
//		if (artifactConfig == null) {
//			return ConfigTipsMgr.Artifact_732;
//		}
//
//		if (level >= artifactConfig.getMaxLevel()) {
//			return ConfigTipsMgr.Artifact_733;//已经到最大等级不可升级
//		}
//		//判断升级消耗
//		ConfigArtifactLevelInfo artifactLevelConfig = ConfigArtifactLevelInfo.getConfig(configId, level);
//		if (artifactLevelConfig == null) {
//			return ConfigTipsMgr.Artifact_732;
//		}
//		if (artifactLevelConfig.getNextId() == 0) {//表示最高级
//			return ConfigTipsMgr.Artifact_733;//已经到最大等级不可升级
//		}
//		boolean bool = Context.getItemService().enoughAndDeductItem(artifact.getPlayerId(),
//				artifactLevelConfig.getNeedGoodsMap(), NatureEnum.ARTIFACT_UPGRADE,
//				StringUtils.toLogString("configId", configId, "level", level));
//
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;//道具不足
//		}
//		//记录材料
//		artifact.setExp(artifact.getExp() + 10); //TODO 需要策划验证, 目前每次升级默认加10.
//		if (artifact.getExp() >= artifactLevelConfig.getNeedExp()) {
//			artifact.setLevel(artifact.getLevel() + 1);
//		}
//		artifact.recordUsedMaterialHM(artifactLevelConfig.getNeedGoodsMap());
//		artifact.update();
//		//调用活动
//		Context.getSevenDayService().onActifactUpgrade(artifact.getPlayerId());
//		FunctionCondition.checkOpenFunction(FunctionCondition.ArtifactLevel,artifact.getPlayerId(),artifact.getLevel());
//
//		Context.getEventPublisher().publishEvent(ArtifactUpgradeEvent.create(artifact.getPlayerId(), configId, level));
//		return 0;
//	}
	
//	/**
//	* 请求领取神器任务
//	* @param playerId
//	* @param req
//	* @param ack
//	* @author Jeremy
//	*/
//	public int reqArtifactReceiveTask(long playerId, PBArtifact.ReqArtifactReceiveTask req, AckArtifactReceiveTaskResp ack){
//		int code = doTaskReward(playerId, req.getConfigId(), req.getTaskId());
//		if (code == 0) {
//			this.responseArtifactInfo(playerId);
//		}
//		return code;
//	}

//	/**
//	 * 领取任务奖励
//	 * 如果领完了所有奖励, 激活下一个神器
//	 * @param configId 神器配置id
//	 * @return 错误码
//	 */
//	private int doTaskReward(long playerId, int configId, int taskConfigId) {
//		Artifact artifact = getArtifact(playerId, configId);
//		ArtifactMissionHelper helper = artifact.getArtifactMissionHelper();
//		if (helper == null) {
//			return ConfigTipsMgr.Artifact_731;
//		}
//		EntityMission mission = helper.getMission(taskConfigId);
//		if (!mission.isActived()) {//未激活
//			return ConfigTipsMgr.Artifact_740;
//		}
//		ConfigArtifactTasks config = ConfigManager.get(ConfigArtifactTasks.class,taskConfigId);
//		if (config == null) {
//			return ConfigTipsMgr.Artifact_731;
//		}
//		//该状态,获取奖励,激活下一个神器
//		mission.setState(EntityMission.STATE_REWARDED);
//		boolean bool = artifact.checkCanActivite();
//		if (bool) {
//			checkUnlockNext(playerId, configId);//检测当前神器下一个是否可以解锁
//		}
//		artifact.update();
//		Context.getItemService().addNewItem(playerId, config.getRewardMap(), NatureEnum.ARTIFACT_REFINE_MISSION, StringUtils.toLogString("configId", configId, "taskConfigId", taskConfigId));
//		AckRewardsResp resp = AckRewardsResp.newInstance().addAllRewards(config.getRewardMap());
//		SendMessageUtil.sendResponse(playerId, resp);
//		return 0;
//	}

//	/**
//	 * 检测是否可以解锁下一个
//	 * @param configId 神器配置id
//	 * @return true:可以解锁, false:不可
//	 */
//	private boolean checkUnlockNext(long playerId, int configId) {
//		ConfigArtifact config = ConfigManager.get(ConfigArtifact.class,configId);
//		if (config.getNext() == 0)
//			return false;
//
//		Artifact artifact = getArtifact(playerId, configId);
//		artifact.setState(ConfigArtifact.ARTIFACT_STATE_NOACTIVITE);
//		artifact.update();
//		Context.getEventPublisher().publishEvent(ArtifactUnlockEvent.create(playerId, configId));
//		return true;
//	}
	
//	/**
//	* 请求圣印
//	* @param playerId
//	* @param req
//	* @param ack
//	* @author Jeremy
//	*/
//	public int reqArtifactHolySeal(long playerId, PBArtifact.ReqArtifactHolySeal req, AckArtifactHolySealResp ack){
//		int code = doHolySeal(playerId, req.getConfigId(), req.getNum());
//		if (code == 0) {
//			this.responseArtifactInfo(playerId);
//		}
//		return code;
//	}

//	/**
//	 * 圣印
//	 * @param configId 神器配置id
//	 * @return 错误码
//	 */
//	private int doHolySeal(long playerId, int configId, int costNumber) {
//		if (costNumber <= 0) {
//			return ConfigTipsMgr.Artifact_738;
//		}
//		Artifact artifact = getArtifact(playerId, configId);
//		if (!artifact.isActivite()) {
//			return ConfigTipsMgr.Artifact_739;//未激活
//		}
//		ConfigArtifactLevelInfo artifactLevelConfig = ConfigArtifactLevelInfo.getConfig(configId, artifact.getLevel());
//		if (artifactLevelConfig == null) {
//			return ConfigTipsMgr.Artifact_732;
//		}
//		int costStampStoneNum = artifact.costStampStoneNum();
//		if (costStampStoneNum >= artifactLevelConfig.getStampLimit()) {
//			return ConfigTipsMgr.Artifact_737;
//		}
//		int realNum = artifactLevelConfig.getStampLimit() - costStampStoneNum;
//		realNum = Math.min(realNum, costNumber);
//
//		Map<Integer, Integer> costMap = Maps.newHashMap();
//		for (Integer key : ConfigConstantPlus.artifactHolySealStampCost.keySet()) {
//			costMap.put(key, ConfigConstantPlus.artifactHolySealStampCost.get(key) * realNum);
//		}
//
//		//判断道具
//		boolean bool = Context.getItemService().enoughAndDeductItem(playerId,
//				costMap, NatureEnum.ARTIFACT_SKILLUP,
//				StringUtils.toLogString("configId", configId));
//
//		if (!bool) {
//			return ConfigTipsMgr.Bag_204;//道具不足
//		}
//		artifact.recordUsedMaterialHM(costMap);
//		artifact.setHolySealLv(artifact.getHolySealLv() + realNum);
//		artifact.update();
//		EventPublisher.publishEvent0(new ArtifactHolySealEvent(artifact));
//		return 0;
//	}

}