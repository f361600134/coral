package org.coral.server.game.module.battle.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.coral.server.game.data.config.ConfigSkillMgr;
import org.coral.server.game.data.config.pojo.ConfigSkill;

import com.google.common.collect.Lists;

/**
 * 战斗技能持有者
 *
 * @author Klass
 * @date 2020/8/13 14:15
 */
public class BattleSkillHolder {

    /**
     * 主动技能
     */
    private List<BattleSkill> activeSkills = Lists.newArrayList();

    /**
     * 被动技能
     */
    private List<BattleSkill> passiveSkills = Lists.newArrayList();

    public void addSkill(int configId, BattleEntity owner) {
        BattleSkill skill = new BattleSkill(configId, owner);
        ConfigSkill config = ConfigSkillMgr.getConfig(configId);
//        if (config.isActiveSkill() || config.isNormalAttackSkill()) {
//            activeSkills.add(skill);
//        } else if (config.isPassiveSkill()) {
//            passiveSkills.add(skill);
//        }
    }

    public BattleSkill getNormalAttack() {
//        return activeSkills.stream().filter(e -> e.isNormalAttack()).findAny()
//                .orElseThrow(() -> new IllegalStateException("Normal attack is not exist."));
    	return null;
    }

    public BattleSkill getSkill(int id) {
        List<BattleSkill> skills = Lists.newArrayList();
        skills.addAll(activeSkills);
        skills.addAll(passiveSkills);
        return skills.stream().collect(Collectors.toMap(BattleSkill::getConfigId, e -> e)).get(id);
    }

    public List<BattleSkill> getPassiveSkills() {
        return passiveSkills;
    }

    /**
     * 获取可以释放的技能
     *
     * @param round
     * @return
     */
    public List<BattleSkill> usableSkills(int round) {
//        return activeSkills.stream().filter(e -> e.isUsable(round)).collect(Collectors.toList());
    	return null;
    }

    public List<BattleSkill> getActiveSkills() {
        return activeSkills;
    }

}
