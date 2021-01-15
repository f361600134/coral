package org.coral.server.game.module.hero.domain;

import java.util.List;
import java.util.Map;

import org.coral.orm.core.annotation.Column;
import org.coral.server.game.module.attribute.domain.IAttributeEntity;
import org.coral.server.game.module.attribute.domain.IAttributeNode;
import org.coral.server.game.module.base.HeroPo;
import org.coral.server.game.module.hero.attr.HeroRootNode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 *	 武将实体类
 * @author Jeremy
 */
public class Hero extends HeroPo implements IAttributeEntity {

	private static final long serialVersionUID = -6477234322349578720L;

	/**
	 * 	已激活星格id列表(不包含未激活)
	 */
	@Column(PROP_STARIDSTR)
	private List<Integer> starIds;
	
	/**
	 * 	培养的消耗:{configId:num,configId:num}
	 */
	@Column(PROP_USEDMATERIALSTR)
	private Map<Integer, Integer> usedMaterials;

	/**
	 * 	总星级 天赋index,从0开始
	 */
	private transient int starLevel;
	
	/**
	 * 武将属性根节点
	 */
	private transient HeroRootNode heroAttrNode;

	public Hero() {
		this.starIds = Lists.newArrayList();
		this.usedMaterials = Maps.newHashMap();
		this.heroAttrNode = new HeroRootNode(this);
	}
	
	public Hero(long playerId, int configId) {
		this.playerId = playerId;
		this.configId = configId;
		this.starIds = Lists.newArrayList();
		this.usedMaterials = Maps.newHashMap();
		this.heroAttrNode = new HeroRootNode(this);
	}

	public List<Integer> getStarIds() {
		return starIds;
	}

	public int getStarLevel() {
		return starLevel;
	}
	
	public Map<Integer, Integer> getUsedMaterials() {
		return usedMaterials;
	}

	@Override
	public IAttributeNode getAttributeNode() {
		return heroAttrNode;
	}
	
	public static Hero create(long playerId, int configId) {
		Hero hero = new Hero(playerId, configId);
		return hero;
	}
	
}
