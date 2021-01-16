package org.coral.server.game.data.config;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.pojo.ConfigHeroInfo;
import org.coral.server.utils.FileUtilitys;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

@AnnotationConfig
public class ConfigHeroInfoMgr {

    private static final Logger LOGGER = LogManager.getLogger(ConfigHeroInfoMgr.class);
	
	private static String filename = ServerConstant.JsonPath+"ConfigHeroInfo.json";
	
	private static Map<Integer, ConfigHeroInfo> maps = Maps.newHashMap();
	
	public static void load(){
		String content = FileUtilitys.ReadFile(filename);
		List<ConfigHeroInfo> list = JSON.parseArray(content, ConfigHeroInfo.class);
		
		Map<Integer, ConfigHeroInfo> temps = Maps.newHashMap();
		for(ConfigHeroInfo config : list)
		{
			config.parse();
			temps.put(config.getID(), config);
		}
		maps = temps;
	}

	public static ConfigHeroInfo getConfig(int id) {
		ConfigHeroInfo data = maps.get(id);
		if(data==null)
		{
			LOGGER.error("shit! ce hua config error in ConfigHeroInfo! fuck him! id={}", id);
		}
		return data;
	}
	
	public static boolean exist(int id) {
		return maps.containsKey(id);
	}
	
	/////////UserDefine Begin///////////
	
	/*武将资质*/
	public static final int Quality_Green_60 = 1;	//60绿色
	public static final int Quality_Blue_80 = 2;	//80蓝色
	public static final int Quality_Purple_100 = 3;	//100紫色
	public static final int Quality_Purple_120 = 4;	//120紫色
	public static final int Quality_Origin_150 = 5;	//150橙色
	
	private static Map<Integer, Integer> godHeroMaps;	//原始id:神将id
	
	/**
	 * 计算消耗元宝公式
	 */
	public static int calCostDiamond(int starlv, int artfactLv) {
		int starCost = (int)(1.666 * starlv * starlv + 4.977 * starlv + 2.526);
		int artifactCost = (int)(0.049 * artfactLv * artfactLv + 4.661 * artfactLv - 3.437);
		int cost = starCost + artifactCost;
		return cost;
	}
	
	public static void analyse(){
		Map<Integer, Integer> tempGodHeroMaps = Maps.newHashMap();
		for(ConfigHeroInfo config : maps.values())
		{//神将
			if(config.getGodHeroSkill()>0 && config.getStarHeroSkill()==0)
				tempGodHeroMaps.put(config.getInitialConfidId(), config.getID());
		}
		godHeroMaps = tempGodHeroMaps;
	}
	public static void complete(){
	}
	
	public static int getGodHeroId(int initHeroId) {
		return godHeroMaps.getOrDefault(initHeroId, 0);
	}
	
	/////////UserDefine End/////////////
	
}
