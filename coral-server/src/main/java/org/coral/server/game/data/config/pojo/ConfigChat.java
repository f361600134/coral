package org.coral.server.game.data.config.pojo;

import org.coral.server.game.data.config.IConfig;

public class ConfigChat implements IConfig{

	private int ID;//ID
	private String channelName;//频道名称
	private int unlockLevel;//频道解锁等级
	private int speakNeedLevel;//玩家发言所需等级
	private int interval;//玩家发言间隔
	private int speakLimitTime;//发言限制间隔
	private int cacheNum;//缓存数量
	private int broadcastIntervalTime;//广播间隔时间
	private int defaultBroadcastNum;//默认广播容量超出立即广播

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getChannelName(){
        return channelName;
    }
    public void setChannelName(String channelName){
        this.channelName = channelName;
    }
    
	public int getUnlockLevel(){
        return unlockLevel;
    }
    public void setUnlockLevel(int unlockLevel){
        this.unlockLevel = unlockLevel;
    }
    
	public int getSpeakNeedLevel(){
        return speakNeedLevel;
    }
    public void setSpeakNeedLevel(int speakNeedLevel){
        this.speakNeedLevel = speakNeedLevel;
    }
    
	public int getInterval(){
        return interval;
    }
    public void setInterval(int interval){
        this.interval = interval;
    }
    
	public int getSpeakLimitTime(){
        return speakLimitTime;
    }
    public void setSpeakLimitTime(int speakLimitTime){
        this.speakLimitTime = speakLimitTime;
    }
    
	public int getCacheNum(){
        return cacheNum;
    }
    public void setCacheNum(int cacheNum){
        this.cacheNum = cacheNum;
    }
    
	public int getBroadcastIntervalTime(){
        return broadcastIntervalTime;
    }
    public void setBroadcastIntervalTime(int broadcastIntervalTime){
        this.broadcastIntervalTime = broadcastIntervalTime;
    }
    
	public int getDefaultBroadcastNum(){
        return defaultBroadcastNum;
    }
    public void setDefaultBroadcastNum(int defaultBroadcastNum){
        this.defaultBroadcastNum = defaultBroadcastNum;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		
		
		this.parseExt();
    }
	
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
