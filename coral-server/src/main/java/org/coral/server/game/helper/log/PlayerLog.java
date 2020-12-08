package org.coral.server.game.helper.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.game.module.player.domain.Player;

import java.util.Map;

public class PlayerLog {

	private static final Logger logger = LogManager.getLogger(PlayerLog.class);

//	/**
//	 * 道具追踪 logType,日期,角色id,角色名
//	 * ,输入账号,唯一账号,物品唯一ID,物品配置ID,物品名称,产出/消耗,功能点,变化数量,变化前数量,变化后数量
//	 * 
//	 * @param player
//	 * @param action
//	 * @param logType
//	 * @param itemId
//	 * @param itemConfigId
//	 * @param number
//	 * @param afterNumber
//	 * @return void
//	 * @date 2019年6月25日下午6:17:13
//	 */
//	public static void obtainItem(final Player player, String action, int logType, int itemId, int itemConfigId, int number, int afterNumber) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(logType).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			////
//			builder.append(itemId).append(",");// 物品唯一ID
//			builder.append(itemConfigId).append(",");// 物品配置ID
//			builder.append(ConfigGoodMgr.getItemName(itemConfigId)).append(",");// 物品名称
//			builder.append((number > 0 ? "1" : "2")).append(",");// 产出/消耗
//			builder.append(action).append(",");// 功能点
//			builder.append(number).append(",");// 变化数量
//			builder.append((afterNumber - number)).append(",");// 变化前数量
//			builder.append(afterNumber).append(",");// 变化后数量
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(logType), builder.toString());
//		} catch (Exception e) {
//			logger.error("obtainItem error", e);
//		}
//	}
//
//	
	/**
	 * 增加资源 logType,日期,角色id,角色名,输入账号,唯一账号,物品唯一ID,物品配置ID,物品名称,产出/消耗,功能点,变化数量,变化前数量,变化后数量
	 * 
	 * @param playerContext
	 *            玩家对象, 获取资源
	 * @param action
	 *            操作类型
	 * @param logType
	 *            日志类型
	 * @param goodId
	 *            货币id, 表示元宝, 金币, 将魂等货币id
	 * @param number
	 *            增加 or 减少数量, 默认正数为增加, 负数为减少
	 * @param beforeNumber
	 *            增加前的数量
	 * @return void
	 * @date 2019年6月20日下午5:18:24
	 */
	public static void obtainReource(final Player player, String source, int logType, int itemConfigId, int number, int afterNumber) {
		try {
			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(logType).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			////
//			builder.append(0).append(",");// 物品唯一ID
//			builder.append(itemConfigId).append(",");// 物品配置ID
//			builder.append(ConfigGoodMgr.getItemName(itemConfigId)).append(",");// 物品名称
//			builder.append((number > 0 ? "1" : "2")).append(",");// 产出/消耗
//			
//			builder.append(action).append(",");// 功能点
//			builder.append(number).append(",");// 变化数量
//			builder.append((afterNumber - number)).append(",");// 变化前数量
//			builder.append(afterNumber).append(",");// 变化后数量
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(logType), builder.toString());
		} catch (Exception e) {
			logger.error("obtainReource error", e);
		}
	}
//
//	/**
//	 * 酒馆日志
//	 * logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品配置ID,物品名称,消耗数量,消耗前数量,消耗后数量,获得(名字1*数量1|名字2*数量2)
//	 * 
//	 * @param player
//	 * @param action
//	 * @param logType
//	 * @param itemConfigId
//	 * @param number
//	 * @param afterNumber
//	 * @param rewardMap
//	 * @return void
//	 * @date 2019年6月28日下午1:12:35
//	 */
//	public static void logPub(final Player player, NatureEnum nEnum, int itemConfigId, int number, int afterNumber, Map<Integer, Integer> rewardMap) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(itemConfigId).append(",");// 物品配置ID
//			builder.append(ConfigGoodMgr.getItemName(itemConfigId)).append(",");// 物品名称
//			builder.append(number).append(",");// 消耗数量
//			builder.append((afterNumber - number)).append(",");// 消耗前数量
//			builder.append(afterNumber).append(",");// 消耗后数量
//			builder.append(SourceNature.itemMapToStr(rewardMap)).append(",");// 获得(名字1*数量1)
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logPub error", e);
//		}
//	}
//
//	/**
//	 * 商店日志
//	 * logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品配置ID,物品名称,消耗数量,消耗前数量,消耗后数量,获得(名字1*数量),剩余刷新次数
//	 * 
//	 * @param player
//	 * @param action
//	 * @param logType
//	 * @param itemConfigId
//	 * @param number
//	 * @param afterNumber
//	 * @param rewardMap
//	 * @return void
//	 * @date 2019年6月28日下午1:13:58
//	 */
//	public static void logShop(final Player player, NatureEnum nEnum, int itemConfigId, int number, int afterNumber, int refreshCount, String rewardMapStr) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(itemConfigId).append(",");// 物品配置ID
//			builder.append(ConfigGoodMgr.getItemName(itemConfigId)).append(",");// 物品名称
//			builder.append(number).append(",");// 消耗数量
//			builder.append((afterNumber - number)).append(",");// 消耗前数量
//			builder.append(afterNumber).append(",");// 消耗后数量
//			builder.append(rewardMapStr).append(",");// 获得(名字1*数量1)
//			builder.append(refreshCount).append(",");// 刷新次数
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logShop error", e);
//		}
//	}
//
//	/**
//	 * 背包日志 logType,日期,角色id,角色名,行为,消耗物品(名字*数量),获得物品(名字1*数量1)
//	 * 
//	 * @param player
//	 * @param action
//	 * @param logType
//	 * @param costItemStr
//	 *            消耗物品
//	 * @param rewardMapStr
//	 *            获得物品
//	 * @return void
//	 * @date 2019年6月28日下午3:40:59
//	 */
//	public static void logBag(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, int param1) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");//logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");//日期
//			builder.append(player.getDbPlayer().getId()).append(",");//角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");//角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");//输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");//唯一账号
//			builder.append(nEnum.getDesc()).append(",");//行为
//			////
//			builder.append(costItemStr).append(",");//消耗物品
//			builder.append(rewardMapStr).append(",");//获得物品
//			builder.append(param1).append(",");// 参数1
//			builder.append(" ").append(",");// 参数2
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logBag error", e);
//		}
//	}
//
//	
//	/**
//	 * 竞技场日志
//	 * 皇城挑战开始/皇城购买次数: logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品(名字*数量),获得物品(名字1*数量1),剩余次数,今日购买次数,累计购买总次数
//	 * 擂台赛挑战开始
//	 * 
//	 * @param player
//	 * @param action
//	 * @param logType
//	 * @param costItemStr
//	 *            消耗物品
//	 * @param rewardMapStr
//	 *            获得物品
//	 * @return void
//	 * @date 2019年6月28日下午3:40:59
//	 */
//	public static void logPVP(final Player player, NatureEnum nEnum, String costItemStr, 
//			String rewardMapStr, int attr1, int attr2, int attr3) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(attr1).append(",");// 参数1
//			builder.append(attr2).append(",");// 参数2
//			builder.append(attr3).append(",");// 参数3
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logPVP error", e);
//		}
//	}
//
//	/**
//	 * 武将日志
//	 * logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品(名字*数量),获得物品(名字1*数量1),武将唯一id,武将配置id,武将名,获得(天赋)
//	 * 
//	 * @param player
//	 * @param action
//	 * @param logType
//	 * @param itemId
//	 * @param itemConfigId
//	 * @param number
//	 * @param afterNumber
//	 * @return void
//	 * @date 2019年6月25日下午6:17:13
//	 */
//	public static void logHero(final Player player, final Hero hero, NatureEnum nEnum, String costItemStr, String rewardMapStr, int obtainId) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(hero.getId()).append(",");// 武将唯一id
//			builder.append(hero.getConfigHero().getID()).append(",");// 武将配置id
//			builder.append(hero.getConfigHero().getHeroname()).append(",");// 武将名
//			builder.append(obtainId).append(",");// 获得
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logHero error", e);
//		}
//	}
//	
//	/**
//	 * 武将日志
//	 * logType,日期,角色id,角色名,输入账号,唯一账号,日志类型,行为,消耗物品(名字*数量),获得物品(名字1*数量1),武将唯一id,武将配置id,武将名,获得(天赋)
//	 * 
//	 * @param player
//	 * @param action
//	 * @param logType
//	 * @param itemId
//	 * @param itemConfigId
//	 * @param number
//	 * @param afterNumber
//	 * @return void
//	 * @date 2019年6月25日下午6:17:13
//	 */
//	public static void logHero(final Player player, final Hero hero, int logType, String action, String costItemStr, String rewardMapStr, int value1, int value2, int value3) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(logType).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(action).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(hero.getId()).append(",");// 武将唯一id
//			builder.append(hero.getConfigHero().getID()).append(",");// 武将配置id
//			builder.append(hero.getConfigHero().getHeroname()).append(",");// 武将名
//			builder.append(value1).append(",");// 获得
//			builder.append(value2).append(",");// 获得
//			builder.append(value3).append(",");// 获得
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(logType), builder.toString());
//		} catch (Exception e) {
//			logger.error("logHero error", e);
//		}
//	}
//	
//	/**
//	 * 活動日志
//	 * logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品(名字*数量),获得物品(名字1*数量1),活动Type,活动id
//	 * @param player
//	 * @param nEnum
//	 * @param costItemStr
//	 * @param rewardMapStr
//	 * @param activityId  
//	 * @return void  
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logActivity(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, int parm1) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(parm1).append(",");// 获得
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logActivity error", e);
//		}
//	}
//	
//	/**
//	 * 神兵打造日志
//	 * 神兵打造: logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品(名字*数量),获得物品(名字1*数量1),神兵id,神兵配置id,绑定武将id,武将配置id
//	 * 神兵精炼: logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品(名字*数量),获得物品(名字1*数量1),神兵id,神兵配置id,精炼等级
//	 * 神兵突破: logType,日期,角色id,角色名,输入账号,唯一账号,行为,消耗物品(名字*数量),获得物品(名字1*数量1),神兵id,神兵配置id,精炼等级
//	 * @param player
//	 * @param nEnum
//	 * @param costItemStr
//	 * @param rewardMapStr
//	 * @param activityId  
//	 * @return void  
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logArtifact(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, 
//			int artifactId, int artifacrConfigId, int attr1, int attr2) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(artifactId).append(",");// 神兵id
//			builder.append(artifacrConfigId).append(",");// 神兵配置id
//			builder.append(attr1).append(",");// 参数1
//			builder.append(attr2).append(",");// 参数2
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logArtifact error", e);
//		}
//	}
//	
//	/**
//	 * 副本日志
//	 * @param player
//	 * @param nEnum
//	 * @param costItemStr
//	 * @param rewardMapStr
//	 * @param activityId  
//	 * @return void  
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logStage(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, 
//			int attr1, int attr2, int attr3, Object attr4, Object attr5) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(attr1).append(",");// 参数1
//			builder.append(attr2).append(",");// 参数2
//			builder.append(attr3).append(",");// 参数3
//			builder.append(attr4).append(",");// 参数4
//			builder.append(attr5).append(",");// 参数5
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logStage error", e);
//		}
//	}
//	
//	/**
//	 * 任务日志
//	 * @param player
//	 * @param nEnum
//	 * @param costItemStr
//	 * @param rewardMapStr
//	 * @param activityId  
//	 * @return void  
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logMission(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, 
//			int missionId, String missionName) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(missionId).append(",");// 任务id
//			builder.append(missionName).append(",");// 任务名称
////			builder.append().append(",");// 状态
////			builder.append(attr4).append(",");// 参数4
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logMission error", e);
//		}
//	}
//	
//	/**
//	 * 建筑日志
//	 * @param player
//	 * @param nEnum
//	 * @param costItemStr
//	 * @param rewardMapStr
//	 * @param activityId  
//	 * @return void  
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logBuilding(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, 
//			int curId, int curLevel) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(curId).append(",");// 资源当前id
//			builder.append(curLevel).append(",");// 当前等级
////			builder.append().append(",");// 状态
////			builder.append(attr4).append(",");// 参数4
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logBuilding error", e);
//		}
//	}
//	
//	/**
//	 * 邮件日志
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logMail(final Player player, final Email mail, NatureEnum nEnum) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(SourceNature.itemMapToStr(mail.getRewards())).append(",");// 奖励物品
//			builder.append(mail.getId()).append(",");// 邮件id
//			builder.append(mail.getInfo().getTitle()).append(",");//邮件标题
//			builder.append(mail.getInfo().getIsRead() ? 1 : 0).append(",");//是否阅读
//			builder.append(DateUtilitys.date2String(mail.getInfo().getEmailTime(), DateUtilitys.DATETIME_20)).append(",");//发送时间
//			builder.append(DateUtilitys.date2String(mail.getInfo().getEndTime(), DateUtilitys.DATETIME_20)).append(",");//过期时间
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logMail error", e);
//		}
//	}
//	
//	/**
//	 * 玩家单一行为日志
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logAction(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr,
//			int arrt1, int arrt2) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(arrt1).append(",");// 参数1
//			builder.append(arrt2).append(",");// 参数2
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logAction error", e);
//		}
//	}
//	
////	/**
////	 * 玩家操作, 登陆登出注册日志
////	 * @date 2019年7月1日下午2:08:43
////	 */
////	public static void logPlayerOpt(final Player player, NatureEnum nEnum, Object param1,Object param2) {
////		try {
////			if (player == null)
////				return;
////			////
////			StringBuilder builder = new StringBuilder();
////			builder.append(nEnum.getLogType()).append(",");// logType
////			builder.append(Config.ServerId).append(",");// serverId
////			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
////			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
////			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
//////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
//////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
////			builder.append(nEnum.getDesc()).append(",");// 行为
////			builder.append(param1).append(",");// 扩展参数1
////			builder.append(param2).append(",");// 扩展参数2
////			////
////			builder.deleteCharAt(builder.length() - 1);
////			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
////		} catch (Exception e) {
////			logger.error("logLogin error", e);
////		}
////	}
//	
//	/**
//	 * 玩家祭拜日志
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logAltar(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, int crit) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(crit).append(",");// 暴击
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logAltar error", e);
//		}
//	}
//	
//	
//	/**
//	 * 玩家马场相关日志
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logHorse(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, int attr1, int attr2, int attr3) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			builder.append(attr1).append(",");// attr1
//			builder.append(attr2).append(",");// attr2
//			builder.append(attr3).append(",");// attr3
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logHorse error", e);
//		}
//	}
//	
//	/**
//	 * 玩家家族相关日志
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logFamily(final Player player, final Family family, NatureEnum nEnum, String costItemStr, String rewardMapStr, Object attr1, Object attr2, Object attr3) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			////
//			builder.append(family.getId()).append(",");// 家族id1
//			builder.append(family.getInfo().getName()).append(",");//家族名
//			////
//			builder.append(attr1).append(",");// attr1
//			builder.append(attr2).append(",");// attr2
//			builder.append(attr3).append(",");// attr3
//			builder.append(0).append(",");// attr4
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logHorse error", e);
//		}
//	}
//	
//	/**
//	 * 玩家家族相关日志
//	 * @date 2019年7月1日下午2:08:43
//	 */
//	public static void logFamily(final Player player, final Family family, NatureEnum nEnum, String costItemStr, String rewardMapStr, Object attr1, Object attr2, Object attr3, Object attr4) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
////			builder.append(player.getDbPlayer().getInputName()).append(",");// 输入账号
////			builder.append(player.getDbPlayer().getUserName()).append(",");// 唯一账号
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			////
//			builder.append(family.getId()).append(",");// 家族id1
//			builder.append(family.getInfo().getName()).append(",");//家族名
//			////
//			builder.append(attr1).append(",");// attr1
//			builder.append(attr2).append(",");// attr2
//			builder.append(attr3).append(",");// attr3
//			builder.append(attr4).append(",");// attr4	
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logHorse error", e);
//		}
//	}
//
//	/**
//	 * :统计相关
//	 */
//	public static void logOnlinePeople(NatureEnum nEnum, int onlineNum) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_23)).append(",");// 日期
//			////
//			builder.append(onlineNum).append(",");// 在线人数
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logOnlinePeople error", e);
//		}
//	}
//
//	/**
//	 * 房地产
//	 */
//	public static void logHouse(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, Object attr1, Object attr2, Object attr3) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//			////
//			builder.append(attr1).append(",");// attr1
//			builder.append(attr2).append(",");// attr2
//			builder.append(attr3).append(",");// attr3
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logHorse error", e);
//		}
//	}
//	
//	/**
//	 * 作弊/登录/注册等3个参数的日志
//	 */
//	public static void logAction(final Player player, NatureEnum nEnum, Object attr1, Object attr2, Object attr3, Object attr4) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//
//			builder.append(attr1).append(",");// attr1
//			builder.append(attr2).append(",");// attr2
//			builder.append(attr3).append(",");// attr3
//			builder.append(attr4).append(",");// attr4
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logAction error", e);
//		}
//	}
//	
//	/**
//	 * 攻击警告日志
//	 */
//	public static void logAction(NatureEnum nEnum, Object attr1, Object attr2, Object attr3, Object attr4) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(0).append(",");// 角色id
//			builder.append("").append(",");// 角色名
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//
//			builder.append(attr1).append(",");// attr1
//			builder.append(attr2).append(",");// attr2
//			builder.append(attr3).append(",");// attr3
//			builder.append(attr4).append(",");// attr4
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logAction error", e);
//		}
//	}
//
//	/**
//	 * 军机处
//	 */
//	public static void logMilitary(final Player player, NatureEnum nEnum, String costItemStr, String rewardMapStr, Object attr1, Object attr2, Object attr3, Object attr4, Object attr5) {
//		try {
//			////
//			StringBuilder builder = new StringBuilder();
//			builder.append(nEnum.getLogType()).append(",");// logType
//			builder.append(Config.ServerId).append(",");// serverId
//			builder.append(DateUtilitys.getCurrentTimeStamp(DateUtilitys.DATETIME_20)).append(",");// 日期
//			builder.append(player.getDbPlayer().getId()).append(",");// 角色id
//			builder.append(player.getDbPlayer().getNickName()).append(",");// 角色名
//			builder.append(nEnum.getDesc()).append(",");// 行为
//			////
//			builder.append(costItemStr).append(",");// 消耗物品
//			builder.append(rewardMapStr).append(",");// 获得物品
//
//			builder.append(attr1).append(",");// attr1
//			builder.append(attr2).append(",");// attr2
//			builder.append(attr3).append(",");// attr3
//			builder.append(attr4).append(",");// attr4
//			builder.append(attr5).append(",");// attr5
//			////
//			builder.deleteCharAt(builder.length() - 1);
//			KafkaLog.info(String.valueOf(nEnum.getLogType()), builder.toString());
//		} catch (Exception e) {
//			logger.error("logAction error", e);
//		}
//	}

}
