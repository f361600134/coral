package org.coral.server.game.data.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigHeroStar {

	private int ID;//星格id
	private String name;//星格名称
	private String icon;//icon
	private List<List<Integer>> attValues;//属性
	private List<List<Integer>> NeedItem;//激活所需物品

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
    
	public List<List<Integer>> getAttValues(){
        return attValues;
    }
    public void setAttValues(List<List<Integer>> attValues){
        this.attValues = attValues;
    }
    
	public List<List<Integer>> getNeedItem(){
        return NeedItem;
    }
    public void setNeedItem(List<List<Integer>> NeedItem){
        this.NeedItem = NeedItem;
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
			    
		Map<Integer, Integer> NeedItemTemps = Maps.newHashMap();
    	for(List<Integer> item : this.NeedItem)
    	{
    		int c = NeedItemTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		NeedItemTemps.put(item.get(0), c);
    	}
    	NeedItemMap = NeedItemTemps;
			    
		
		this.parseExt();
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attValuesMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttValuesMap(){
    	return attValuesMap;
    }
    
    private Map<Integer, Integer> NeedItemMap = Maps.newHashMap();
    public Map<Integer, Integer> getNeedItemMap(){
    	return NeedItemMap;
    }
    
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
