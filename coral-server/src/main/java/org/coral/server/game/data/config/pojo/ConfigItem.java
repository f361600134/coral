package org.coral.server.game.data.config.pojo;

import org.coral.server.game.data.config.IConfig;

public class ConfigItem implements IConfig{

	private int ID;//ID
	private String name;//物品名称
	private int stack;//物品叠加上限
	private int quality;//物品品质
	private int effectType;//使用后触发效果类型
	private int effectParameter;//使用后触发效果参数

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
    
	public int getStack(){
        return stack;
    }
    public void setStack(int stack){
        this.stack = stack;
    }
    
	public int getQuality(){
        return quality;
    }
    public void setQuality(int quality){
        this.quality = quality;
    }
    
	public int getEffectType(){
        return effectType;
    }
    public void setEffectType(int effectType){
        this.effectType = effectType;
    }
    
	public int getEffectParameter(){
        return effectParameter;
    }
    public void setEffectParameter(int effectParameter){
        this.effectParameter = effectParameter;
    }
    

	////////////////////// 特殊扩展 //////////////
    
    public static int COMPOSE = 0;
    public static int DECOMPOSE = 1;
	
	public void parse(){
		
		
		
		this.parseExt();
    }
	
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
