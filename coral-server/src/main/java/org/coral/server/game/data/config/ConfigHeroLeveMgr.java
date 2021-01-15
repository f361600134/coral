package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigHeroLeve;
import org.coral.server.game.module.attribute.domain.AttributeDictionary;
import org.coral.server.game.module.attribute.domain.AttributeType;
import org.coral.server.utils.FileUtilitys;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigHeroLeveMgr {

    private static final Logger LOGGER = LogManager.getLogger(ConfigHeroLeveMgr.class);
	
	private static String filename = ServerConstant.JsonPath+"ConfigHeroLeve.json";
	
	private static Map<Integer, ConfigHeroLeve> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigHeroLeve> list = JSON.parseArray(content, ConfigHeroLeve.class);
		
		Map<Integer, ConfigHeroLeve> temps = Maps.newHashMap();
		for(ConfigHeroLeve config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigHeroLeve getConfig(int id) {
		ConfigHeroLeve data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigHeroLeve! fuck him! id={}", id);
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
