package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigMission;
import org.coral.server.utils.FileUtilitys;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigMissionMgr {

    private static final Logger LOGGER = LogManager.getLogger(ConfigMissionMgr.class);
	
	private static String filename = ServerConstant.JsonPath + "ConfigMission.json";
	
	private static Map<Integer, ConfigMission> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigMission> list = JSON.parseArray(content, ConfigMission.class);
		
		Map<Integer, ConfigMission> temps = Maps.newHashMap();
		for(ConfigMission config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigMission getConfig(int id) {
		ConfigMission data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigMission! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
//	//任务状态:0=未接取;1=已接取未完成;2=已完成未领取;3=已完成已领取
//	public static final int State_NoRecv = 0;
//	public static final int State_NoComplete = 1;
//	public static final int State_NoReward = 2;
//	public static final int State_Completed = 3;
//	
//	//每天刷新的任务
//	private static List<ConfigMission> dailyMissions;
//	
//	///////////////  接取   ///////////////////
//	//上一条任务:受影响领取的下一条任务
//	private static Map<Integer, Integer> missionRecvEffects;
//	//VIP接取相关的任务
//	private static List<ConfigMission> vipRecvMissions;
//	//月卡接取相关的任务
//	private static List<ConfigMission> monthCardRecvMissions;
//	//至尊卡接取相关的任务
//	private static List<ConfigMission> supremeRecvMissions;
//	
//	//时钟接取相关的任务
//	private static Map<Integer, List<Integer>> hourRecvMissions;	//hour:mission ids
//	//时钟关闭相关的任务
//	private static Map<Integer, List<Integer>> hourCloseMissions;	//hour:mission ids
//	
//	///////////////  完成   ///////////////////
//	
//	public static final int CompleteType_PlayerLevel = 53;
//	public static final int CompleteType_MainHallLevel = 54;
//	public static final int CompleteType_BarracksLevel = 55;
//	public static final int CompleteType_FolkHouseLevel = 56;
//	public static final int CompleteType_MineFieldLevel = 57;
//	
//	public static final int CompleteType_StagePass = 25;
//	public static final int CompleteType_AnyStagePass = 2;
//	public static final int CompleteType_EliteStagePass = 5;
//	public static final int CompleteType_PurgatoryStagePass = 21;
//	
//	public static final int CompleteType_GemCopyPass = 9;
//	public static final int CompleteType_ArmCopyPass = 11;
//	public static final int CompleteType_ArmCopyPassHero = 50;
//	public static final int CompleteType_WusStagePass = 12;
//	public static final int CompleteType_SoulStagePass = 14;
//	
//	public static final int CompleteType_TownResource = 1;
//	public static final int CompleteType_TreasureShopBuy = 6;
////	public static final int CompleteType_TreasureShopBuyStar = 49;
//	public static final int CompleteType_PubGenCard = 10;
//	
//	public static final int CompleteType_HeroTalent = 26;
//	public static final int CompleteType_HeroAdd = 48;
//	
//	public static final int CompleteType_EquipStrengthen = 27;
//	public static final int CompleteType_EquipQuality = 47;
//	
//	
//	public static final int CompleteType_FamilyDartRob = 70;
//	
//	public static final int CompleteType_Arena = 8;
//	public static final int CompleteType_Official = 7;
//	public static final int CompleteType_OfficialGrade = 58;
//	public static final int CompleteType_OfficialId = 59;
//	
//	public static final int completeType_StableCall = 19;
//	public static final int completeType_SevenStar = 16;
//	public static final int completeType_FamilyWus = 20;
//	public static final int completeType_ArtifactStage = 23;
//	
//	
//	//按完成类型分类的任务
//	private static Map<Integer, List<Integer>> typeCompleteMissions;	//completeType:mission ids
//	
	public static void analyse(){}
//		List<ConfigMission> tempDailyMissions = Lists.newArrayList();
//		Map<Integer, Integer> tempMissionEffects = Maps.newHashMap();
//		List<ConfigMission> tempVipMissions = Lists.newArrayList();
//		List<ConfigMission> tempMonthCardMissions = Lists.newArrayList();
//		List<ConfigMission> tempSupremeRecvMissions = Lists.newArrayList();
//		Map<Integer, List<Integer>> tempHourRecvMissions = Maps.newHashMap();
//		Map<Integer, List<Integer>> tempHourCloseMissions = Maps.newHashMap();
//		Map<Integer, List<Integer>> tempTypeCompleteMissions = Maps.newHashMap();
//		
//		for(ConfigMission config : maps.values())
//		{
//			if(config.getDuration()==2)
//				tempDailyMissions.add(config);
//			
//			if(config.getMissionLimit()>0)
//				tempMissionEffects.put(config.getMissionLimit(), config.getID());
//			
//			if(config.getOtherLimitType()==1)
//				tempVipMissions.add(config);
//			if(config.getOtherLimitType()==2)
//				tempMonthCardMissions.add(config);
//			if(config.getOtherLimitType()==4)
//				tempSupremeRecvMissions.add(config);
//			if(config.getOtherLimitType()==3)
//			{
//				//接取的时间
//				List<Integer> recvs = tempHourRecvMissions.get(config.getOtherLimitValue().get(0));
//				if(recvs==null)
//				{
//					recvs = Lists.newArrayList();
//					tempHourRecvMissions.put(config.getOtherLimitValue().get(0), recvs);
//				}
//				recvs.add(config.getID());
//				//关闭的时间
//				List<Integer> closes = tempHourCloseMissions.get(config.getOtherLimitValue().get(1));
//				if(closes==null)
//				{
//					closes = Lists.newArrayList();
//					tempHourCloseMissions.put(config.getOtherLimitValue().get(1), closes);
//				}
//				closes.add(config.getID());
//			}
//			
//			if(config.getCompleteType()>0)
//			{
//				List<Integer> completes = tempTypeCompleteMissions.get(config.getCompleteType());
//				if(completes==null)
//				{
//					completes = Lists.newArrayList();
//					tempTypeCompleteMissions.put(config.getCompleteType(), completes);
//				}
//				completes.add(config.getID());
//			}
//		}
//		dailyMissions = tempDailyMissions;
//		missionRecvEffects = tempMissionEffects;
//		vipRecvMissions = tempVipMissions;
//		monthCardRecvMissions = tempMonthCardMissions;
//		supremeRecvMissions = tempSupremeRecvMissions;
//		hourRecvMissions = tempHourRecvMissions;
//		hourCloseMissions = tempHourCloseMissions;
//		typeCompleteMissions = tempTypeCompleteMissions;
//	}
//	
	public static void complete(){
	}
//	
//	/**
//	 * 获取所有配置任务列表
//	 */
//	public static Collection<ConfigMission> getAllMissions()
//	{
//		return maps.values();
//	}
//	/**
//	 * 获取每日任务列表
//	 */
//	public static List<ConfigMission> getDailyMissions() {
//		return dailyMissions;
//	}
//	/**
//	 * 获取任务影响的其它任务接受
//	 */
//	public static Integer getMissionRecvEffects(int missionId) {
//		return missionRecvEffects.getOrDefault(missionId, 0);
//	}
//	/**
//	 * 获取VIP影响的接取任务列表
//	 */
//	public static List<ConfigMission> getVipRecvMissions() {
//		return vipRecvMissions;
//	}
//	/**
//	 * 获取月卡影响的接取任务列表
//	 */
//	public static List<ConfigMission> getMonthCardRecvMissions() {
//		return monthCardRecvMissions;
//	}
//	/**
//	 * 获取至尊卡影响的接取任务列表
//	 */
//	public static List<ConfigMission> getSupremeRecvMissions() {
//		return supremeRecvMissions;
//	}
//	
//	/**
//	 * 获取时钟小时影响的接取任务列表
//	 */
//	public static List<Integer> getHourRecvMissions(int hour) {
//		return hourRecvMissions.get(hour);
//	}
//	/**
//	 * 获取时钟小时影响的关闭任务列表
//	 */
//	public static List<Integer> getHourCloseMissions(int hour) {
//		return hourCloseMissions.get(hour);
//	}
//	
//	/**
//	 * 获取指定完成类型的任务列表
//	 */
//	public static List<Integer> getTypeCompleteMissions(int type) {
//		return typeCompleteMissions.get(type);
//	}
	/////////UserDefine End/////////////
	
}
