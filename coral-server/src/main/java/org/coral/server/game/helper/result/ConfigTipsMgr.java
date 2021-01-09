package org.coral.server.game.helper.result;

public class ConfigTipsMgr {

	public static final int DEFAULT = -1;
	public static final int ERROR = -1;

	public static final int Bag_200 = 200;// 背包功能异常
	public static final int Bag_201 = 201;// 背包不存在
	public static final int Bag_202 = 202;// 道具不存在
	public static final int Bag_203 = 203;// 不支持该操作
	public static final int Bag_204 = 204;// 消耗道具不足
	public static final int Bag_205 = 205;// 购买异常
	public static final int Bag_206 = 206;// 没有可出售的物品
	public static final int Bag_207 = 207;// 购买次数不足
	public static final int Bag_208 = 208;// 选择的道具不存在
	public static final int Bag_209 = 209;// 选择的数量不一致
	

	public static final int Mail_400 = 400;// 邮箱不存在
	public static final int Mail_401 = 401;// 邮件不存在
	public static final int Mail_402 = 402;// 邮件已领取
	public static final int Mail_403 = 403;// 邮件已过期

	public static final int Chat_410 = 410;// 不存在该频道
	public static final int Chat_411 = 411;// 聊天过快
	public static final int Chat_412 = 412;// 被禁言
	public static final int Chat_413 = 413;// 不能自言自语
	public static final int Chat_414 = 414;// 不够聊天等级
	public static final int Chat_415 = 415;// 数量过长
	public static final int Chat_416 = 416;// 表情超过限制
	public static final int Chat_417 = 417;// 剩余x秒可以聊天
	public static final int Chat_418 = 418;// 找不到聊天对象
	public static final int Chat_419 = 419;// 聊天内容为空

	public static final int Dungeon_551 = 551;// 主线副本模块逻辑错误
	public static final int Dungeon_552 = 552;// 次数不足
	public static final int Dungeon_553 = 553;// 没有可领取的挂机收益
	public static final int Dungeon_554 = 554;// 等级不够无法挑战
	public static final int Dungeon_555 = 555;// 同章节,无法进入下一章节
	public static final int Dungeon_556 = 556;// 未完成無法領取
	public static final int Dungeon_557 = 557;// 已經領取過了
	public static final int Dungeon_558 = 558;//

	// 700~750 商店
	public static final int Shop_700 = 700;// 商店逻辑错误
	public static final int Shop_701 = 701;// 已达到购买上限
	public static final int Shop_702 = 702;// 没有此物品不能购买
	public static final int Shop_703 = 703;// 商品未解锁不能购买
	public static final int Shop_704 = 704;// 商品已售罄
	public static final int Shop_705 = 705;// 不存在的商店类型
	public static final int Shop_706 = 706;// 免费刷新次数不足
	public static final int Shop_707 = 707;// 此商店不支持刷新
	public static final int Shop_708 = 708;// 刷新次数不足
	
	public static final int Artifact_730 = 730;// 神器逻辑错误
	public static final int Artifact_731 = 731;// 神器不存在
	public static final int Artifact_732 = 732;// 配置异常
	public static final int Artifact_733 = 733;// 已经到最大等级不可升级
	public static final int Artifact_734 = 734;// 需提升神器等级才可以精炼
	public static final int Artifact_735 = 735;// 需提升神器等级才可以技能升级
	public static final int Artifact_736 = 736;// 需提升神器等级才可以重铸
	public static final int Artifact_737 = 737;// 需提升神器等级才可以圣印
	public static final int Artifact_738 = 738;// 需提升神器等级才可以圣印
	public static final int Artifact_739 = 739;// 神器未激活
	public static final int Artifact_740 = 740;// 神器任务未激活
	
	// 800~830 无尽试炼
	public static final int Endless_800 = 800;// 无尽试炼逻辑错误
	public static final int Endless_801 = 801;// 已经上阵了支援
	public static final int Endless_802 = 802;// 找不到此英雄不能上阵
	public static final int Endless_803 = 803;// 每日仅可以挑战一种类型的试炼
	public static final int Endless_804 = 804;// 无可领取奖励
	public static final int Endless_805 = 805;// 选择的武将不存在
	
	//900~910 签到
	public static final int Checkin_900 = 900;// 签到逻辑错误
	public static final int Checkin_901 = 901;// 未激活不能签到
	public static final int Checkin_902 = 902;// 今日已经签到过
	
	//911~920 在线奖励
	public static final int Checkin_911 = 911;// 在线奖励错误
	public static final int Checkin_912 = 912;// 在线奖励未激活
	public static final int Checkin_913 = 913;// 在线奖励已领取
	
	//921~930 8天
	public static final int Checkin_921 = 921;// 8天奖励错误
	public static final int Checkin_922 = 922;// 8天在线奖励未激活
	public static final int Checkin_923 = 923;// 8天在线奖励已领取

	//931~950 在线奖励
	public static final int Checkin_931 = 931;// 7天奖励错误
	public static final int Checkin_932 = 932;// 7天在线奖励未激活
	public static final int Checkin_933 = 933;// 7天在线奖励已领取
	public static final int Checkin_934 = 934;// 7天在线奖励vip不足
	public static final int Checkin_935 = 935;// 7天在线奖励钻石
	
	//1001~1010 查询玩家
	public static final int Other_1001 = 1001;// 查询玩家不存在
	public static final int Other_1002 = 1002;// 查询武将不存在

	//1011~1030 圣物
	public static final int Halidom_1011 = 1011;// 圣物不存在
	public static final int Halidom_1012 = 1012;// 圣物已解锁,无需重复解锁
	public static final int Halidom_1013 = 1013;// 选择武将不存在
	public static final int Halidom_1014 = 1014;// 系别不一致,无法解锁
	public static final int Halidom_1015 = 1015;// 星级不一致,无法解锁
	public static final int Halidom_1016 = 1016;// 数量不足,无法解锁
	public static final int Halidom_1017 = 1017;// 圣物未激活
	public static final int Halidom_1018 = 1018;// 已到达最大等级
	public static final int Halidom_1019 = 1019;// 已到达最大阶级
	public static final int Halidom_1020 = 1020;// 等级不足无法进阶
	
	//1020~1050精灵系统
	public static final int Spirit_1020 = 1020;//合成不存在道具
	public static final int Spirit_1021 = 1021;//精灵不可再升星
	public static final int Spirit_1022 = 1022;//不存在的精灵窝
	public static final int Spirit_1023 = 1023;//精灵窝开启不满足条件
	
	// 100000开始, 通用提示
	public static final int Frequency_100000 = 100000;   //道具不足
	public static final int Frequency_100001 = 100001;   //钻石不足
	public static final int Frequency_100002 = 100002;   //金币不足
	public static final int Frequency_100003 = 100003;   //主角经验不足
	public static final int Frequency_100004 = 100004;   //英雄经验不足
	public static final int Frequency_100005 = 100005;   //召唤积分不足
	public static final int Frequency_100006 = 100006;   //友情点不足
	public static final int Frequency_100007 = 100007;   //先知结晶不足
	public static final int Frequency_100008 = 100008;   //星命卡牌不足
	public static final int Frequency_100009 = 100009;   //远航情报不足
	public static final int Frequency_100010 = 100010;   //熔炼值不足
	public static final int Frequency_100011 = 100011;   //探宝积分不足
	public static final int Frequency_100012 = 100012;   //公会贡献不足
	public static final int Frequency_100013 = 100013;   //神格不足
	public static final int Frequency_100014 = 100014;   //活跃度不足
	public static final int Frequency_100015 = 100015;   //周常活跃度不足
	public static final int Frequency_100016 = 100016;   //烈阳晶石不足
	public static final int Frequency_100017 = 100017;   //竞技声望不足
	public static final int Frequency_100018 = 100018;   //竞技积分不足
	public static final int Frequency_100019 = 100019;   //征战勋章不足
	public static final int Frequency_100020 = 100020;   //荣誉勋章不足
	public static final int Frequency_100021 = 100021;   //皮肤碎片不足
	
//	/**
//	 * 生成错误码,错误码跟货币的id保持一致
//	 * @param configId
//	 * @return
//	 */
//	public static int enoughCode(int configId) {
//		ResourceType type = ResourceType.getEnum(configId);
//		return type == null ? Frequency_100000:type.getClientType();
//	}
	
//	/**
//	 * 生成错误码,错误码跟货币的id保持一致
//	 * @param configId
//	 * @return
//	 */
//	public static int enoughCode(Map<Integer, Integer> costItem) {
//		Iterator<Integer> iter = costItem.keySet().iterator();
//		int configId = 0;
//		if (iter.hasNext()) {
//			configId = iter.next();
//		}
//		return enoughCode(configId);
//	}
	
}
