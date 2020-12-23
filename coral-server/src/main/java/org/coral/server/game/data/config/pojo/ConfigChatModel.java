package org.coral.server.game.data.config.pojo;

import org.coral.server.game.data.config.IConfig;

public class ConfigChatModel implements IConfig{

	private int ID;//ID
	private String modelDesc;//模板内容

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getModelDesc(){
        return modelDesc;
    }
    public void setModelDesc(String modelDesc){
        this.modelDesc = modelDesc;
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
