package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigChatModel;
import org.coral.server.utils.FileUtilitys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig()
public class ConfigChatModelMgr {

    private static final Logger log = LoggerFactory.getLogger(ConfigChatModelMgr.class);
	
	private static String filename = ServerConstant.JsonPath+"ConfigChatModel.json";
	
	private volatile static Map<Integer, ConfigChatModel> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigChatModel> list = JSON.parseArray(content, ConfigChatModel.class);
		
		Map<Integer, ConfigChatModel> temps = Maps.newHashMap();
		for(ConfigChatModel config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}
	
	public static ConfigChatModel getConfig(int id) {
		ConfigChatModel data = maps.get(id);
		if(data==null)
		{
			log.error(String.format("shit! ce hua config error in ConfigChatModel! fuck him! id=%d", id));
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static int module_1 = 1;//模板1
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	public static  Map<Integer, ConfigChatModel> maps(){
		return maps;
	}
	/////////UserDefine End/////////////
	
}
