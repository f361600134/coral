package org.coral.server.game.module.base;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;

/**
* PlayerPo
* @author Jeremy
*/
@PO(name = "k_player")
public abstract class PlayerPo extends BasePo {

	public static final String PROP_PLAYERID = "playerId";
	public static final String PROP_ACCOUNTNAME = "accountName";
	public static final String PROP_PLAYERNAME = "playerName";
	public static final String PROP_SEX = "sex";
	public static final String PROP_LEVEL = "level";
	public static final String PROP_EXP = "exp";
	public static final String PROP_JOBTYPE = "jobType";
	public static final String PROP_POWER = "power";
	public static final String PROP_PHYSICALPOWER = "physicalPower";
	public static final String PROP_INGOT = "ingot";
	public static final String PROP_COPPER = "copper";
	public static final String PROP_GIFTGOLD = "giftGold";
	public static final String PROP_NATION = "nation";
	public static final String PROP_UNIONID = "unionId";
	public static final String PROP_SCENEID = "sceneId";
	public static final String PROP_POSITIONX = "positionX";
	public static final String PROP_POSITIONY = "positionY";
	public static final String PROP_WALKSPEED = "walkSpeed";
	public static final String PROP_VIP = "vip";
	public static final String PROP_LASTLOGINTIME = "lastLoginTime";
	public static final String PROP_PREVOFFLINETIME = "prevOfflineTime";
	public static final String PROP_REGTIME = "regTime";
	public static final String PROP_FORBIDENSPEAK = "forbidenSpeak";
	public static final String PROP_FORBITENDTIME = "forbitEndTime";
	public static final String PROP_OFFSETX = "offsetX";
	public static final String PROP_OFFSETY = "offsetY";
	public static final String PROP_BAGCELLNUM = "bagCellNum";
	public static final String PROP_DEPOTCELLNUM = "depotCellNum";
	public static final String PROP_PLAYERNOWTITLE = "playerNowTitle";
	public static final String PROP_REPUTATION = "reputation";
	public static final String PROP_ZHENYUAN = "zhenYuan";
	public static final String PROP_SHOUHUN = "shouHun";
	public static final String PROP_GREENSOUL = "greenSoul";
	public static final String PROP_BLUESOUL = "blueSoul";
	public static final String PROP_PURPLESOUL = "purpleSoul";
	public static final String PROP_ORANGESOUL = "orangeSoul";
	public static final String PROP_PAYTOTAL = "payTotal";
	public static final String PROP_SUMMONINTEGRAL = "summonIntegral";
	public static final String PROP_FRIENDSHIPPOINT = "friendshipPoint";
	public static final String PROP_PROPHETCRYSTAL = "prophetCrystal";
	public static final String PROP_PROPHETCARD = "prophetCard";
	public static final String PROP_INFORMATION = "information";
	public static final String PROP_EXPHERO = "expHero";
	public static final String PROP_ARENAPRESTIGE = "arenaPrestige";
	public static final String PROP_ARENAINTEGARL = "arenaIntegarl";
	public static final String PROP_INITSERVERID = "initServerId";
	public static final String PROP_CURSERVERID = "curServerId";
	
	protected long playerId;//玩家ID
	protected String accountName;//玩家帐号名称
	protected String playerName;//角色名称（昵称）
	protected int sex;//性别(1:男,2:女)
	protected int level;//角色等级
	protected int exp;//角色经验
	protected int jobType;//职业类型
	protected long power;//战斗力
	protected int physicalPower;//体力
	protected int ingot;//当前拥有元宝数
	protected long copper;//游戏币
	protected int giftGold;//点券
	protected int nation;//国家:1为魏2为蜀3为吴
	protected long unionId;//帮派ID
	protected int sceneId;//场景ID,1为新手村
	protected int positionX;//图地坐标X
	protected int positionY;//地图坐标Y
	protected int walkSpeed;//人物行走速度
	protected int vip;//VIP等级
	protected int lastLoginTime;//最近一次登录时间
	protected int prevOfflineTime;//最近一次离线时间
	protected int regTime;//注册时间
	protected long forbidenSpeak;//禁言时间
	protected int forbitEndTime;//封号结束时间
	protected int offsetX;//地图偏移X
	protected int offsetY;//地图偏移Y
	protected int bagCellNum;//背包已开放格子数
	protected int depotCellNum;//仓库格子数量
	protected int playerNowTitle;//玩家选择的称号
	protected int reputation;//声望
	protected int zhenYuan;//真元
	protected int shouHun;//兽魂
	protected int greenSoul;//绿武魂
	protected int blueSoul;//蓝武魂
	protected int purpleSoul;//紫武魂
	protected int orangeSoul;//橙武魂
	protected int payTotal;//充值元宝
	protected int summonIntegral;//召唤积分
	protected int friendshipPoint;//友情点
	protected int prophetCrystal;//先知结晶
	protected int prophetCard;//天命卡牌
	protected int information;//远航情报
	protected int expHero;//英雄经验
	protected int arenaPrestige;//竞技场声望
	protected int arenaIntegarl;//竞技场积分
	protected int initServerId;//初始服务器id
	protected int curServerId;//当前服务器id
	
	public PlayerPo(){
		this.accountName = "";
		this.playerName = "";
	}
	
	/** 玩家ID **/
	public long getPlayerId(){
		return this.playerId;
	}
	
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/** 玩家帐号名称 **/
	public String getAccountName(){
		return this.accountName;
	}
	
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}
	
	/** 角色名称（昵称） **/
	public String getPlayerName(){
		return this.playerName;
	}
	
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	
	/** 性别(1:男,2:女) **/
	public int getSex(){
		return this.sex;
	}
	
	public void setSex(int sex){
		this.sex = sex;
	}
	
	/** 角色等级 **/
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	/** 角色经验 **/
	public int getExp(){
		return this.exp;
	}
	
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/** 职业类型 **/
	public int getJobType(){
		return this.jobType;
	}
	
	public void setJobType(int jobType){
		this.jobType = jobType;
	}
	
	/** 战斗力 **/
	public long getPower(){
		return this.power;
	}
	
	public void setPower(long power){
		this.power = power;
	}
	
	/** 体力 **/
	public int getPhysicalPower(){
		return this.physicalPower;
	}
	
	public void setPhysicalPower(int physicalPower){
		this.physicalPower = physicalPower;
	}
	
	/** 当前拥有元宝数 **/
	public int getIngot(){
		return this.ingot;
	}
	
	public void setIngot(int ingot){
		this.ingot = ingot;
	}
	
	/** 游戏币 **/
	public long getCopper(){
		return this.copper;
	}
	
	public void setCopper(long copper){
		this.copper = copper;
	}
	
	/** 点券 **/
	public int getGiftGold(){
		return this.giftGold;
	}
	
	public void setGiftGold(int giftGold){
		this.giftGold = giftGold;
	}
	
	/** 国家:1为魏2为蜀3为吴 **/
	public int getNation(){
		return this.nation;
	}
	
	public void setNation(int nation){
		this.nation = nation;
	}
	
	/** 帮派ID **/
	public long getUnionId(){
		return this.unionId;
	}
	
	public void setUnionId(long unionId){
		this.unionId = unionId;
	}
	
	/** 场景ID,1为新手村 **/
	public int getSceneId(){
		return this.sceneId;
	}
	
	public void setSceneId(int sceneId){
		this.sceneId = sceneId;
	}
	
	/** 图地坐标X **/
	public int getPositionX(){
		return this.positionX;
	}
	
	public void setPositionX(int positionX){
		this.positionX = positionX;
	}
	
	/** 地图坐标Y **/
	public int getPositionY(){
		return this.positionY;
	}
	
	public void setPositionY(int positionY){
		this.positionY = positionY;
	}
	
	/** 人物行走速度 **/
	public int getWalkSpeed(){
		return this.walkSpeed;
	}
	
	public void setWalkSpeed(int walkSpeed){
		this.walkSpeed = walkSpeed;
	}
	
	/** VIP等级 **/
	public int getVip(){
		return this.vip;
	}
	
	public void setVip(int vip){
		this.vip = vip;
	}
	
	/** 最近一次登录时间 **/
	public int getLastLoginTime(){
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(int lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
	
	/** 最近一次离线时间 **/
	public int getPrevOfflineTime(){
		return this.prevOfflineTime;
	}
	
	public void setPrevOfflineTime(int prevOfflineTime){
		this.prevOfflineTime = prevOfflineTime;
	}
	
	/** 注册时间 **/
	public int getRegTime(){
		return this.regTime;
	}
	
	public void setRegTime(int regTime){
		this.regTime = regTime;
	}
	
	/** 禁言时间 **/
	public long getForbidenSpeak(){
		return this.forbidenSpeak;
	}
	
	public void setForbidenSpeak(long forbidenSpeak){
		this.forbidenSpeak = forbidenSpeak;
	}
	
	/** 封号结束时间 **/
	public int getForbitEndTime(){
		return this.forbitEndTime;
	}
	
	public void setForbitEndTime(int forbitEndTime){
		this.forbitEndTime = forbitEndTime;
	}
	
	/** 地图偏移X **/
	public int getOffsetX(){
		return this.offsetX;
	}
	
	public void setOffsetX(int offsetX){
		this.offsetX = offsetX;
	}
	
	/** 地图偏移Y **/
	public int getOffsetY(){
		return this.offsetY;
	}
	
	public void setOffsetY(int offsetY){
		this.offsetY = offsetY;
	}
	
	/** 背包已开放格子数 **/
	public int getBagCellNum(){
		return this.bagCellNum;
	}
	
	public void setBagCellNum(int bagCellNum){
		this.bagCellNum = bagCellNum;
	}
	
	/** 仓库格子数量 **/
	public int getDepotCellNum(){
		return this.depotCellNum;
	}
	
	public void setDepotCellNum(int depotCellNum){
		this.depotCellNum = depotCellNum;
	}
	
	/** 玩家选择的称号 **/
	public int getPlayerNowTitle(){
		return this.playerNowTitle;
	}
	
	public void setPlayerNowTitle(int playerNowTitle){
		this.playerNowTitle = playerNowTitle;
	}
	
	/** 声望 **/
	public int getReputation(){
		return this.reputation;
	}
	
	public void setReputation(int reputation){
		this.reputation = reputation;
	}
	
	/** 真元 **/
	public int getZhenYuan(){
		return this.zhenYuan;
	}
	
	public void setZhenYuan(int zhenYuan){
		this.zhenYuan = zhenYuan;
	}
	
	/** 兽魂 **/
	public int getShouHun(){
		return this.shouHun;
	}
	
	public void setShouHun(int shouHun){
		this.shouHun = shouHun;
	}
	
	/** 绿武魂 **/
	public int getGreenSoul(){
		return this.greenSoul;
	}
	
	public void setGreenSoul(int greenSoul){
		this.greenSoul = greenSoul;
	}
	
	/** 蓝武魂 **/
	public int getBlueSoul(){
		return this.blueSoul;
	}
	
	public void setBlueSoul(int blueSoul){
		this.blueSoul = blueSoul;
	}
	
	/** 紫武魂 **/
	public int getPurpleSoul(){
		return this.purpleSoul;
	}
	
	public void setPurpleSoul(int purpleSoul){
		this.purpleSoul = purpleSoul;
	}
	
	/** 橙武魂 **/
	public int getOrangeSoul(){
		return this.orangeSoul;
	}
	
	public void setOrangeSoul(int orangeSoul){
		this.orangeSoul = orangeSoul;
	}
	
	/** 充值元宝 **/
	public int getPayTotal(){
		return this.payTotal;
	}
	
	public void setPayTotal(int payTotal){
		this.payTotal = payTotal;
	}
	
	/** 召唤积分 **/
	public int getSummonIntegral(){
		return this.summonIntegral;
	}
	
	public void setSummonIntegral(int summonIntegral){
		this.summonIntegral = summonIntegral;
	}
	
	/** 友情点 **/
	public int getFriendshipPoint(){
		return this.friendshipPoint;
	}
	
	public void setFriendshipPoint(int friendshipPoint){
		this.friendshipPoint = friendshipPoint;
	}
	
	/** 先知结晶 **/
	public int getProphetCrystal(){
		return this.prophetCrystal;
	}
	
	public void setProphetCrystal(int prophetCrystal){
		this.prophetCrystal = prophetCrystal;
	}
	
	/** 天命卡牌 **/
	public int getProphetCard(){
		return this.prophetCard;
	}
	
	public void setProphetCard(int prophetCard){
		this.prophetCard = prophetCard;
	}
	
	/** 远航情报 **/
	public int getInformation(){
		return this.information;
	}
	
	public void setInformation(int information){
		this.information = information;
	}
	
	/** 英雄经验 **/
	public int getExpHero(){
		return this.expHero;
	}
	
	public void setExpHero(int expHero){
		this.expHero = expHero;
	}
	
	/** 竞技场声望 **/
	public int getArenaPrestige(){
		return this.arenaPrestige;
	}
	
	public void setArenaPrestige(int arenaPrestige){
		this.arenaPrestige = arenaPrestige;
	}
	
	/** 竞技场积分 **/
	public int getArenaIntegarl(){
		return this.arenaIntegarl;
	}
	
	public void setArenaIntegarl(int arenaIntegarl){
		this.arenaIntegarl = arenaIntegarl;
	}
	
	/** 初始服务器id **/
	public int getInitServerId(){
		return this.initServerId;
	}
	
	public void setInitServerId(int initServerId){
		this.initServerId = initServerId;
	}
	
	/** 当前服务器id **/
	public int getCurServerId(){
		return this.curServerId;
	}
	
	public void setCurServerId(int curServerId){
		this.curServerId = curServerId;
	}
	
	
	@Override
	public String toString() {
		return "Player [playerId= "+ playerId +", accountName= "+ accountName +", playerName= "+ playerName +", sex= "+ sex +", level= "+ level
				 +", exp= "+ exp +", jobType= "+ jobType +", power= "+ power +", physicalPower= "+ physicalPower +", ingot= "+ ingot
				 +", copper= "+ copper +", giftGold= "+ giftGold +", nation= "+ nation +", unionId= "+ unionId +", sceneId= "+ sceneId
				 +", positionX= "+ positionX +", positionY= "+ positionY +", walkSpeed= "+ walkSpeed +", vip= "+ vip +", lastLoginTime= "+ lastLoginTime
				 +", prevOfflineTime= "+ prevOfflineTime +", regTime= "+ regTime +", forbidenSpeak= "+ forbidenSpeak +", forbitEndTime= "+ forbitEndTime +", offsetX= "+ offsetX
				 +", offsetY= "+ offsetY +", bagCellNum= "+ bagCellNum +", depotCellNum= "+ depotCellNum +", playerNowTitle= "+ playerNowTitle +", reputation= "+ reputation
				 +", zhenYuan= "+ zhenYuan +", shouHun= "+ shouHun +", greenSoul= "+ greenSoul +", blueSoul= "+ blueSoul +", purpleSoul= "+ purpleSoul
				 +", orangeSoul= "+ orangeSoul +", payTotal= "+ payTotal +", summonIntegral= "+ summonIntegral +", friendshipPoint= "+ friendshipPoint +", prophetCrystal= "+ prophetCrystal
				 +", prophetCard= "+ prophetCard +", information= "+ information +", expHero= "+ expHero +", arenaPrestige= "+ arenaPrestige +", arenaIntegarl= "+ arenaIntegarl
				 +", initServerId= "+ initServerId +", curServerId= "+ curServerId+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`playerId`",
		"`accountName`",
		"`playerName`",
		"`sex`",
		"`level`",
		"`exp`",
		"`jobType`",
		"`power`",
		"`physicalPower`",
		"`ingot`",
		"`copper`",
		"`giftGold`",
		"`nation`",
		"`unionId`",
		"`sceneId`",
		"`positionX`",
		"`positionY`",
		"`walkSpeed`",
		"`vip`",
		"`lastLoginTime`",
		"`prevOfflineTime`",
		"`regTime`",
		"`forbidenSpeak`",
		"`forbitEndTime`",
		"`offsetX`",
		"`offsetY`",
		"`bagCellNum`",
		"`depotCellNum`",
		"`playerNowTitle`",
		"`reputation`",
		"`zhenYuan`",
		"`shouHun`",
		"`greenSoul`",
		"`blueSoul`",
		"`purpleSoul`",
		"`orangeSoul`",
		"`payTotal`",
		"`summonIntegral`",
		"`friendshipPoint`",
		"`prophetCrystal`",
		"`prophetCard`",
		"`information`",
		"`expHero`",
		"`arenaPrestige`",
		"`arenaIntegarl`",
		"`initServerId`",
		"`curServerId`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getPlayerId(),
		getAccountName(),
		getPlayerName(),
		getSex(),
		getLevel(),
		getExp(),
		getJobType(),
		getPower(),
		getPhysicalPower(),
		getIngot(),
		getCopper(),
		getGiftGold(),
		getNation(),
		getUnionId(),
		getSceneId(),
		getPositionX(),
		getPositionY(),
		getWalkSpeed(),
		getVip(),
		getLastLoginTime(),
		getPrevOfflineTime(),
		getRegTime(),
		getForbidenSpeak(),
		getForbitEndTime(),
		getOffsetX(),
		getOffsetY(),
		getBagCellNum(),
		getDepotCellNum(),
		getPlayerNowTitle(),
		getReputation(),
		getZhenYuan(),
		getShouHun(),
		getGreenSoul(),
		getBlueSoul(),
		getPurpleSoul(),
		getOrangeSoul(),
		getPayTotal(),
		getSummonIntegral(),
		getFriendshipPoint(),
		getProphetCrystal(),
		getProphetCard(),
		getInformation(),
		getExpHero(),
		getArenaPrestige(),
		getArenaIntegarl(),
		getInitServerId(),
		getCurServerId(),
		};
	}

	@Override
	public String[] ids() {
		return new String[] {
			"`PlayerId`",
		};
	}
	
	@Override
	public Object[] idValues() {
		return new Object[] {
			playerId,
		};
	}
	
	@Override
	public Object key() {
		// 第一主键的数据值, 例如playerId
		//return getId();
		return getPlayerId();
	}

	@Override
	public String cacheId() {
		// 缓存的二级ID, 如果不是一对多关系的return null即可
		//return String.valueOf(getId());
		return String.valueOf(getPlayerId());
	}

	@Override
	public String keyColumn() {
		// 第一主键的数据库列名
		return ids()[0];
	}
	
}
