package org.coral.server.game.module.battle.domain;

import java.util.Objects;

import org.coral.server.game.data.config.ConfigBufferMgr;
import org.coral.server.game.data.config.pojo.ConfigBuffer;

/**
 * 战斗技能Buff
 *
 * @author Klass
 * @date 2020/8/13 14:58
 */
public class BattleBuff {

    /**
     * Buff唯一ID
     */
    private int id;

    /**
     * Buff配置ID
     */
    private int configId;

    /**
     * 剩余回合数
     */
    private short remainRound;

    /**
     * 剩余被攻击次数
     */
    private short remainBeAttackTimes;

    /**
     * 护盾值
     */
    private int shield;

    private BattleEntity caster;

    private BattleEntity owner;

    public BattleBuff(int configId, BattleEntity caster, BattleEntity owner) {
//        this.id = BattleUniqueIdGenerator.getBuffId();
        this.configId = configId;
        ConfigBuffer config = ConfigBufferMgr.getConfig(configId);
//        remainRound = config.getDurationRound();
//        remainBeAttackTimes = config.getBeAttackTimes();
        this.caster = caster;
        this.owner = owner;
    }

    /**
     * 刷新剩余回合数
     */
    public void refreshRounds() {
        remainRound--;
    }

    public void refreshRemainBeAttackTimes() {
        remainBeAttackTimes--;
    }

    public void init() {
//        Optional.ofNullable(getBattleBuffHandler()).ifPresent(e -> e.init(this));
    }

    public void release() {
//        Optional.ofNullable(getBattleBuffHandler()).ifPresent(e -> e.release(this));
    }

    public void handle() {
//        Optional.ofNullable(getBattleBuffHandler()).ifPresent(e -> e.handle(this));
    }

//    private BattleBuffHandler getBattleBuffHandler() {
//        BuffConfig config = BuffConfig.get(configId);
//        return BattleBuffHandlerManager.getHandler(config.getType());
//    }

    /**
     * 是否可以触发
     *
     * @param triggerType
     * @return
     */
    public boolean isCanBeTriggered(byte triggerType) {
//        BuffConfig config = BuffConfig.get(configId);
//        return config.getTriggerType().contains(triggerType);
    	return false;
    }

    /**
     * 是否过期
     *
     * @return
     */
    public boolean isExpired() {
        return remainRound == 0;
    }

    public boolean isRemoved() {
        return isExpired() || remainBeAttackTimes == 0;
    }

    /**
     * 是否添加到当前回合
     *
     * @return
     */
//    public boolean isAddCurrentRound() {
//        BuffConfig config = BuffConfig.get(configId);
//        return config.getInitRound() == BattleConstant.BuffInitRoundType.THIS_ROUND;
//    }

    /**
     * 是否添加到下一回合
     *
     * @return
     */
    public boolean isAddNextRound() {
//        BuffConfig config = BuffConfig.get(configId);
//        return config.getInitRound() == BattleConstant.BuffInitRoundType.NEXT_ROUND;
    	return false;
    }

//    public String getParams() {
//        BuffConfig config = BuffConfig.get(configId);
//        return config.getParams();
//    }

//    public String getFormula() {
//        BuffConfig config = BuffConfig.get(configId);
//        return config.getFormula();
//    }

//    public List<Integer> getTags() {
//        BuffConfig config = BuffConfig.get(configId);
//        return config.getTags();
//    }

//    public byte getType() {
//        BuffConfig config = BuffConfig.get(configId);
//        return config.getType();
//    }

    public BattleEntity getOwner() {
        return owner;
    }

    public BattleEntity getCaster() {
        return caster;
    }

    public int getConfigId() {
        return configId;
    }

    public int getId() {
        return id;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleBuff buff = (BattleBuff) o;
        return id == buff.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
