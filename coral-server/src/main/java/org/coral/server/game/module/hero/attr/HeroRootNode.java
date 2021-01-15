package org.coral.server.game.module.hero.attr;

import org.coral.server.game.module.attribute.domain.AttributeDictionary;
import org.coral.server.game.module.attribute.domain.IAttributeNode;
import org.coral.server.game.module.hero.domain.Hero;

/**
 * 	武将属性根节点
 * @author Jeremy
 */
public class HeroRootNode extends AbstractHeroAttrNode {
	
    private final HeroBaseNode baseNode;

    private final HeroLevelNode levelNode;

    private final HeroStarNode starNode;

    public HeroRootNode(Hero hero) {
        super(hero);
        baseNode = new HeroBaseNode(hero);
        addChild(baseNode);
        levelNode = new HeroLevelNode(hero);
        addChild(levelNode);
        starNode = new HeroStarNode(hero);
        addChild(starNode);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public String getName() {
        return "hero" + hero.getId();
    }

    @Override
    protected AttributeDictionary calculateAttrDic() {
        AttributeDictionary dictionary = new AttributeDictionary();
        for (IAttributeNode node : childs) {
            dictionary.addAttr(node.getAttrDic());
        }
        //setPower(dictionary);
        return dictionary;
    }
    
    public HeroBaseNode getBaseNode() {
		return baseNode;
	}

    public HeroLevelNode getLevelNode() {
        return levelNode;
    }
    
    public HeroStarNode getStarNode() {
		return starNode;
	}


}
