package org.coral.server.game.module.chatplus.service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.coral.net.core.base.GameSession;
import org.coral.server.game.data.proto.PBPlayer.ReqChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component
public class CommandService{
	
	private static final Log log = LogFactory.getLog(CommandService.class);
	
	private String CommandPlayer = "@player ";
	private String CommandBag = "@bag ";
	private String CommandHero = "@hero ";
	private String CommandFull = "@full ";
	private String CommandCount = "@count ";
	private String CommandFamily = "@family ";
	private String CommandFight = "@fight ";
	private String CommandRecharge = "@recharge ";
	private String CommandArena = "@arena ";
	//服务器用于测试
	//@test method,param1,param2...
	private String CommandTest = "@test ";
	
//	@Autowired
//	private ItemService itemService;
//	
	@Autowired
	private CommandAssist commandAssist;
//	@Autowired
//	private SessionManager sessionManager;
	
	public void gmFromClient(GameSession session, ReqChat data){
//		if(!Config.OpenGMFromClient) {
//			return;
//		}
		long playerId = session.getPlayerId();
		String content = data.getContent().toLowerCase().trim();
		if(content.startsWith(CommandPlayer))
		{			
			
		}else if (content.startsWith(CommandBag)) {
			String params = content.substring(CommandBag.length());
			List<Integer> paramList = this.formatIntegerList(params);
			Map<Integer, Integer> addGoods = Maps.newHashMap();
			Map<Integer, Integer> deductGoods = Maps.newHashMap();
			for(int i=1; i<paramList.size(); i=i+2)
			{
				int configId = paramList.get(i-1);
				int count = paramList.get(i);
				if (count > 0) {
					addGoods.put(configId, count);
				}else {
					deductGoods.put(configId, Math.abs(count));
				}
			} 
//			itemService.addNewItem(playerId, addGoods, NatureEnum.GM, "");
//			itemService.deductItem(playerId, deductGoods, NatureEnum.GM, "");
		}else if (content.startsWith(CommandTest)) {
			String params = content.substring(CommandTest.length());
			String paramArr[] = params.split(",");
			String methodName = paramArr[0];
			Method method = CommandAssist.methodMap.get(methodName);
			if (method != null) {
				try {
					method.invoke(commandAssist, playerId, paramArr);
				} catch (Exception e) {
					log.error("GM 执行测试出错, 测试参数:"+ Arrays.toString(paramArr));
				} 
			}
		}else if (content.startsWith(CommandRecharge)){
//			try {
//				String params = content.substring(CommandRecharge.length());
//				Context.getRechargeService().doExcharge(playerId, Integer.parseInt(params),NatureEnum.GM_RECHARGE);
//				ActivityNewAstrict activityNewAstrict = (ActivityNewAstrict) Context.getActivityService().getActivity(playerId, ActivityEnum.NEW_SERVER_LIMIT_BUY.getActivityId());
//				activityNewAstrict.doExcharge(playerId, Integer.parseInt(params));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			Context.getCheckinService().onCharge(playerId);
		}else if(content.startsWith(CommandArena)) {
//			String params = content.substring(CommandArena.length());
//			int type=Integer.parseInt(params);
//			//开启冠军赛
//			if(type==0) {
//				Context.getArenaService().openChampionForManual();
//			}
		}else if(content.startsWith(CommandHero)) {
//			String params = content.substring(CommandHero.length());
//			String[] paramArray = params.split(",");
//			String param = paramArray[0];
//			int star = Integer.parseInt(paramArray[1]);
//			//增加英雄
//			Map<Integer, Integer> added = Maps.newHashMap();
//			if (param.toLowerCase().equals("all")) {
//				for (ConfigHero config : ConfigHeroMgr.maps().values()) {
//					if (config.getStar() != star) {
//						continue;
//					}
//					added.put(config.getID(), 1);
//				}
//			}
//			itemService.addNewItem(playerId, added, NatureEnum.GM, "");
		}else if(content.startsWith(CommandFight)) {
//			String params = content.substring(CommandFight.length());
//			String[] paramArray = params.split(",");
//			String param = paramArray[0];
//			int configId = Integer.parseInt(paramArray[1]);
//			if (param.equals("dungeon")) {
//				Context.getDungeonService().reqFightBoss(playerId, configId);
//			}
		}
	}
	
	public boolean isGmCommand(String content){
		if(content==null) {
			return false;
		}else
		{
			return content.toLowerCase().startsWith(CommandBag) 
					|| content.toLowerCase().startsWith(CommandPlayer)
					|| content.toLowerCase().startsWith(CommandHero)
					|| content.toLowerCase().startsWith(CommandTest)
					|| content.toLowerCase().startsWith(CommandFull)
					|| content.toLowerCase().startsWith(CommandCount)
					|| content.toLowerCase().startsWith(CommandFamily)
					|| content.toLowerCase().startsWith(CommandFight)
					|| content.toLowerCase().startsWith(CommandRecharge)
					|| content.toLowerCase().startsWith(CommandArena)
					;
		}
	}
	
	private List<Integer> formatIntegerList(String params){
		String[] paramArray = params.split(",");
		List<Integer> paramList = Lists.newArrayList();
		for(String param : paramArray) {
			paramList.add(Integer.parseInt(param));
		}
		return paramList;
	}
	
	private Map<String, Integer> formatMap(String params){
		String[] paramArray = params.split(",");
		Map<String, Integer> paramMap = Maps.newHashMap();
		for(int i=1; i<paramArray.length; i=i+2) {
			paramMap.put(paramArray[i - 1], Integer.parseInt(paramArray[i]));
		}
		return paramMap;
	}
 	
}
