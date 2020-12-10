package org.coral.server.game.module.base;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;

/**
* ItemPo
* @author Jeremy
*/
@PO(name = "item")
public abstract class ItemPo extends BasePo {

	public static final String PROP_PLAYERID = "playerId";
	public static final String PROP_ITEMID = "itemId";
	public static final String PROP_CONFIGID = "configId";
	public static final String PROP_COUNT = "count";
	public static final String PROP_RECIEVETIME = "recieveTime";
	public static final String PROP_PARAMS = "params";
	
	protected long playerId;//玩家ID
	protected long itemId;//道具唯一id
	protected int configId;//道具配置id
	protected int count;//物品数量
	protected int recieveTime;//获得时间
	protected String params;//额外参数如符文的随机属性、技能,神装随机属性
	
	public ItemPo(){
		this.params = "";
	}
	
	/** 玩家ID **/
	public long getPlayerId(){
		return this.playerId;
	}
	
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/** 道具唯一id **/
	public long getItemId(){
		return this.itemId;
	}
	
	public void setItemId(long itemId){
		this.itemId = itemId;
	}
	
	/** 道具配置id **/
	public int getConfigId(){
		return this.configId;
	}
	
	public void setConfigId(int configId){
		this.configId = configId;
	}
	
	/** 物品数量 **/
	public int getCount(){
		return this.count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	/** 获得时间 **/
	public int getRecieveTime(){
		return this.recieveTime;
	}
	
	public void setRecieveTime(int recieveTime){
		this.recieveTime = recieveTime;
	}
	
	/** 额外参数如符文的随机属性、技能,神装随机属性 **/
	public String getParams(){
		return this.params;
	}
	
	public void setParams(String params){
		this.params = params;
	}
	
	
	@Override
	public String toString() {
		return "Item[ playerId= " +getPlayerId()+ "+, playerId= "+ playerId +", itemId= "+ itemId +", configId= "+ configId +", count= "+ count +", recieveTime= "+ recieveTime
				 +", params= "+ params+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`playerId`",
		"`itemId`",
		"`configId`",
		"`count`",
		"`recieveTime`",
		"`params`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getPlayerId(),
		getItemId(),
		getConfigId(),
		getCount(),
		getRecieveTime(),
		getParams(),
		};
	}

	@Override
	public String[] indexs() {
		return new String[] {
			PROP_PLAYERID,
		};
	}
	
	@Override
	public Object[] indexValues() {
		return new Object[] {
			getPlayerId(),
		};
	}
	
	@Override
	public Object key() {
		// 第一主键的数据值, 例如playerId
		//return getId();
		return getItemId();
	}

	@Override
	public String cacheId() {
		// 缓存的二级ID, 如果不是一对多关系的return null即可
		//return String.valueOf(getId());
		return String.valueOf(getItemId());
	}

	@Override
	public String keyColumn() {
		// 第一主键的数据库列名
		return PROP_ITEMID;
	}
	
}
