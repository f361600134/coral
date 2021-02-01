package org.coral.server.game.module.battle.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

/**
 * 	战斗队伍
 * @author Jeremy
 *
 */
public class BattleTeam {

	 /**
     * 	队伍类型
     * 1：攻击方
     * 2：防御方
     */
    private byte type;
    
    /**
     * 	战斗实体
     */
    private List<BattleEntity> entities;
	
    public BattleTeam() {
    	this.entities = Lists.newArrayList();
    }
    
    public void setType(byte type) {
		this.type = type;
	}
    
    public byte getType() {
		return type;
	}
    
    /**
     * 	队伍中的所有实体是否全部死亡
     *
     * @return
     */
    public boolean isAllDead() {
        return entities.stream().filter(e -> !e.isDead()).count() == 0;
    }
    
    /**
     *	获取还活着的战斗实体
     *
     * @return
     */
    public List<BattleEntity> getAliveEntities() {
    	List<BattleEntity> ret = Lists.newArrayList();
    	entities.forEach(entity ->{
    		if (!entity.isAlive()) {
				return;
			}
    		if (!entity.isCanBaAttacked()) {
				return;
			}
    		ret.add(entity);
    	});
    	return ret;
//        return entities.stream().filter(BattleEntity::isAlive)
//                .filter(BattleEntity::isCanBaAttacked)
//                .collect(Collectors.toList());
    }
    
    /**
     *	 新增一个武将战斗对象
     * @param entity
     */
    public void addBattleEntity(BattleEntity entity) {
    	this.entities.add(entity);
    }
}
