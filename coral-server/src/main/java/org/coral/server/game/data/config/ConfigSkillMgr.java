package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigSkill;
import org.coral.server.utils.FileUtilitys;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigSkillMgr {

    private static final Logger LOGGER = LogManager.getLogger(ConfigSkillMgr.class);
	
	private static String filename =ServerConstant.JsonPath +"ConfigSkill.json";
	
	private static Map<Integer, ConfigSkill> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigSkill> list = JSON.parseArray(content, ConfigSkill.class);
		
		Map<Integer, ConfigSkill> temps = Maps.newHashMap();
		for(ConfigSkill config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigSkill getConfig(int id) {
		ConfigSkill data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigSkill! fuck him! id={}", id);
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
