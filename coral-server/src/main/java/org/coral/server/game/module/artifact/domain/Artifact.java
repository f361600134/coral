package org.coral.server.game.module.artifact.domain;

import java.util.List;
import java.util.Map;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@PO(name = "artifact")
public class Artifact extends BasePo {
	
	private static final Logger log = LoggerFactory.getLogger(Artifact.class);
	
	//////////////持久化数据//////////
	private long playerId;//玩家ID
	private int configId;//神器配置id
	private int level;//神器等级
	private int exp;//经验
	private int refineLv;//精炼等级
	private int skillLv;//技能等级
	private int state;//状态
	private int holySealLv;//圣印等级
	private int skinId;//皮肤id
	private List<Integer> missions;//已完成任务列表
	private Map<Integer, Integer> usedMaterialHM;//累计使用材料
//	private ArtifactMissionHelper artifactMissionHelper;

	public int getSkillId() {
//		ConfigArtifactSkill config = ConfigArtifactSkill.getConfig(configId, skillLv);
//		return config.getSkillID();
		return 0;
	}

	public static Artifact get(long id) {
//		return Artifact.load(Artifact.class, id);
		return null;
	}

	public static Artifact get(long playerId, int configId) {
//		List<Artifact> artifacts = Artifact.load(Artifact.class, "playerId", playerId);
//		for (Artifact artifact : artifacts) {
//			if (artifact.getConfigId() == configId) {
//				return artifact;
//			}
//		}
		return null;
	}

	/** 玩家ID **/
	public long getPlayerId(){
		return this.playerId;
	}

	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}

	/** 神器配置id **/
	public int getConfigId(){
		return this.configId;
	}

	public void setConfigId(int configId){
		this.configId = configId;
	}

	/** 神器等级 **/
	public int getLevel(){
		return this.level;
	}

	public void setLevel(int level){
		this.level = level;
	}

	/** 经验 **/
	public int getExp(){
		return this.exp;
	}

	public void setExp(int exp){
		this.exp = exp;
	}

	/** 精炼等级 **/
	public int getRefineLv(){
		return this.refineLv;
	}

	public void setRefineLv(int refineLv){
		this.refineLv = refineLv;
	}

	/** 技能等级 **/
	public int getSkillLv(){
		return this.skillLv;
	}

	public void setSkillLv(int skillLv){
		this.skillLv = skillLv;
	}

	/** 状态 **/
	public int getState(){
		return this.state;
	}

	public void setState(int state){
		this.state = state;
	}

	/** 圣印等级 **/
	public int getHolySealLv(){
		return this.holySealLv;
	}

	public void setHolySealLv(int holySealLv){
		this.holySealLv = holySealLv;
	}

	/** 皮肤id **/
	public int getSkinId(){
		return this.skinId;
	}

	public void setSkinId(int skinId){
		this.skinId = skinId;
	}


	//////////////非持久化数据//////////

//	//基础属性
//	private Map<Integer, Integer> baseAttrs;
//	
//	//技能加成属性
//	private Map<Integer, Integer> skillAttrs;
	
	public Artifact() {
		missions = Lists.newArrayList();
		usedMaterialHM = Maps.newHashMap();
//		this.artifactMissionHelper = new ArtifactMissionHelper();
	}
	
	/**
	 * 获取任务列表
	 * @return
	 */
	public List<Integer> getMissions() {
		return missions;
	}
	
	public Map<Integer, Integer> getUsedMaterialHM() {
		return usedMaterialHM;
	}
	
//	public ArtifactMissionHelper getArtifactMissionHelper() {
//		return artifactMissionHelper;
//	}
	
//	/**
//	 * 记录全部的材料
//	 * @param useMaterialMap
//	 */
//	public void recordUsedMaterialHM(Map<Integer, Integer> useMaterialMap) {
//		CollectionUtil.mergeToMap(useMaterialMap, usedMaterialHM);
//	}
	
//	public PBArtifact.PBArtifactInfo toProto() {
//		PBArtifactInfoBuilder builder = PBArtifactInfoBuilder.newInstance();
//		builder.setConfigId(getConfigId());
//		builder.setLevel(getLevel());
//		builder.setExp(getExp());
//		builder.setRefineLv(getRefineLv());
//		builder.setSkillLv(getSkillLv());
//		builder.setState(getState());
//		builder.setSkinId(getSkinId());
//		builder.setUseStampStoneNum(costStampStoneNum());
//		//任务列表
//		builder.addAllMissions(artifactMissionHelper.toProto());
//		return builder.build();
//	}
	
//	/**
//	 * 初始化任务列表,每个神器根据自己的配置初始化任务
//	 */
//	public void init(long playerId) {
//		Map<Integer, EntityMission> maps = Maps.newConcurrentMap();
//		ConfigArtifact config = ConfigManager.get(ConfigArtifact.class,getConfigId());
//		for (Integer missionId : config.getMissionIds()) {
//			ConfigArtifactTasks configTask = ConfigManager.get(ConfigArtifactTasks.class, missionId);
//			EntityMission entityMission = new EntityMission(missionId, EntityMission.STATE_NONE, 0,
//					configTask.getCompleteType(), configTask.getCompleteCondition(), configTask.getCompleteValue());
//			maps.put(missionId, entityMission);
//		}
//		artifactMissionHelper.init(playerId, maps);
//	}
	
//	/**
//	 * 生成一个神器
//	 * @param playerId
//	 * @param configId 神器配置id
//	 * @return
//	 */
//	public static Artifact create(Artifact artifact,long playerId, int configId) {
////		Artifact artifact = Artifact.loadOrCreate(Artifact.class, Artifact.incrementId(Artifact.class));
//		artifact.setPlayerId(playerId);
//		artifact.setConfigId(configId);
//		artifact.setLevel(1);
//		artifact.setExp(0);
//		artifact.setRefineLv(1);
//		artifact.setSkillLv(1);
//		artifact.setState(ConfigArtifact.ARTIFACT_STATE_LOCK);
//		artifact.setHolySealLv(1);
//		artifact.setSkinId(0);
//		artifact.init(playerId);
//		return artifact;
//	}
	
//	/**
//	 * 获得此神器总属性
//	 * @return
//	 */
//	public Map<Integer, Integer> getTotalAttrs(){
//		Map<Integer, Integer> attrs = Maps.newConcurrentMap();
//		if (!isActivite()) {//未解锁
//			return attrs;
//		}
//		//计算基础属性
//		ConfigArtifactLevelInfo levelConfig = ConfigArtifactLevelInfo.getConfig(getConfigId(), getLevel());
//		if (levelConfig == null) {
//			log.info("getTotalAttrs error, configId:{}, level:{}", getConfigId(), getLevel());
//			return attrs;
//		}
//		CollectionUtil.mergeMap(levelConfig.getAdditionMap(), attrs);
//		
//		//计算技能加成属性
//		ConfigArtifactSkill skillConfig = ConfigArtifactSkill.getConfig(getConfigId(), getSkillLv());
//		if (skillConfig == null) {
//			log.info("getTotalAttrs error, configId:{}, level:{}", getConfigId(), getLevel());
//			return attrs;
//		}
//		CollectionUtil.mergeMap(skillConfig.getSkillAttrMap(), attrs);
//		
//		//计算刻印石属性
//		int costStoneNum = costStampStoneNum();
//		Map<Integer, Integer> stampStoneAttr = Maps.newConcurrentMap();
//		for (Integer key : ConfigConstantPlus.artifactStampAttribute.keySet()) {
//			stampStoneAttr.put(key, ConfigConstantPlus.artifactStampAttribute.get(key) * costStoneNum);
//		}
//		CollectionUtil.mergeMap(stampStoneAttr, attrs);
//		return attrs;
//	}
	
//	/**
//	 * 获取圣印消耗道具数量
//	 * @return
//	 */
//	public int costStampStoneNum() {
//		Iterator<Integer> iter = ConfigConstantPlus.artifactHolySealStampCost.keySet().iterator();
//		int stoneId = iter.hasNext() ? iter.next() : 0;
//		return getUsedMaterialHM().getOrDefault(stoneId, 0);
//	}
	
//	/**
//	 * 是否锁住状态
//	 * @return true 是, false 否
//	 * @return
//	 */
//	public boolean isLock() {
//		return getState() == ConfigArtifact.ARTIFACT_STATE_LOCK;
//	}
//	
//	/**
//	 * 是否解锁状态
//	 * @return true 是, false 否
//	 * @return
//	 */
//	public boolean isUnlock() {
//		return getState() == ConfigArtifact.ARTIFACT_STATE_NOACTIVITE;
//	}
//	
//	/**
//	 * 是否激活
//	 * @return true 是, false 否
//	 */
//	public boolean isActivite() {
//		return getState() == ConfigArtifact.ARTIFACT_STATE_ACTIVITE;
//	}
	
//	/**
//	 * 当任务完成
//	 * @return
//	 */
//	public void onFinishMission(int configId) {
//		this.missions.add(configId);
//	}
	
	/**
	 * 神器重铸
	 */
	public void onRecast() {
		this.setLevel(1);
		this.setExp(0);
		this.setRefineLv(1);
		this.setSkillLv(1);
		this.setHolySealLv(1);
	}
	
	/**
	 * 检测是否可以激活神器
	 */
	public boolean checkCanActivite() {
//		boolean bool = artifactMissionHelper.isAllReward();
//		if (bool) {//如果可以激活
//			//神器改变为激活状态 
//			setState(ConfigArtifact.ARTIFACT_STATE_ACTIVITE);
//		}
//		return bool;
		return false;
	}

	@Override
	public Object key() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String keyColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object cacheId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] indexs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] indexValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] props() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] propValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
