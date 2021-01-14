package org.coral.server.game.module.base;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;

/**
* HeroPo
* @author Jeremy
*/
@PO(name = "hero")
public abstract class HeroPo extends BasePo {

	public static final String PROP_ID = "id";
	public static final String PROP_PLAYERID = "playerId";
	public static final String PROP_CONFIGID = "configId";
	public static final String PROP_LEVEL = "level";
	
	protected long id;//武将唯一id
	protected long playerId;//所属玩家id
	protected int configId;//配置id
	protected int level;//等级
	
	public HeroPo(){
	}
	
	/** 武将唯一id **/
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	/** 所属玩家id **/
	public long getPlayerId(){
		return this.playerId;
	}
	
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/** 配置id **/
	public int getConfigId(){
		return this.configId;
	}
	
	public void setConfigId(int configId){
		this.configId = configId;
	}
	
	/** 等级 **/
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	
	@Override
	public String toString() {
		return "Hero [id= "+ id +", playerId= "+ playerId +", configId= "+ configId +", level= "+ level+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`id`",
		"`playerId`",
		"`configId`",
		"`level`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getId(),
		getPlayerId(),
		getConfigId(),
		getLevel(),
		};
	}

	@Override
	public String[] indexs() {
		return new String[] {
			PROP_ID,
		};
	}
	
	@Override
	public Object[] indexValues() {
		return new Object[] {
			getId(),
		};
	}
	
	@Override
	public Object key() {
		// 第一主键的数据值, 例如playerId
		return getId();
	}

	@Override
	public String cacheId() {
		// 缓存的二级ID, 如果不是一对多关系的return null即可
		return String.valueOf(getId());
	}

	@Override
	public String keyColumn() {
		// 第一主键的数据库列名
		return indexs()[0];
	}
	
}
