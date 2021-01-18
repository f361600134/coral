package org.coral.server.game.module.battle.domain;

import java.util.List;

import org.coral.server.game.data.config.ConfigSkillMgr;
import org.coral.server.game.data.config.pojo.ConfigSkill;

import com.google.common.collect.Lists;

/**
 * 战斗技能
 *
 * @author Klass
 * @date 2020/8/13 14:11
 */
public class BattleSkill {

	private int configId;

	private BattleEntity owner;

	/**
	 * 技能释放次数
	 */
	private short times;

	/**
	 * 效果索引
	 */
	private byte effectIndex;

	/**
	 * 冷却时间
	 */
	private byte coolDownRound;

	/**
	 * 整场战斗最多触发次数
	 */
	private byte maxTimesInBattle;

	/**
	 * 技能效果的所有目标
	 */
	private List<BattleEntity> allTargets = Lists.newArrayList();

	private List<BattleEntity> targets = Lists.newArrayList();

	public static final int PHYSIC_DAMAGE = 1;

	public static final int MAGIC_DAMAGE = 2;

	public BattleSkill(int configId, BattleEntity owner) {
		this.configId = configId;
		this.owner = owner;
		ConfigSkill config = ConfigSkillMgr.getConfig(configId);
//		this.coolDownRound = config.getCoolDownRound();
//		this.maxTimesInBattle = config.getMaxTimesInBattle();
	}

//	/**
//	 * 使用技能
//	 *
//	 */
//	public void use(BattleRound battleRound) {
//		times++;
//		SkillConfig config = SkillConfig.get(configId);
//		BattleAction.Builder builder = BattleAction.newBuilder()
//				.id(configId)
//				.type(BattleProtocol.BattleActionType.USE_SKILL_VALUE)
//				.source(owner.getId());
//		battleRound.addAction(builder.build());
//		EventPublisher.publishEvent0(new BeforeCastSkillEvent(this));
//		owner.setCurrentSkill(this);
//		owner.getBattle().setLastActionEntity(owner);
//		handleSkillEffect(config.getEffectIds(), builder.build());
//		effectIndex = 0;
//		this.targets.clear();
//		owner.setLastBattleSkill(this);
//		EventPublisher.publishEvent0(new AfterCastSkillEvent(this));
//		allTargets.clear();
//		this.maxTimesInBattle--;
//	}
//
//	private void handleSkillEffect(List<Integer> skillEffectIds, BattleAction useSkillAction) {
//		List<BattleEntity> oldTargets = Lists.newArrayList();
//		List<BattleEntity> lastTargets = Lists.newArrayList();
//		for (int id : skillEffectIds) {
//			SkillEffectConfig effectConfig = SkillEffectConfig.get(id);
//			SkillHandler handler = SkillHandlerManager.getHandler(effectConfig.getType());
//			List<BattleEntity> targets = selectTargets(effectConfig, oldTargets);
//			if (targets.isEmpty()) {
//				LoggerUtils.error(BattleSkill.class, "Skill effect [{}] targets is empty.", id);
//				continue;
//			}
//			useSkillAction.addActions(handler.handle(owner, targets, oldTargets, this));
//			effectIndex++;
//			allTargets.addAll(targets);
//			oldTargets = targets;
//			lastTargets.addAll(targets);
//		}
//		owner.setLastTargets(lastTargets);
//	}
//
//	/**
//	 * 使用技能，目标为最后一次攻击自己的战斗实体
//	 */
//	public void use() {
//		SkillConfig config = SkillConfig.get(configId);
//		BattleAction.Builder builder = BattleAction.newBuilder()
//				.id(configId)
//				.type(BattleProtocol.BattleActionType.USE_SKILL_VALUE)
//				.source(owner.getId());
//		owner.addBattleAction(builder.build());
//		owner.getBattle().setLastActionEntity(owner);
//		List<BattleEntity> oldTargets = Lists.newArrayList();
//		List<BattleEntity> targets = Lists.newArrayList();
//		for (int id : config.getEffectIds()) {
//			SkillEffectConfig effectConfig = SkillEffectConfig.get(id);
//			SkillHandler handler = SkillHandlerManager.getHandler(effectConfig.getType());
//			SkillContext context = owner.getSkillContext();
//			builder.addActions(handler.handle(owner, Lists.newArrayList(context.getCaster()), oldTargets, this));
//			allTargets.add(context.getCaster());
//			effectIndex++;
//			oldTargets = targets;
//		}
//		owner.setLastBattleSkill(this);
//		EventPublisher.publishEvent0(new AfterCastSkillEvent(this));
//		allTargets.clear();
//		effectIndex = 0;
//		this.maxTimesInBattle--;
//	}
//
//	/**
//	 * 触发被动技能效果
//	 *
//	 * @param triggerType
//	 */
//	public void triggerPassiveSkillEffect(int triggerType, BattleRound battleRound) {
//		BattleAction.Builder builder = BattleAction.newBuilder()
//				.id(configId)
//				.type(BattleProtocol.BattleActionType.USE_SKILL_VALUE)
//				.source(owner.getId());
//		battleRound.addAction(builder.build());
//		SkillConfig config = SkillConfig.get(configId);
//		List<BattleEntity> oldTargets = Lists.newArrayList();
//		for (int id : config.getEffectIds()) {
//			SkillEffectConfig effectConfig = SkillEffectConfig.get(id);
//			if (effectConfig.getTriggerType() != triggerType) {
//				continue;
//			}
//			SkillHandler handler = SkillHandlerManager.getHandler((short) id);
//			List<BattleEntity> targets;
//			if (owner.getLastTargets().isEmpty()) {
//				targets = selectTargets(effectConfig, oldTargets);
//			} else {
//				targets = owner.getLastTargets();
//			}
//			builder.addActions(handler.handle(owner, targets, oldTargets, this));
//			oldTargets.addAll(targets);
//			effectIndex++;
//			oldTargets = targets;
//		}
//		effectIndex = 0;
//	}
//
//	public List<BattleEntity> selectTargets(SkillEffectConfig config, List<BattleEntity> oldTargets) {
//		if (!this.targets.isEmpty()) {
//			return this.targets;
//		}
//		for (int type : config.getTargetType()) {
//			SkillTargetSelector selector = SkillTargetSelectorManager.getSkillTargetSelector((byte) type);
//			List<BattleEntity> targets = Lists.newArrayList();
//			if (config.isTargetAssociate()) {
//				targets = oldTargets;
//			}
//			List<BattleEntity> result = selector.select(owner, targets, config.getTargetNum());
//			if (!result.isEmpty()) {
//				return result;
//			}
//		}
//		return Collections.EMPTY_LIST;
//	}
//
//	public void addTarget(BattleEntity target) {
//		targets.add(target);
//	}
//
//	/**
//	 * 技能是否可以释放
//	 *
//	 * @param round
//	 * @return
//	 */
//	public boolean isUsable(int round) {
//		SkillConfig config = SkillConfig.get(configId);
//		if (maxTimesInBattle == 0) {
//			return false;
//		}
//		if (coolDownRound == 0) {
//			return true;
//		}
//		return config.getInitRound() + times * coolDownRound == round;
//	}
//
//	/**
//	 * 获取当前正在处理的效果ID
//	 *
//	 * @return
//	 */
//	public int getCurrentEffectId() {
//		SkillConfig config = SkillConfig.get(configId);
//		return config.getEffectIds().get(effectIndex);
//	}
//
//	public boolean isNormalAttack() {
//		SkillConfig config = SkillConfig.get(configId);
//		return config.isNormalAttackSkill();
//	}
//
//	public boolean isPhysicDamageSkill() {
//		SkillConfig config = SkillConfig.get(configId);
//		return config.getDamageType() == PHYSIC_DAMAGE;
//	}
//
//	public boolean isMagicDamageSkill() {
//		SkillConfig config = SkillConfig.get(configId);
//		return config.getDamageType() == MAGIC_DAMAGE;
//	}
//
//	public void setCoolDownRound(byte coolDownRound) {
//		this.coolDownRound = coolDownRound;
//	}
//
//	public short getPriority() {
//		return SkillConfig.get(configId).getPriority();
//	}
//
//	public byte getCoolDownRound() {
//		return SkillConfig.get(configId).getCoolDownRound();
//	}
//
	public int getConfigId() {
		return configId;
	}
//
//	public SkillConfig getConfig() {
//		return SkillConfig.get(configId);
//	}

	public BattleEntity getOwner() {
		return owner;
	}

	public List<BattleEntity> getAllTargets() {
		return allTargets;
	}

}
