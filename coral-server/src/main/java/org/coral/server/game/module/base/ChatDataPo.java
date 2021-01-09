package org.coral.server.game.module.base;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;

/**
* ChatDataPo
* @author Jeremy
*/
@PO(name = "chat_data")
public abstract class ChatDataPo extends BasePo {

	public static final String PROP_LEFTKEY = "leftKey";
	public static final String PROP_RIGHTKEY = "rightKey";
	public static final String PROP_DATA = "data";
	public static final String PROP_CHANNEL = "channel";
	public static final String PROP_READTIME = "readTime";
	
	protected long leftKey;//左key，与右key组合成唯一key
	protected long rightKey;//右key，与左key组合成唯一key
	protected String data;//玩家聊天数据
	protected int channel;//频道
	protected long readTime;//读取聊天时间
	
	public ChatDataPo(){
		this.data = "";
	}
	
	/** 左key，与右key组合成唯一key **/
	public long getLeftKey(){
		return this.leftKey;
	}
	
	public void setLeftKey(long leftKey){
		this.leftKey = leftKey;
	}
	
	/** 右key，与左key组合成唯一key **/
	public long getRightKey(){
		return this.rightKey;
	}
	
	public void setRightKey(long rightKey){
		this.rightKey = rightKey;
	}
	
	/** 玩家聊天数据 **/
	public String getData(){
		return this.data;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	/** 频道 **/
	public int getChannel(){
		return this.channel;
	}
	
	public void setChannel(int channel){
		this.channel = channel;
	}
	
	/** 读取聊天时间 **/
	public long getReadTime(){
		return this.readTime;
	}
	
	public void setReadTime(long readTime){
		this.readTime = readTime;
	}
	
	
	@Override
	public String toString() {
		return "ChatData [leftKey= "+ leftKey +", rightKey= "+ rightKey +", data= "+ data +", channel= "+ channel +", readTime= "+ readTime
				+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`leftKey`",
		"`rightKey`",
		"`data`",
		"`channel`",
		"`readTime`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getLeftKey(),
		getRightKey(),
		getData(),
		getChannel(),
		getReadTime(),
		};
	}

	@Override
	public String[] indexs() {
		return new String[] {
			PROP_LEFTKEY,
			PROP_RIGHTKEY
		};
	}
	
	@Override
	public Object[] indexValues() {
		return new Object[] {
			getLeftKey(),
			getRightKey()
		};
	}
	
	@Override
	public Object key() {
		return null;
	}

	@Override
	public String cacheId() {
		return leftKey+"_"+rightKey;
	}

	@Override
	public String keyColumn() {
		return indexs()[0];
	}
	
}
