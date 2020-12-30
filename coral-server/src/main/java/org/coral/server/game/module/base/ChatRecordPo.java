package org.coral.server.game.module.base;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;

/**
* ChatRecordPo
* @author Jeremy
*/
@PO(name = "chat_record")
public abstract class ChatRecordPo extends BasePo {

	public static final String PROP_UNIQUEID = "uniqueId";
	public static final String PROP_CHANNEL = "channel";
	public static final String PROP_DATA = "data";
	
	protected long uniqueId;//唯一id
	protected int channel;//频道号
	protected String data;//私聊数据
	
	public ChatRecordPo(){
		this.data = "";
	}
	
	/** 唯一id **/
	public long getUniqueId(){
		return this.uniqueId;
	}
	
	public void setUniqueId(long uniqueId){
		this.uniqueId = uniqueId;
	}
	
	/** 频道号 **/
	public int getChannel(){
		return this.channel;
	}
	
	public void setChannel(int channel){
		this.channel = channel;
	}
	
	/** 私聊数据 **/
	public String getData(){
		return this.data;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	
	@Override
	public String toString() {
		return "ChatRecord [uniqueId= "+ uniqueId +", channel= "+ channel +", data= "+ data+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`uniqueId`",
		"`channel`",
		"`data`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getUniqueId(),
		getChannel(),
		getData(),
		};
	}

	@Override
	public String[] indexs() {
		return new String[] {
			PROP_UNIQUEID,
		};
	}
	
	@Override
	public Object[] indexValues() {
		return new Object[] {
			getUniqueId(),
		};
	}
	
	@Override
	public Object key() {
		// 第一主键的数据值, 例如playerId
		//return getId();
		return getUniqueId();
	}

	@Override
	public String cacheId() {
		// 缓存的二级ID, 如果不是一对多关系的return null即可
		//return String.valueOf(getId());
		return String.valueOf(getUniqueId());
	}

	@Override
	public String keyColumn() {
		// 第一主键的数据库列名
		return indexs()[0];
	}
	
}
