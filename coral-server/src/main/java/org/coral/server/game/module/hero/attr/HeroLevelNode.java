package org.coral.server.game.module.hero.attr;

import org.coral.server.game.data.config.ConfigHeroLeveMgr;
import org.coral.server.game.data.config.pojo.ConfigHeroLeve;
import org.coral.server.game.module.attribute.domain.AttributeDictionary;
import org.coral.server.game.module.hero.domain.Hero;

/**
 * 	武将等级属性节点
 * @author Administrator
 *
 */
public class HeroLevelNode extends AbstractHeroAttrNode {

    public HeroLevelNode(Hero hero) {
        super(hero);
    }

    @Override
    public String getName() {
        return "level";
    }

    /**
     * 	等级属性
     */
    @Override
    protected AttributeDictionary calculateAttrDic() {
        AttributeDictionary attrDic = new AttributeDictionary();
        int level = hero.getLevel();
        ConfigHeroLeve config = ConfigHeroLeveMgr.getConfig(hero.getConfigId());
        AttributeDictionary dictionary = config.initBaseAttr();
        dictionary.getDictionary().forEach((key, value)->{
        	attrDic.addAttr(key, value * level);
        });
        return attrDic;
    }

}
