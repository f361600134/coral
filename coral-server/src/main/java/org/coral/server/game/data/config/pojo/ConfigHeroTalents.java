package org.coral.server.game.data.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigHeroTalents {

	private int ID;//天赋新id
	private String name;//天赋名
	private String icon;//icon
	private String desc;//描述
	private int type;//天赋类型
	private List<Integer> skill;//天赋技能ID
	private List<List<Integer>> attValues;//属性
	private List<Integer> activeStars;//激活所需星格
	private List<List<Integer>> needItems;//激活所需物品
	private int needHeroNum;//所需武将数量

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
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
	public List<Integer> getSkill(){
        return skill;
    }
    public void setSkill(List<Integer> skill){
        this.skill = skill;
    }
    
	public List<List<Integer>> getAttValues(){
        return attValues;
    }
    public void setAttValues(List<List<Integer>> attValues){
        this.attValues = attValues;
    }
    
	public List<Integer> getActiveStars(){
        return activeStars;
    }
    public void setActiveStars(List<Integer> activeStars){
        this.activeStars = activeStars;
    }
    
	public List<List<Integer>> getNeedItems(){
        return needItems;
    }
    public void setNeedItems(List<List<Integer>> needItems){
        this.needItems = needItems;
    }
    
	public int getNeedHeroNum(){
        return needHeroNum;
    }
    public void setNeedHeroNum(int needHeroNum){
        this.needHeroNum = needHeroNum;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> attValuesTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attValues)
    	{
    		int c = attValuesTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attValuesTemps.put(item.get(0), c);
    	}
    	attValuesMap = attValuesTemps;
			    
		Map<Integer, Integer> needItemsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.needItems)
    	{
    		int c = needItemsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		needItemsTemps.put(item.get(0), c);
    	}
    	needItemsMap = needItemsTemps;
			    
		
		this.parseExt();
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attValuesMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttValuesMap(){
    	return attValuesMap;
    }
    
    private Map<Integer, Integer> needItemsMap = Maps.newHashMap();
    public Map<Integer, Integer> getNeedItemsMap(){
    	return needItemsMap;
    }
    
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
