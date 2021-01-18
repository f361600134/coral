package org.coral.server.game.module.battle.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.coral.server.game.module.battle.domain.BattleEntity;

import com.google.common.collect.Lists;

/**
 * 战斗中用到的各种排序
 *
 * @author Klass
 * @date 2020/8/12 11:36
 */
public class BattleSorter {
	
	public static Comparator<BattleEntity> comparator = new Comparator<BattleEntity>() {
		@Override
		public int compare(BattleEntity o1, BattleEntity o2) {
			return o2.getSpeed() - o1.getSpeed();
		}
	};

    /**
     * 按照战斗实体速度降序排序
     *
     * @param entities
     * @return
     */
    public static List<BattleEntity> sortBySpeed(List<BattleEntity> entities) {
    	Collections.sort(entities, comparator);
        return entities;
//    	 List<BattleEntity> result = Lists.newArrayList(entities);
//         result.sort(((o1, o2) -> o2.getSpeed() - o1.getSpeed()));
//         return result;
    }

    /**
     * HP升序排序
     *
     * @param entities
     * @return
     */
    public static List<BattleEntity> sortByHp(List<BattleEntity> entities) {
        List<BattleEntity> result = Lists.newArrayList(entities);
        result.sort((Comparator.comparingInt(BattleEntity::getHp)));
        return result;
    }

//    /**
//     * 技能按优先级降序排序
//     *
//     * @param skills
//     * @return
//     */
//    public static BattleSkill sortBySkillPriority(List<BattleSkill> skills) {
//        skills.sort(Comparator.comparingInt(BattleSkill::getPriority).reversed());
//        return Iterables.getFirst(skills, null);
//    }

}
