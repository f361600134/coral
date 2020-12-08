package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigChat;
import org.coral.server.utils.FileUtilitys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigChatMgr {

    private static final Logger log = LoggerFactory.getLogger(ConfigChatMgr.class);
	
	private static String filename = ServerConstant.JsonPath+"ConfigChat.json";
	
	private volatile static Map<Integer, ConfigChat> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigChat> list = JSON.parseArray(content, ConfigChat.class);
		
		Map<Integer, ConfigChat> temps = Maps.newHashMap();
		for(ConfigChat config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigChat getConfig(int id) {
		ConfigChat data = maps.get(id);
		if(data==null)
		{
			log.error(String.format("shit! ce hua config error in ConfigChat! fuck him! id=%d", id));
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	public static void analyse(){
	}
	
	public static void complete(){
	}
	
	public static  Map<Integer, ConfigChat> maps(){
		return maps;
	}
	/////////UserDefine End/////////////
	
}
