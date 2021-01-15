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
	public static final String PROP_STARIDSTR = "starIdStr";
	public static final String PROP_TALENT = "talent";
	public static final String PROP_USEDMATERIALSTR = "usedMaterialStr";
	
	protected long id;//武将唯一id
	protected long playerId;//所属玩家id
	protected int configId;//配置id
	protected int level;//等级
	protected String starIdStr;//星格信息,List
	protected int talent;//已激活天赋
	protected String usedMaterialStr;//记录使用材料
	
	public HeroPo(){
		this.starIdStr = "";
		this.usedMaterialStr = "";
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
	
	/** 星格信息,List **/
	public String getStarIdStr(){
		return this.starIdStr;
	}
	
	public void setStarIdStr(String starIdStr){
		this.starIdStr = starIdStr;
	}
	
	/** 已激活天赋 **/
	public int getTalent(){
		return this.talent;
	}
	
	public void setTalent(int talent){
		this.talent = talent;
	}
	
	/** 记录使用材料 **/
	public String getUsedMaterialStr(){
		return this.usedMaterialStr;
	}
	
	public void setUsedMaterialStr(String usedMaterialStr){
		this.usedMaterialStr = usedMaterialStr;
	}
	
	
	@Override
	public String toString() {
		return "Hero [id= "+ id +", playerId= "+ playerId +", configId= "+ configId +", level= "+ level +", starIdStr= "+ starIdStr
				 +", talent= "+ talent +", usedMaterialStr= "+ usedMaterialStr+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`id`",
		"`playerId`",
		"`configId`",
		"`level`",
		"`starIdStr`",
		"`talent`",
		"`usedMaterialStr`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getId(),
		getPlayerId(),
		getConfigId(),
		getLevel(),
		getStarIdStr(),
		getTalent(),
		getUsedMaterialStr(),
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
		//return getId();
		return getId();
	}

	@Override
	public String cacheId() {
		// 缓存的二级ID, 如果不是一对多关系的return null即可
		//return String.valueOf(getId());
		return String.valueOf(getId());
	}

	@Override
	public String keyColumn() {
		// 第一主键的数据库列名
		return indexs()[0];
	}
	
}
