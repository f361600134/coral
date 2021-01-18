package org.coral.server.game.module.battle.domain;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.coral.server.core.event.GameEventBus;
import org.coral.server.game.module.battle.callback.BattleCallback;
import org.coral.server.game.module.battle.event.AfterBattleEntityActionEvent;
import org.coral.server.game.module.battle.event.AfterBattleEvent;
import org.coral.server.game.module.battle.event.BeforeBattleEntityActionEvent;
import org.coral.server.game.module.battle.event.BeforeBattleEvent;
import org.coral.server.game.module.battle.event.BeforeRoundStartEvent;
import org.coral.server.game.module.battle.event.RoundFinishEvent;
import org.coral.server.game.module.battle.report.BattleReport;
import org.coral.server.game.module.battle.report.BattleRound;
import org.coral.server.game.module.battle.util.BattleSorter;
import org.coral.server.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 一场战斗
 *
 * @author Klass
 * @date 2020/8/12 11:20
 */
public class Battle {
	
	private static final Logger logger = LoggerFactory.getLogger(Battle.class);

    private int id;

    /**
     * 当前回合数
     */
    private int round = 1;

    private boolean finish;

    private BattleCallback callback;

    /**
     * 攻击队伍
     */
    private BattleTeam attackTeam;

    /**
     * 防御队伍
     */
    private BattleTeam defenceTeam;

    /**
     * 胜利的队伍
     */
    private BattleTeam winTeam;

    private BattleReport report;

    private BattleRound battleRound;

    private long startTime;

    /**
     * 最大回合数
     */
    private int maxRounds = 5;

    /**
     * 最后一次行动的战斗实体
     */
    private BattleEntity lastActionEntity;

    /**
     * 保存其他可能用到的参数
     */
    private Map<String, String> params = Maps.newHashMap();

    private Battle() {

    }

//    private Battle(int battleType,BattleTeam attackTeam, BattleTeam defenceTeam, BattleCallback callback, Map<String, String> params, int maxRounds) {
//        this.id = BattleUniqueIdGenerator.getBattleId();
//        this.attackTeam = attackTeam;
//        this.defenceTeam = defenceTeam;
//        this.callback = callback;
//        this.params = params;
//        this.attackTeam.setBattle(this);
//        this.defenceTeam.setBattle(this);
//        if (maxRounds != 0) {
//            this.maxRounds = maxRounds;
//        }
//        this.attackTeam.setType(BattleConstant.ATTACK_SIDE);
//        this.defenceTeam.setType(BattleConstant.DEFENCE_SIDE);
//        this.report = new BattleReport(this.id,battleType);
//    }
//
//    public static Battle create(int battleType,BattleTeam attackTeam, BattleTeam defenceTeam, BattleCallback callback, Map<String, String> params, int maxRounds) {
//        return new Battle(battleType,attackTeam, defenceTeam, callback, params, maxRounds);
//    }

    /**
     * 开始战斗
     */
    public void start() {
        this.startTime = TimeUtil.now();
        Stopwatch watch = Stopwatch.createStarted();
        callback.onBeforeBattle(this);
        GameEventBus.publishEvent(new BeforeBattleEvent(this));
        for (;;) {
            if (finish) {
                break;
            }
            GameEventBus.publishEvent(new BeforeRoundStartEvent(this));
            executeRound();
            GameEventBus.publishEvent(new RoundFinishEvent(this));
        }
        finish();
        report.setTotalRound(round - 1);
        GameEventBus.publishEvent(new AfterBattleEvent(this));
        logger.info("Battle cost time:{}", watch.elapsed(TimeUnit.MILLISECONDS));
    }

    public void callback() {
        callback.onAfterBattle(this);
    }

    public long costTime() {
        return TimeUtil.now() - this.startTime;
    }

    /**
     * 攻击方是否胜利
     *
     * @return
     */
    public boolean isWin() {
        return winTeam == attackTeam;
    }

//    /**
//     * 战斗后保存实体的状态
//     */
//    public void saveBattleEntityState() {
//        for (BattleTeam team : getBattleTeams()) {
//            team.saveBattleEntityState();
//        }
//    }

//    /**
//     * 获取战场上所有的战斗实体
//     *
//     * @return
//     */
//    public List<BattleEntity> getEntities() {
//        List<BattleEntity> result = Lists.newArrayList();
//        result.addAll(attackTeam.getEntities());
//        result.addAll(defenceTeam.getEntities());
//        return result;
//    }

    /**
     * 战斗结束
     */
    private void finish() {
        if (round > maxRounds) {
            winTeam = defenceTeam;
            return;
        }
        if (attackTeam.isAllDead()) {
            winTeam = defenceTeam;
            return;
        }
        if (defenceTeam.isAllDead()) {
            winTeam = attackTeam;
        }
    }

    /**
     * 执行一个回合
     */
    private void executeRound() {
        List<BattleEntity> allEntities = getAliveEntities();
        List<BattleEntity> entities = BattleSorter.sortBySpeed(allEntities);
        battleRound = new BattleRound(round);
        for (BattleEntity entity : entities) {
        	GameEventBus.publishEvent(new BeforeBattleEntityActionEvent(entity));
            entity.action(battleRound);
            GameEventBus.publishEvent(new AfterBattleEntityActionEvent(entity));
        }
        report.addBattleRound(battleRound);
        round++;
        checkFinish();
    }

    /**
     * 获取敌方队伍
     *
     * @param self
     * @return
     */
    public BattleTeam getEnemyBattleTeam(BattleTeam self) {
        if (self == attackTeam) {
            return defenceTeam;
        }
        return attackTeam;
    }

    /**
     * 获取战斗中所有存活的实体
     *
     * @return
     */
    public List<BattleEntity> getAliveEntities() {
        List<BattleEntity> result = Lists.newArrayList();
        result.addAll(attackTeam.getAliveEntities());
        result.addAll(defenceTeam.getAliveEntities());
        return result;
    }

    public List<BattleEntity> getEntitiesExcludeSelf(BattleEntity self) {
        List<BattleEntity> aliveEntities = getAliveEntities();
        return aliveEntities.stream().filter(e -> e != self).collect(Collectors.toList());
    }

//    /**
//     * 构建战报
//     * 
//     * @return
//     */
//    public BattleProtocol.BattleReportResp buildBattleReport() {
//        BattleProtocol.BattleReportResp resp = Converter.create().toProtobuf(BattleProtocol.BattleReportResp.class, report);
//        LoggerUtils.info(Battle.class, report.toString());
//        return resp;
//    }
//
//    public List<BattleEntityState> getStates() {
//        List<BattleEntityState> result = Lists.newArrayList();
//        for (BattleTeam team : getBattleTeams()) {
//            result.addAll(team.getStates());
//        }
//        return result;
//    }

    /**
     * 检查战斗是否结束
     */
    private void checkFinish() {
        finish = round > maxRounds
                || attackTeam.isAllDead()
                || defenceTeam.isAllDead();
    }

    public List<BattleTeam> getBattleTeams() {
        return Lists.newArrayList(attackTeam, defenceTeam);
    }

    public BattleTeam getWinTeam() {
        return winTeam;
    }

    public int getId() {
        return id;
    }

    public int getRound() {
        return round;
    }

    public BattleRound getBattleRound() {
        return battleRound;
    }

    public BattleTeam getAttackTeam() {
        return attackTeam;
    }

    public BattleTeam getDefenceTeam() {
        return defenceTeam;
    }

    public BattleReport getReport() {
        return report;
    }

    public BattleEntity getLastActionEntity() {
        return lastActionEntity;
    }

    public void setLastActionEntity(BattleEntity lastActionEntity) {
        this.lastActionEntity = lastActionEntity;
    }

    public String getParam(String key) {
        return params.get(key);
    }

    public long getStartTime() {
        return startTime;
    }

}
