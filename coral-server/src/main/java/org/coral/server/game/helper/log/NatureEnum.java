package org.coral.server.game.helper.log;

/**
 * 资源类型, 资源描述
 * @auth Jeremy
 * @date 2019年6月28日下午3:55:10
 */
public enum NatureEnum {
	
	GM(-1, "GM"),
	Unknown(0, "未声明来源"),
	
//	//1:其他操作
//	CheckIn(1001, "签到"),
//	CheckInAdd(1002, "补签"),
//	LoginJoinCamp(1003, "登录加入阵营奖励"),
//	PlayerLevelUp(1004, "玩家升级"),
//	VipReward(1005, "Vip奖励"),
//	ExchargeReward(1006, "充值"),
//	PlayerBuyPower(1007, "购买体力"),
//	PlayerPubFamousBuy(1008, "购买魂玉"),
//	PlayerThumbUp(1009, "点赞"),
//	PlayerBuyRice(1010, "购买粮草"),
//	PlayerFlyChat(1011, "弹幕"),
//
//	//2:货币操作
//	Item(2001, "道具"),
//	Diamond(2002, "元宝"),
//	Gold(2003, "铜币"),
//	Mimeral(2004, "矿石"),
//	PubFamous(2005, "魂玉"),
//	Power(2006, "体力"),
//	ShopMilitary(2007, "军功"),
//	StarGhost(2008,"星魂"),
//	ShopBattle(2009,"战功"),
//	ShopJade(2010, "玉璧"),
//	PubNormal(2011,"招募令"),
//	HorseGhost(2012,"马魂"),
//	ShopTreasure(2013,"将魂"),
//	ShopRefresh(2014,"商店刷新次数"),
//	FamilyDevote(2015,"家族贡献"),
//	Exp(2016,"经验"),
//	FamilyExpDevote(2017,"家族战勋"),
//	DragonGhostStone(2018,"龙魂石"),
//	DragonGodStone(2019,"龙神石"),
//	DragonHorsePiece(2020,"龙马碎片"),
//	HorseWashPill(2021,"战马洗髓丸"),
//	HorseWakePill(2022,"战马觉醒丸"),
//	HorseGhostPiece(2023,"马魂碎片"),
//	InheritStone(2024,"传承晶石"),
//	InheritCount(2025,"传承令牌"),
//	Stamp(2026, "点卷"),
//	VipExp(2027, "VIP经验"),
//	//3:酒馆
//	GenNormalCard(3001, "酒馆抽良将"),
//	GenFamousCard(3002, "酒馆抽名将"),
//	GenInitCard(3003, "酒馆倒计时10连抽"),
//	GenScoreCard(3004, "酒馆积分10连抽"),
//	//4:商店
//	ShopBuyTreasure(4001,"秘宝商店购买"),
//	ShopBuyBattle(4002,"战功商店购买"),
//	ShopBuyJade(4003,"玉璧商店购买"),
//	ShopBuyMilitary(4004,"军功商店购买"),
//	ShopTreasureRefresh(4005,"秘宝商店刷新"),
//	ShopBuyFamily(4006,"家族商店购买"),
//	ShopBuyStable(4007,"马场商店购买"),
//	ShopBuyDiamond(4008,"元宝商店购买"),
//	ShopBuyLuckStar(4009,"吉星商店购买"),
//	ShopLuckStarRefresh(4010,"吉星商店刷新"),
//	//5:武将
//	HeroActiveStar(5001,"武将升星"),
//	HeroRelation(5002,"武将羁绊"),
//	HeroActiveTalent(5003,"武将星格激活"),
//	HeroObtain(5004,"武将获得"),
//	HeroRelive(5005,"武将重生"),
//	HeroGodActivite(5006,"激活神将"),
//	HeroRecruit(5007, "举贤榜"),
//	//6:背包
//	BagUse(6001, "背包使用道具"),
//	BagComposeEquip(6002, "背包合成装备"),
//	BagComposeHero(6003, "背包合成武将"),
//	BagComposeGem(6004, "背包合成宝石"),
//	BagDecomposeEquip(6005, "背包分解装备"),
//	BagDecomposeHero(6006, "背包分解武将"),
//	BagWearEquip(6007, "背包穿戴装备"),
//	BagUndressEquip(6008, "背包卸下装备"),
//	BagSell(6009, "背包出售物品"),
//	BagEquipStrengthen(6010, "背包装备强化"),
//	BagSettingGem(6011, "背包镶嵌宝石"),
//	BagUnSettingGem(6012, "背包卸下宝石"),
//	BagDecomposeHorse(6013, "背包马匹分解"),
//	BagComposeHorse(6014, "背包马匹合成"),
//	BagComposeHorseGhost(6015, "背包马魂合成"),
//	BagDecomposeHorseGhost(6016, "背包马魂分解"),
//	BagWakeupHorseGhost(6017, "背包马魂觉醒"),
//	BagSettingHorseGhost(6018, "背包马魂镶嵌"),
//	BagUnsettingHorseGhost(6019, "背包马魂卸下"),
//	BagHeroInherit(6020, "武将传承"),
//	BagEquipAwake(6021, "背包装备觉醒"),
//	BagEquipForge(6022, "背包装备锻造"),
//	BagInheritedEquip(6023, "装备传承"),
//	BagRecastEquip(6024, "精工炉回炉"),
//	BagWearSymbol(6025, "背包穿戴兵符"),
//	BagUndressSymbol(6026, "背包卸下兵符"),
//	BagSymbolRefine(6027, "背包兵符精炼"),
//	BagSymbolStarUp(6028, "背包兵符升星"),
//	BagSymbolWaken(6029, "背包兵符觉醒"),
//	BagSymbolActive(6030, "兵符铭文激活"),
//	BagSymbolDemolition(6031, "背包兵符拆解"),
//	BagSymbolDecompose(6032, "背包兵符分解"),
//	BagComposeSymbol(6033, "背包合成兵符"),
//	//7:祭拜
//	AltarGold(7001, "钱币祭祀"),
//	AltarEquip(7002, "装备祭祀"),
//	AltarGem(7003, "宝石祭祀"),
//	AltarGhost(7004, "星魂祭祀"),
//	//8:副本&皇城&擂台赛&竞技场
//	PVPOfficialFight(8001,"皇城挑战开始"),
//	PVPOfficialBuy(8002,"皇城购买次数"),
//	PVPContestFight(8003,"擂台赛挑战开始"),
//	PVPContestBuy(8004,"擂台次数购买"),
//	PVPContestRefresh(8005,"擂台赛刷新对手"),
//	PVPOfficialFightEnd(8006,"皇城挑战结束"),
//	PVPContestFightEnd(8007,"擂台挑战结束"),
//	StageFight(8008, "关卡副本挑战"),
//	StageSweep(8009, "关卡副本扫荡"),
//	StageBuyCount(8010, "关卡副本次数购买"),
//	StageBox(8011, "关卡副本宝箱"),
//	ArmStageFight(8012, "兵器副本挑战"),
//	ArmStageFightSweep(8013, "兵器副本扫荡"),
//	ArtifactStageFight(8014, "神兵副本挑战"),
//	ArtifactStageSweep(8015, "神兵副本扫荡"),
//	ArtifactStageBuy(8016, "神兵副本次数购买"),
//	GemCopyFight(8017, "宝石副本挑战"),
//	GemCopySweep(8018, "宝石副本扫荡"),
//	WusCopyFight(8019, "无双副本挑战"),
//	WusCopySweep(8020, "无双副本扫荡"),
//	SoulCopyFight(8021, "将魂副本挑战"),
//	CityCopySearchMap(8022, "攻城拔塞搜索地图"),
//	CityAggress(8023, "攻城拔塞掠夺"),
//	CityRecv(8024, "攻城拔塞收菜"),
//	SevenStarOver(8025, "七星台结算"),
//	SevenStarSweep(8026, "七星台扫荡"),
//	SevenStarBox(8027, "七星台宝箱奖励"),
//	Fortress(8028, "斩将夺宝"),//
//	DartRefreshQuality(8029, "运镖刷新品质"),
//	DartRob(8030, "运镖掠夺军资"),
//	DartDelivery(8031, "运镖运送军资"),
//	DartClearCdTime(8032, "运镖清除cd"),
//	WorldBossFight(8033, "世界Boss副本挑战"),
//	WorldBossBuy(8034, "世界Boss购买次数"),
//	BurningStageOver(8035, "火烧连营结算"),
//	BurningStageReceiveReward(8036, "火烧大本营奖励领取"),
//	AreanRefresh(8037, "竞技场刷新"),
//	AreanFight(8038, "竞技场结算"),
//	AreanBuy(8039, "竞技场购买"),
//	//9:任务
//	Mission(9001, "任务领取奖励"),
//	MissionFinish(9002, "任务完成"),
//	StrategyMission(9003, "锦囊任务领取奖励"),
//	TalentMissionFinish(9004, "天赋任务完成"),
//	TalentMission(9005, "天赋任务领取奖励"),
//	//10:活动
//	Activity(10001, "活动"),
//	RaceBet(10002,"赛马下注"),
//	RaceSettlement(10003,"赛马结算"),
//	//11:神兵系统
//	ArtifactCreate(11001, "神兵打造"),
//	ArtifactUpgrade(11002, "神兵精炼"),
//	ArtifactBrake(11003, "神兵突破"),
//	//12:建筑
//	MainHallBeginLevelUp(12001, "主城升级"),
//	MainHallAccelerateLevelUp(12002,"主城加速升级"),
//	MinefieldBeginLevelUp(12003,"矿场升级"),
//	MinefieldAccelerateLevelUp(12004,"矿场加速升级"),
//	MinefieldProduce(12005,"矿场产出"),
//	FolkhousesBeginLevelUp(12006,"民居升级"),
//	FolkhousesAccelerateLevelUp(12007,"民居加速升级"),
//	FolkhousesProduce(12008,"民居产出"),
//	BarracksTechAccelLevelUp(12009,"兵营科技加速升级"),
//	BarracksTechBeginLevelUp(12010,"兵营科技升级"),
//	BarracksAccelerateLevelUp(12011,"兵营加速升级"),
//	BarracksBeginLevelUp(12012,"兵营升级"),
//	HorseLevelUp(12013,"马场升级"),
//	HorseAccelerateLevelUp(12014,"马场加速升级"),
//	VaultProduce(12015,"金库产出"),
//	//13:邮件
//	MailReward(13001, "邮件奖励"),
//	MailSend(13002, "邮件发送"),
//	MailRead(13003, "邮件查看"),
//	//14:玩家登陆登出
//	Login(14001, "角色登陆"),
//	Logout(14002, "角色登出"),
//	Register(14004, "角色注册"),
//	Excharge(14005, "角色充值成功"),
//	//15:家族
//	FamilyCreate(15001, "创建家族"),
//	FamilyAltar(15002, "家族祭祀"),
//	FamilyWusCopyFight(15003, "家族无双副本挑战"),
//	FamilyWusCopySweep(15004, "家族无双副本扫荡"),
//	FamilyFightReward(15005, "家族战奖励"),
//	FamilyFightBuilding(15006, "家族战建筑奖励"),
//	FamilyDartCD(15007, "家族运镖CD"),
//	FamilyJoin(15008, "申请家族"),
//	FamilyLeave(15009, "退出家族"),
//	FamilyApproval(15010, "审批申请"),
//	FamilyMail(15011, "邮件发送"),
//	FamilyNotice(15012, "修改公告"),
//	FamilyDeclaration(15013, "修改宣言"),
//	FamilyDartStation(15014, "家族运镖驻防"),
//	FamilyDartCancel(15015, "家族运镖取消驻防"),
//	FamilyFightSignUp(15016, "家族战报名"),
//	FamilyFightStation(15017, "家族战布放"),
//	FamilyFightCancel(15018, "家族战取消布放"),
//	FamilyFightAkeyStation(15019, "家族战一键布放"),
//	FamilyFightAkeyCancelStation(15020, "家族战一键取消布放"),
//	FamilyFightStart(15021, "家族战开战"),
//	FamilyDartFight(15022, "家族运镖掠夺"),
//	FamilyBuilding(15023, "家族建设"),
//	FamilyBossFight(15024, "家族Boss副本挑战"),
//	FamilyBossRevive(15025, "家族Boss副本复活"),
//	//16:马场
//	StableGoldCall(16001, "马场普通召唤"),
//	StableDiamondCall(16002, "马场高级召唤"),
////	StableWildCall(16003, "马场野马生产"),
////	StableIdentify(16004, "马场鉴定"),
//	//17:统计相关
//	onlinePeople(17001, "同时在线人数"),
//	//18:房地产
//	HouseBuy(18001,"购买房子"),
//	HouseLottery(18002,"房子抽奖"),
//	CallMaidservant(18003,"婢女召唤"),
//	MaidservantOutPut(18004,"婢女产出"),
//	HouseLock(18005,"房子上锁"),
//	HallLottery(18006,"大殿抽奖"),
//	HallSnatch(18007,"大殿抢夺"),
//	EditDefend(18008,"修改大殿驻防"),
//	CancelDefend(18009,"取消大殿驻防"),
//	//19:国战
//	NationalWarUseRice(19001, "消耗粮草"),
//	//20:副本欺诈
//	PlayerStageCheat(20001, "副本欺诈"),
//	NetWorkCheat(20002, "网络攻击"),
//	//21:军机处
//	MilitaryReinforceActive(21001, "激活援军"),
//	MilitaryTalentUp(21002, "升级援军天赋"),
//	MilitaryDivine(21003, "吉星"),
//	MilitaryTalentReset(21003, "重置援军天赋"),
	;
	
	private int logType;
	private String desc;
	private NatureEnum(int logType, String desc) {
		this.logType = logType;
		this.desc = desc;
	}
	
	public int getLogType() {
		return logType;
	}
	public void setLogType(int logType) {
		this.logType = logType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	 * 根据日志类型, 获取日志枚举
	 * @param logType
	 * @return  
	 * @return NatureEnum  
	 * @date 2019年7月1日下午1:34:07
	 */
	public static NatureEnum getEnum(int logType) {
		for (NatureEnum  nEnum : NatureEnum.values()) {
			if (nEnum.logType == logType) {
				return nEnum;
			}
		}
		return null;
	}
	
}
