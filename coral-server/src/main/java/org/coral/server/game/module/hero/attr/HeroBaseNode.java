package org.coral.server.game.module.hero.attr;

import org.coral.server.game.data.config.ConfigHeroInfoMgr;
import org.coral.server.game.data.config.pojo.ConfigHeroInfo;
import org.coral.server.game.module.attribute.domain.AttributeDictionary;
import org.coral.server.game.module.hero.domain.Hero;

/**
 * 	武将基础属性节点
 * @author Administrator
 *
 */
public class HeroBaseNode extends AbstractHeroAttrNode {

    public HeroBaseNode(Hero hero) {
        super(hero);
    }

    @Override
    public String getName() {
        return "Base";
    }

    /**
     * 	基础属性
     */
    @Override
    protected AttributeDictionary calculateAttrDic() {
        AttributeDictionary attrDic = new AttributeDictionary();
        ConfigHeroInfo configHero = ConfigHeroInfoMgr.getConfig(hero.getConfigId());
        configHero.getAttrsMap().forEach((key, value) -> {
        	 attrDic.addAttr(key, value);
        });
        return attrDic;
    }

}
