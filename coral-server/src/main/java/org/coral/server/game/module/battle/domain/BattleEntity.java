package org.coral.server.game.module.battle.domain;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.RandomUtils;
import org.coral.server.game.module.attribute.domain.AttributeDictionary;
import org.coral.server.game.module.attribute.domain.AttributeType;
import org.coral.server.game.module.battle.attr.BattleEntityAttrNode;
import org.coral.server.game.module.battle.report.BattleRound;

import com.google.common.collect.Lists;

/**
 * 战斗实体
 *
 * @author Klass
 * @date 2020/8/12 11:24
 */
public abstract class BattleEntity {

	/**
	 *	将领id/怪物id<br>
	 */
    private int id;

    /**
     * 	英雄配置id<br>
     */
    private int configId;

    /**
     *	英雄当前等级<br>
     */
    private short level;

    /**
     * 	站位<br>
     */
    private byte position;
    
    /**
     * 	当前血量
     */
    protected int hp;

    /**
     *	 最大血量
     */
    protected int maxHp;
    
    /**
     * 	该战斗对象战斗前的原始属性<br>
     */
    protected final AttributeDictionary originalAttrDic = new AttributeDictionary();

    /**
     * 
     */
    protected final BattleEntityAttrNode attrNode = new BattleEntityAttrNode(this);

    private BattleTeam selfTeam;

//    private BattleSkillHolder skillHolder;
//
//    private BattleBuffHolder buffHolder;

    /**
     * 已经死亡多少回合
     */
    private byte deadRounds;

//    private SkillContext skillContext;

//    /**
//     * 当前使用的技能
//     */
//    private BattleSkill currentSkill;

//    /**
//     * 最后一次行动时的目标
//     */
    private List<BattleEntity> lastTargets = Lists.newArrayList();

    /**
     * 是否触发圣灵
     */
    private boolean isTriggerHolyGhost;

//    /**
//     * 最后一次施放的技能
//     */
//    private BattleSkill lastBattleSkill;

    private int buffTag;

    /**
     * 伤害系数
     */
    private int damageFactor = 1;

    /**
     * BUFF权重
     */
    private int buffWeight = 1;

    /**
     * 回复系数
     */
    private int recoveryFactor = 1;

//    /**
//     * 最后一次对本实体施法的实体
//     */
//    private BattleEntity lastCaster;

    public static final BattleEntity EMPTY_BATTLE_ENTITY = new BattleEntity() {

        @Override
        public byte getType() {
            return 0;
        }

    };

    public BattleEntity() {

    }

    public BattleEntity(List<Integer> skillIds, BattleTeam selfTeam) {
//        this.id = BattleUniqueIdGenerator.getEntityId();
//        this.selfTeam = selfTeam;
//        this.skillHolder = new BattleSkillHolder();
//        this.buffHolder = new BattleBuffHolder(this);
//        addBattleSkills(skillIds);
    }

    public void resetAfterAction() {
        buffTag = 0;
        damageFactor = 1;
        buffWeight = 1;
        recoveryFactor = 1;
    }

    public void addBattleSkills(List<Integer> skillIds) {
//        for (int skillId : skillIds) {
//            skillHolder.addSkill(skillId, this);
//        }
    }

//    public void refreshBattleAttributes(Map<BattleAttributeType, Integer> initAttributes) {
//        mergeBattleAttributes(initAttributes);
//        ImmutableMap<String, Object> params = ImmutableMap.of(BattleConstant.ENTITY, this);
//        int attack = FormulaUtils.calculateInt(ConfigConstantPlus.attackFormula, params);
//        int def = FormulaUtils.calculateInt(ConfigConstantPlus.defFormula, params);
//        if (this.hp == 0) {
//            this.hp = FormulaUtils.calculateInt(ConfigConstantPlus.hpFormula, params);
//        }
//        int speed = FormulaUtils.calculateInt(ConfigConstantPlus.speedFormula, params);
//        this.maxHp = hp;
//        attributes.put(BattleAttributeType.AtkFinal, attack);
//        attributes.put(BattleAttributeType.DefFinal, def);
//        attributes.put(BattleAttributeType.SpeedFinal, speed);
//    }

//    protected void mergeBattleAttributes(Map<BattleAttributeType, Integer> initAttributes) {
//        for (Map.Entry<BattleAttributeType, Integer> entry : initAttributes.entrySet()) {
//            int value = attributes.getOrDefault(entry.getKey(), 0);
//            int newValue = value + entry.getValue();
//            attributes.put(entry.getKey(), newValue);
//        }
//    }

//    public void initBattleAttributes() {
//        BattleTeam team = getSelfTeam();
//        team.getState(getPosition()).ifPresent(e -> {
//            this.setHp(e.getHp());
//            this.setMaxHp(e.getMaxHp());
//        });
//    }

    /**
     * 回合开始之前处理
     */
    public void beforeRoundStart() {

    }

    public int getVocation() {
        return 0;
    }

    /**
     * 战斗实体每个回合的行动
     */
    public void action(BattleRound battleRound) {
        if (isDead()) {
            deadRounds++;
            return;
        }
        if (!isActionable()) {
            return;
        }
//        BattleSkill normalAttack = skillHolder.getNormalAttack();
//        if (buffHolder.hasStatus(BattleConstant.CHAOS)) {
//            // 选择除自身外任意1个存活单位作为普通攻击目标
//            List<BattleEntity> aliveEntities = getBattle().getEntitiesExcludeSelf(this);
//            int index = RandomUtils.randInt(aliveEntities.size());
//            normalAttack.addTarget(aliveEntities.get(index));
//            normalAttack.use(battleRound);
//        } else if (buffHolder.hasStatus(BattleConstant.SILENCE)) {
//            // 是否存在嘲讽状态
//            if (buffHolder.hasStatus(BattleConstant.CHAOS)) {
//                // TODO 选择嘲讽施法者作为普攻目标
//            } else {
//                // 选择默认攻击目标作为普攻目标
//                normalAttack.use(battleRound);
//            }
//        } else {
//            List<BattleSkill> skills = skillHolder.usableSkills(selfTeam.getRound());
//            BattleSkill skill = Optional.ofNullable(BattleSorter.sortBySkillPriority(skills)).orElse(normalAttack);
//            skill.use(battleRound);
//        }
    }

//    /**
//     * 触发被动技能
//     *
//     * @param triggerType
//     */
//    public void triggerPassiveSkill(int triggerType, BattleRound battleRound) {
//        for (BattleSkill skill : skillHolder.getPassiveSkills()) {
//            skill.triggerPassiveSkillEffect(triggerType, battleRound);
//        }
//    }

//    public BattleSkill getBattleSkill(int id) {
//        return skillHolder.getSkill(id);
//    }

//    public boolean hasBattleSkill(int id) {
//        return Optional.ofNullable(getBattleSkill(id)).isPresent();
//    }

//    public List<BattleSkill> getBattleSkills(List<Integer> skillIds) {
//        List<BattleSkill> result = Lists.newArrayList();
//        for (int skillId : skillIds) {
//            BattleSkill skill = getBattleSkill(skillId);
//            if (skill != null) {
//                result.add(skill);
//            }
//        }
//        return result;
//    }

//    public List<BattleBuff> getBattleBuffsByTags(List<Integer> tags) {
//        return buffHolder.getBattleBuffsByTags(tags);
//    }
//
//    public void handleBattleBuff(byte triggerType) {
//        buffHolder.handle(triggerType);
//    }
//
//    public boolean isAttackTeam() {
//        return selfTeam == getBattle().getAttackTeam();
//    }
//
//    private void changeBattleAttribute(BattleAttributeType type, int value) {
//        attributes.put(type, value);
//    }
//
//    public void changeBattleAttribute(int type, int value) {
//        changeBattleAttribute(BattleAttributeType.getEnum(type), value);
//    }
//
//    public boolean hasStatus(byte type) {
//        return buffHolder.hasStatus(type);
//    }
//
//    public int buffTag(int tag) {
//        return buffHolder.getBattleBuffByTag(tag) == null ? 0 : 1;
//    }
//
//    public void removeBattleBuffByType(byte type) {
//        buffHolder.removeBattleBuffByType(type);
//    }
//
//    public boolean isForbidRelive() {
//        return hasStatus(BattleConstant.FORBID_RELIVE);
//    }
//
//    public boolean isForbidTreat() {
//        return hasStatus(BattleConstant.FORBID_TREAT);
//    }
//
//    public boolean isImmune() {
//        return hasStatus(BattleConstant.IMMUNE);
//    }
//
//    public boolean isInvincible() {
//        return hasStatus(BattleConstant.INVINCIBLE);
//    }
//
//    /**
//     * 目标在无敌状态下是否产生伤害
//     *
//     * @return
//     */
//    public boolean isDamageInvincible() {
//        return false;
//    }
//
//    public void removeBattleBuffsByTag(List<Integer> tags) {
//        buffHolder.removeBattleBuffsByTag(tags);
//    }
//
//    public void removeBattleBuffs(Collection<BattleBuff> buffs) {
//        for (BattleBuff buff : buffs) {
//            buffHolder.removeBattleBuff(buff);
//        }
//    }
//
//    public BattleBuff getBattleBuff(int id) {
//        return buffHolder.get(id);
//    }
//
//    public int addBattleBuff(int configId, BattleEntity caster) {
//        BuffConfig config = BuffConfig.get(configId);
//        List<BattleBuff> buffs = buffHolder.getBattleBuffsByConfigId(configId);
//        if (buffs.size() >= config.getMaxStack()) {
//            return 0;
//        }
//        BattleBuff buff = new BattleBuff(configId, caster, this);
//        buffHolder.addBattleBuff(buff);
//        EventPublisher.publishEvent0(new AddBattleBuffEvent(buff));
//        return buff.getId();
//    }
//
//    public void nextRoundBuffs() {
//        buffHolder.nextRoundBuffs();
//    }
//
//    public void refreshBuffRemainRounds() {
//        buffHolder.refreshBattleBuffRounds();
//    }
//
//    public void changeHp(int value) {
//        int oldHp = this.hp;
//        this.hp += value;
//        if (this.hp < 0) {
//            this.hp = 0;
//            EventPublisher.publishEvent0(new AfterBattleEntityDeadEvent(this, lastCaster));
//        } else if (this.hp > maxHp) {
//            this.hp = maxHp;
//        }
//        EventPublisher.publishEvent0(new HpChangedEvent(oldHp, this));
//    }
//
//    public void addBattleAction(BattleAction action) {
//        getBattleRound().addAction(action);
//    }
//
    /**
     * 是否可以行动
     *
     * @return
     */
    public boolean isActionable() {
//        return (!hasStatus(BattleConstant.FROZEN)
//                && !hasStatus(BattleConstant.DIZZINESS)
//                && !hasStatus(BattleConstant.WEAKNESS)
//                && !hasStatus(BattleConstant.DO_NOT_MOVE));
    	return false;
    }
//
//    public void addTempBattleAttribute(int type, int value) {
//        Integer oldValue = tempAttributes.getOrDefault(BattleAttributeType.getEnum(type), 0);
//        tempAttributes.put(BattleAttributeType.getEnum(type), value + oldValue);
//    }
//
//    public void clearTempBattleAttributes() {
//        for (Map.Entry<BattleAttributeType, Integer> entry : tempAttributes.entrySet()) {
//            int value = attributes.get(entry.getKey());
//            attributes.put(entry.getKey(), value - entry.getValue());
//        }
//        tempAttributes.clear();
//    }
//
//    public boolean isContainsSkill(List<Integer> skillIds) {
//        for (int id : skillIds) {
//            if (skillHolder.getSkill(id) != null) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public BattleRound getBattleRound() {
//        return getBattle().getBattleRound();
//    }
//
//    public BattleAction getLastBattleAction() {
//        return getBattleRound().getLastBattleAction();
//    }
//
//    public void setLastTargets(List<BattleEntity> lastTargets) {
//        this.lastTargets = lastTargets;
//    }
//
    /**
     * 是否可以被攻击
     *
     * @return
     */
    public boolean isCanBaAttacked() {
        return true;
    }
//
//    public boolean isCalcAttackTimes() {
//        return true;
//    }
//
    /**
     * 	是否死亡
     *
     * @return
     */
    public boolean isDead() {
        return this.hp <= 0;
    }
//
//    public int getRace() {
//        return 0;
//    }
//
    public abstract byte getType();

    /**
     * 	是否存活
     *
     * @return
     */
    public boolean isAlive() {
        return !isDead();
    }
//
//    public int getAttribute(BattleAttributeType type) {
//        return attributes.getOrDefault(type, 0);
//    }
//
//    public int getAttribute(int type) {
//        return getAttribute(BattleAttributeType.getEnum(type));
//    }
//
    public int getSpeed() {
//        return (int)originalAttrDic.getAttr(AttributeType.IdPropertySpeed);
        return 1;
    }
//
//    public int getId() {
//        return id;
//    }
//
//    public byte getPosition() {
//        return position;
//    }
//
    public int getHp() {
        return hp;
    }
//
//    public void setPosition(byte position) {
//        this.position = position;
//    }
//
//    public void setConfigId(int configId) {
//        this.configId = configId;
//    }
//
//    public int getConfigId() {
//        return configId;
//    }
//
//    public BattleTeam getSelfTeam() {
//        return selfTeam;
//    }
//
//    public BattleTeam getEnemyTeam() {
//        return selfTeam.getEnemyTeam();
//    }
//
//    public List<BattleSkill> getBattleSkills() {
//        return skillHolder.getActiveSkills();
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setSelfTeam(BattleTeam selfTeam) {
//        this.selfTeam = selfTeam;
//    }
//
//    public BattleSkillHolder getSkillHolder() {
//        return skillHolder;
//    }
//
//    public void setSkillHolder(BattleSkillHolder skillHolder) {
//        this.skillHolder = skillHolder;
//    }
//
//    public BattleBuffHolder getBuffHolder() {
//        return buffHolder;
//    }
//
//    public void setBuffHolder(BattleBuffHolder buffHolder) {
//        this.buffHolder = buffHolder;
//    }
//
//    public short getLevel() {
//        return level;
//    }
//
//    public void setLevel(short level) {
//        this.level = level;
//    }
//
//    public Battle getBattle() {
//        return selfTeam.getBattle();
//    }
//
//    public int getMaxHp() {
//        return maxHp;
//    }
//
//    public int getAttack() {
//        return attributes.get(BattleAttributeType.AtkFinal);
//    }
//
//    public int getDef() {
//        return attributes.get(BattleAttributeType.DefFinal);
//    }
//
//    public byte getDeadRounds() {
//        return deadRounds;
//    }
//
//    public SkillContext getSkillContext() {
//        return skillContext;
//    }
//
//    public void setSkillContext(SkillContext skillContext) {
//        this.skillContext = skillContext;
//    }
//
//    public List<BattleEntity> getLastTargets() {
//        return lastTargets;
//    }
//
//    public void setCurrentSkill(BattleSkill currentSkill) {
//        this.currentSkill = currentSkill;
//    }
//
//    public BattleSkill getCurrentSkill() {
//        return currentSkill;
//    }
//
//    public boolean isTriggerHolyGhost() {
//        return isTriggerHolyGhost;
//    }
//
//    public void setTriggerHolyGhost(boolean triggerHolyGhost) {
//        isTriggerHolyGhost = triggerHolyGhost;
//    }
//
//    public BattleSkill getLastBattleSkill() {
//        return lastBattleSkill;
//    }
//
//    public void setLastBattleSkill(BattleSkill lastBattleSkill) {
//        this.lastBattleSkill = lastBattleSkill;
//    }
//
//    public int getBuffTag() {
//        return buffTag;
//    }
//
//    public void setBuffTag(int buffTag) {
//        this.buffTag = buffTag;
//    }
//
//    public int getDamageFactor() {
//        return damageFactor;
//    }
//
//    public void setDamageFactor(int damageFactor) {
//        this.damageFactor = damageFactor;
//    }
//
//    public int getBuffWeight() {
//        return buffWeight;
//    }
//
//    public void setBuffWeight(int buffWeight) {
//        this.buffWeight = buffWeight;
//    }
//
//    public int getRecoveryFactor() {
//        return recoveryFactor;
//    }
//
//    public void setRecoveryFactor(int recoveryFactor) {
//        this.recoveryFactor = recoveryFactor;
//    }
//
//    public BattleEntity getLastCaster() {
//        return lastCaster;
//    }
//
//    public void setLastCaster(BattleEntity lastCaster) {
//        this.lastCaster = lastCaster;
//    }
//
//    public void setHp(int hp) {
//        this.hp = hp;
//    }
//
//    public void setMaxHp(int maxHp) {
//        this.maxHp = maxHp;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleEntity entity = (BattleEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
