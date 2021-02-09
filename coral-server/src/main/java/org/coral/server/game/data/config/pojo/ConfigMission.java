package org.coral.server.game.data.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigMission {

	private int ID;//任务ID
	private String name;//任务名称
	private int type;//任务类型
	private int duration;//任务时效
	private int lvlLimit;//主公等级
	private int missionLimit;//任务链
	private int otherLimitType;//VIP等级
	private List<Integer> otherLimitValue;//月卡用户
	private String desc;//任务描述
	private List<List<Integer>> reward;//任务奖励
	private int completeType;//完成条件类型
	private int completeValue;//完成条件值
	private int completeTotal;//完成进度总值
	private int isOpen;//是否开放

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    
	public int getLvlLimit(){
        return lvlLimit;
    }
    public void setLvlLimit(int lvlLimit){
        this.lvlLimit = lvlLimit;
    }
    
	public int getMissionLimit(){
        return missionLimit;
    }
    public void setMissionLimit(int missionLimit){
        this.missionLimit = missionLimit;
    }
    
	public int getOtherLimitType(){
        return otherLimitType;
    }
    public void setOtherLimitType(int otherLimitType){
        this.otherLimitType = otherLimitType;
    }
    
	public List<Integer> getOtherLimitValue(){
        return otherLimitValue;
    }
    public void setOtherLimitValue(List<Integer> otherLimitValue){
        this.otherLimitValue = otherLimitValue;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public List<List<Integer>> getReward(){
        return reward;
    }
    public void setReward(List<List<Integer>> reward){
        this.reward = reward;
    }
    
	public int getCompleteType(){
        return completeType;
    }
    public void setCompleteType(int completeType){
        this.completeType = completeType;
    }
    
	public int getCompleteValue(){
        return completeValue;
    }
    public void setCompleteValue(int completeValue){
        this.completeValue = completeValue;
    }
    
	public int getCompleteTotal(){
        return completeTotal;
    }
    public void setCompleteTotal(int completeTotal){
        this.completeTotal = completeTotal;
    }
    
	public int getIsOpen(){
        return isOpen;
    }
    public void setIsOpen(int isOpen){
        this.isOpen = isOpen;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> rewardTemps = Maps.newHashMap();
    	for(List<Integer> item : this.reward)
    	{
    		int c = rewardTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		rewardTemps.put(item.get(0), c);
    	}
    	rewardMap = rewardTemps;
			    
		
		this.parseExt();
    }
	
	//id_count ID数量
    private Map<Integer, Integer> rewardMap = Maps.newHashMap();
    public Map<Integer, Integer> getRewardMap(){
    	return rewardMap;
    }
    
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
