package org.coral.server.game.module.mission;

import java.util.Collection;

import org.coral.server.game.data.proto.PBBag;

public interface IMissionHelper {
	
	/**
	 * 对象转json
	 * @return
	 */
	public Collection<PBBag.PBMissionInfo> toProto();
	
	/**
	 * 对象转json
	 * @return
	 */
	public String toJson();
	
	/**
	 *  登陆
	 * @param playerId
	 * @param loginDays 当前登陆天数
	 * @return
	 */
	public boolean onLogin(long playerId, int loginDays);
	/**
	 *赠送友情点  type:2
	 */
	public boolean onFriendPoint(long playerId, int count);
	/**
	 *召唤  type:3
	 */
	public boolean onCallAll(long playerId, int count);
	/**
	 * 当競技場挑戰  type:4
	 * @param playerId
	 * @param count 次数
	 */
	public boolean onArenaFight(long playerId, int count);
	/**
	 *参与公会副本  type:5
	 */
	public boolean onFamilyChapter(long playerId, int count);
	/**
	 * 公会捐赠 type:6
	 */
	public boolean onGuildDonate(long playerId, int count);
	/**
	 *  当远航任务完成 type:7
	 * @param playerId
	 * @param count 次数
	 */
	public boolean onSailMission(long playerId, int count);
	/**
	 * 参与日常副本 type:8
	 */
	public boolean onJoinDailyDungeon(long playerId, int count);

	/**
	 *
	 *参与试炼塔 type:9
	 */
	public boolean onJoinTowerFight(long playerId, int count);
	/**
	 *
	 *参与无尽挑战 type:10
	 */
	public boolean onJoinEndlessFight(long playerId, int count);
	/**
	 * 当快速作战 type:11
	 * @param playerId
	 * @param count 次数
	 */
	public boolean onChapterQuick(long playerId, int count);
	/**
	 * 合成装备 type:12
	 */
	public boolean onEquipCompose(long playerId, int count);
	/**
	 * 商店刷新 type:13
	 */
	public boolean onShopFlush(long playerId, int count);
	/**
	 *符文合成  type:14
	 */
	public boolean onRuneCompose(long playerId, int count);
	/**
	 *先知召唤  type:15
	 */
	public boolean onCallProphet(long playerId, int count);
	/**
	 * 高级幸运探宝  type:16
	 * */
	public boolean onTreasureHigh(long playerId, int count);
	/**
	 * 精灵商店购买 type:17
	 * @param playerId
	 * @param count 次数
	 */
	public boolean onShopSpiritBuy(long playerId, int count);
	/**
	 * 当玩家升级 type:26
	 * @param playerId
	 * @param level 等级
	 * @return
	 */
	public boolean onPlayerLevelUp(long playerId, int level);

	/**
	 * 当武将等级达到x  type:27
	 * @param playerId
	 * @param level 等级
	 * @return
	 */
	public boolean onHeroLevelUp(long playerId, int level);
	/**
	 * 获得*件*品质装备 type:30
	 */
	public boolean onEquipAward(long playerId, int quality,  int count);
	/**
	 *拥有x个x星武将  type:31
	 */
	public boolean onHeroHave(long playerId, int star);
	/**
	 *竞技场积分达标  type:32
	 */
	public boolean onArenaIntegral(long playerId, int integral);
	/**
	 *竞技场排名 type:33
	 */
	public boolean onArenaRank(long playerId, int rank);
	/**
	 * 接取*色远航任务*次  type:34
	 */
	public boolean onSailTask(long playerId, int color);
	/**
	 * 献祭英雄 type:35
	 */
	public boolean onDecomposeHero(long playerId, int count);
	/**
	 *战力达到x  type:38
	 */
	public boolean onPower(long playerId, int layer);
	/**
	 *段位赛达到王者 type:59
	 */
	public boolean onCompetitionKing(long playerId, int rank);
	/**
	 *试练塔达到x层  type:85
	 */
	public boolean onTowerPass(long playerId, int layer);
	/**
	 * 当通关  type:97
	 * @param playerId
	 * @param layer 当前层
	 * @return
	 */
	public boolean onChapterPass(long playerId, int layer);

	/**
	 * 当高级召唤次数达到x次  type:98
	 * @param playerId
	 * @param count 次数
	 * @return
	 */
	public boolean onCallHigh(long playerId, int count);
	/**
	 *合成五星英雄 type:99
	 */
	public boolean onHeroCompose(long playerId, int star);

	/**
	 * 公会技能升级 type:100
	 */
	public boolean onUpgradeGuildSkill(long playerId, int level);
	/**
	 * 公会商店消费 type:101
	 */
	public boolean onGuildShopBuy(long playerId, int level);
	/**
	 * 公会金币捐献 type:102
	 */
	public boolean onGuildDonateGold(long playerId, int level);
	/**
	 * 公会钻石捐献 type:103
	 */
	public boolean onGuildDonateIngot(long playerId, int level);
	/**
	 * 公会至尊钻石捐献 type:104
	 */
	public boolean onGuildDonateMaxIngot(long playerId, int level);
	/**
	 * 公会发送红包 type:105
	 */
	public boolean onGuildRedEnvelope(long playerId, int level);
	/**
	 * 公会副本增益加成 type:106
	 */
	public boolean onGuildDungeon(long playerId, int level);
}
