package org.coral.server.game.module.hero.attr;

import java.util.List;

import org.coral.server.game.data.config.ConfigBufferMgr;
import org.coral.server.game.data.config.ConfigHeroInfoMgr;
import org.coral.server.game.data.config.ConfigHeroStarMgr;
import org.coral.server.game.data.config.ConfigHeroTalentsMgr;
import org.coral.server.game.data.config.pojo.ConfigBuffer;
import org.coral.server.game.data.config.pojo.ConfigHeroInfo;
import org.coral.server.game.data.config.pojo.ConfigHeroStar;
import org.coral.server.game.data.config.pojo.ConfigHeroTalents;
import org.coral.server.game.module.attribute.domain.AttributeDictionary;
import org.coral.server.game.module.hero.domain.Hero;

import com.google.common.collect.Lists;

/**
 * 武将星格属性节点
 * 
 * @author Administrator
 *
 */
public class HeroStarNode extends AbstractHeroAttrNode {

	public HeroStarNode(Hero hero) {
		super(hero);
	}

	@Override
	public String getName() {
		return "Star";
	}

	/**
	 * 基础属性
	 */
	@Override
	protected AttributeDictionary calculateAttrDic() {
		AttributeDictionary attrDic = new AttributeDictionary();
		int starLevel = hero.getStarLevel();
		// 天赋属性
		List<Integer> starList = Lists.newArrayList();
		ConfigHeroInfo configHero = ConfigHeroInfoMgr.getConfig(hero.getConfigId());
		for (int i = 0; i < starLevel; i++) {// 已激活的天赋
			int talentId = configHero.getTalents().get(i);
			ConfigHeroTalents configTalent = ConfigHeroTalentsMgr.getConfig(talentId);
			attrDic.addAttr(configTalent.getAttValuesMap());
			// 天赋buffer属性
			configTalent.getSkill().forEach(bufferId ->{
				ConfigBuffer configBuf = ConfigBufferMgr.getConfig(bufferId);
				if (configBuf == null || configBuf.getSeverClient() != 1) {
					return;
				}
				attrDic.addAttr(configBuf.getAttValuesMap());
			});
			starList.addAll(configTalent.getActiveStars());
		}
		// 星格属性
		starList.addAll(hero.getStarIds());
		starList.forEach(starId -> {
			ConfigHeroStar configStar = ConfigHeroStarMgr.getConfig(starId);
			attrDic.addAttr(configStar.getAttValuesMap());
		});
		return attrDic;
	}

}
