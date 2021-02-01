package org.coral.server.battle.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.coral.server.game.module.battle.assist.BattleDefine;
import org.coral.server.game.module.battle.domain.BattleEntity;
import org.coral.server.game.module.battle.domain.BattleTeam;
import org.coral.server.game.module.battle.report.BattleReport;
import org.coral.server.game.module.battle.report.BattleRound;
import org.coral.server.game.module.battle.util.BattleSorter;
import org.coral.server.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

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
     * 	当前回合数
     */
    private int round = 1;

    private boolean finish;

    /**
     * 	攻击队伍
     */
    private BattleTeam attackTeam;
    /**
     *	 防御队伍
     */
    private BattleTeam defenceTeam;
    /**
     * 	胜利的队伍
     */
    private BattleTeam winTeam;

    private BattleReport report;

    private BattleRound battleRound;

    private long startTime;

    /**
     * 	最大回合数
     */
    private int maxRounds = 5;

    /**
     * 	最后一次行动的战斗实体
     */
    private BattleEntity lastActionEntity;

    private Battle() {

    }
    
    private Battle(int battleType,BattleTeam attackTeam, BattleTeam defenceTeam, int maxRounds) {
        this.id = 1;
        this.attackTeam = attackTeam;
        this.defenceTeam = defenceTeam;
//        this.attackTeam.setBattle(this);
//        this.defenceTeam.setBattle(this);
        if (maxRounds != 0) {
            this.maxRounds = maxRounds;
        }
        this.attackTeam.setType(BattleDefine.ATTACK_SIDE);
        this.defenceTeam.setType(BattleDefine.DEFENCE_SIDE);
        this.report = new BattleReport(this.id,battleType);
    }
    
    public static Battle create(int battleType,BattleTeam attackTeam, BattleTeam defenceTeam, int maxRounds) {
        return new Battle(battleType,attackTeam, defenceTeam, maxRounds);
    }

    /**
     * 	开始战斗
     */
    public void start() {
        this.startTime = TimeUtil.now();
        for (;;) {
            executeRound();
            if (finish) {
                break;
            }
        }
        finish();
        report.setTotalRound(round - 1);
        logger.info("Battle cost time:{}", (TimeUtil.now() - startTime));
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
    	long startTime = TimeUtil.now();
        List<BattleEntity> allEntities = getAliveEntities();
        //logger.info("==1==> executeRound cost time:{}", (TimeUtil.now() - startTime));
        List<BattleEntity> entities = BattleSorter.sortBySpeed(allEntities);
        //logger.info("==2==> executeRound cost time:{}", (TimeUtil.now() - startTime));
        battleRound = new BattleRound(round);
        for (BattleEntity entity : entities) {
            entity.action(battleRound);
        }
        //logger.info("==3==> executeRound cost time:{}", (TimeUtil.now() - startTime));
        report.addBattleRound(battleRound);
        //logger.info("==4==> executeRound cost time:{}", (TimeUtil.now() - startTime));
        round++;
        checkFinish();
        //logger.info("==5==> executeRound cost time:{}", (TimeUtil.now() - startTime));
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

    public long getStartTime() {
        return startTime;
    }

}
