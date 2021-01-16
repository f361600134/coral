package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigBuffer;
import org.coral.server.utils.FileUtilitys;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigBufferMgr {

    private static final Logger LOGGER = LogManager.getLogger(ConfigBufferMgr.class);
	
	private static String filename = ServerConstant.JsonPath+"ConfigBuffer.json";
	
	private static Map<Integer, ConfigBuffer> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigBuffer> list = JSON.parseArray(content, ConfigBuffer.class);
		
		Map<Integer, ConfigBuffer> temps = Maps.newHashMap();
		for(ConfigBuffer config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigBuffer getConfig(int id) {
		ConfigBuffer data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigBuffer! fuck him! id={}", id);
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
	
	/////////UserDefine End/////////////
	
}
