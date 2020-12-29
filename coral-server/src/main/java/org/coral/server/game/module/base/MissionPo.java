package org.coral.server.game.module.base;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;

/**
* MissionPo
* @author Jeremy
*/
@PO(name = "mission")
public abstract class MissionPo extends BasePo {

	public static final String PROP_CONFIGID = "configId";
	public static final String PROP_PLAYERID = "playerId";
	public static final String PROP_STATE = "state";
	public static final String PROP_PROGRESS = "progress";
	public static final String PROP_RECVTIME = "recvTime";
	
	protected int configId;//任务ID
	protected int playerId;//角色ID
	protected int state;//任务状态:0=未接取;1=已接取未完成;2=已完成未领取;3=已完成已领取
	protected int progress;//任务进度
	protected long recvTime;//任务接取时间
	
	public MissionPo(){
	}
	
	/** 任务ID **/
	public int getConfigId(){
		return this.configId;
	}
	
	public void setConfigId(int configId){
		this.configId = configId;
	}
	
	/** 角色ID **/
	public int getPlayerId(){
		return this.playerId;
	}
	
	public void setPlayerId(int playerId){
		this.playerId = playerId;
	}
	
	/** 任务状态:0=未接取;1=已接取未完成;2=已完成未领取;3=已完成已领取 **/
	public int getState(){
		return this.state;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	/** 任务进度 **/
	public int getProgress(){
		return this.progress;
	}
	
	public void setProgress(int progress){
		this.progress = progress;
	}
	
	/** 任务接取时间 **/
	public long getRecvTime(){
		return this.recvTime;
	}
	
	public void setRecvTime(long recvTime){
		this.recvTime = recvTime;
	}
	
	
	@Override
	public String toString() {
		return "Mission[ playerId= " +getPlayerId()+ "+, configId= "+ configId +", playerId= "+ playerId +", state= "+ state +", progress= "+ progress +", recvTime= "+ recvTime
				+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`configId`",
		"`playerId`",
		"`state`",
		"`progress`",
		"`recvTime`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getConfigId(),
		getPlayerId(),
		getState(),
		getProgress(),
		getRecvTime(),
		};
	}

	@Override
	public String[] indexs() {
		return new String[] {
			PROP_PLAYERID,
			PROP_CONFIGID,
		};
	}
	
	@Override
	public Object[] indexValues() {
		return new Object[] {
			getPlayerId(),
			getConfigId(),
		};
	}
	
	@Override
	public Object key() {
		// 第一主键的数据值, 例如playerId
		//return getId();
		return getPlayerId();
	}

	@Override
	public String cacheId() {
		// 缓存的二级ID, 如果不是一对多关系的return null即可
		//return String.valueOf(getId());
		return String.valueOf(getPlayerId());
	}

	@Override
	public String keyColumn() {
		// 第一主键的数据库列名
		return indexs()[0];
	}
	
}
