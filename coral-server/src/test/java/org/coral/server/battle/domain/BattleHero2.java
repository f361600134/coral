package org.coral.server.battle.domain;

import java.util.List;

import org.coral.server.game.module.battle.assist.BattleDefine.BattleEntityType;
import org.coral.server.game.module.battle.domain.BattleEntity;
import org.coral.server.game.module.battle.domain.BattleTeam;
import org.coral.server.game.module.hero.domain.Hero;

import com.google.common.collect.Lists;

/**
 * 英雄
 *
 * @author Jeremy
 * @date 2020/9/11 14:46
 */
public class BattleHero2 extends BattleEntity {

	private Hero hero;

    private BattleHero2(List<Integer> skillIds, BattleTeam selfTeam, Hero hero) {
        super(skillIds, selfTeam);
        this.hero = hero;
    }

    /**
     *	 创建一个战斗英雄
     *1. 带有属性
     *2. 带有技能
     * @param selfTeam
     * @param hero
     * @return
     */
    public static BattleHero2 create(BattleTeam selfTeam, Hero hero) {
    	BattleHero2 ret = new BattleHero2(Lists.newArrayList(), selfTeam, hero);
    	ret.setHp(1000);
    	ret.setMaxHp(1000);
    	ret.setLevel((short)1);
    	return ret;
    }
    
    @Override
    public void initBattleAttributes() {
        super.initBattleAttributes();
        this.addOriginalAttrDic(hero.getAttrDic());
    }

    @Override
    public int getVocation() {
//        return ConfigHero.get(getConfigId()).getVocation();
    	return 0;
    }

    @Override
    public int getRace() {
//        return ConfigManager.get(ConfigHero.class, getConfigId()).getRace();
    	return 0;
    }

    @Override
    public byte getType() {
        return BattleEntityType.HERO;
    }

}
