package org.coral.server.game.module.battle.domain;

import java.util.List;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.battle.assist.BattleDefine.BattleEntityType;
import org.coral.server.game.module.hero.domain.Hero;
import org.coral.server.game.module.hero.service.IHeroService;

/**
 * 英雄
 *
 * @author Klass
 * @date 2020/9/11 14:46
 */
public class BattleHero extends BattleEntity {

	private long playerId;
    private long heroId;

    public BattleHero(List<Integer> skillIds, BattleTeam selfTeam, long heroId, long playerId) {
        super(skillIds, selfTeam);
        this.heroId = heroId;
        this.playerId = playerId;
//        Hero hero = Hero.load(Hero.class, heroId);
//        setConfigId(hero.getConfigId());
//        for (Rune rune : hero.getRunes()) {
//            addBattleSkills(rune.getSkills());
//        }
    }

    @Override
    public void initBattleAttributes() {
        super.initBattleAttributes();
        IHeroService service = SpringContextHolder.getInstance().getBean(IHeroService.class);
        Hero hero = service.getHero(playerId, heroId);
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
