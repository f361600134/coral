package org.coral.server.game.module.player.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IProtocol;
import org.coral.orm.core.Processor;
import org.coral.server.core.event.GameEventBus;
import org.coral.server.game.data.proto.PBLogin.ReqLogin;
import org.coral.server.game.helper.PropertiesType;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.domain.PlayerContext;
import org.coral.server.game.module.player.event.PlayerLoadEndEvent;
import org.coral.server.game.module.player.event.PlayerLoadEvent;
import org.coral.server.game.module.player.proto.AckLoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;

@Component
public class PlayerService implements IPlayerService{
	
	private static final Logger logger = LogManager.getLogger(PlayerService.class);
	
	@Autowired private Processor process;
	
	/**
	 * key: 玩家id
	 * value: 玩家上下文
	 */
	private Map<Long, PlayerContext> playerMap = Maps.newConcurrentMap();
	
	/**
	 * 玩家登陆缓存信息
	 * key: 玩家账号 username
	 * value: true
	 */
	private Cache<String, PlayerContext> cache = CacheBuilder.newBuilder()
			.expireAfterAccess(10, TimeUnit.MINUTES)// 在给定时间内没有被读/写访问,则清除
			.maximumSize(2 << 10)// 最大容量
			.initialCapacity(2 << 4)// 初始容量
			.build();
	
	/**
	 * 通过玩家id获取一个玩家上下文
	 * @date 2020年7月17日
	 * @param playerId
	 * @return
	 */
	public PlayerContext getPlayerContext(Long playerId) {
		return playerMap.get(playerId);
	}
	
	/**
	 *  获取所有在线玩家id
	 * @return void  
	 * @date 2020年8月24日下午2:57:19
	 */
	public Collection<Long> getPlayerIds(){
		return playerMap.keySet();
	}
	
	/**
	 * 添加一个新的玩家上下文对象
	 * @param context  
	 * @return void  
	 * @date 2020年9月8日下午6:42:11
	 */
	public void addContext(PlayerContext context) {
		this.playerMap.put(context.getPlayerId(), context);
	}
	
	/**
	 * 通过玩家账号获取一个玩家对象
	 * @param username
	 * @return  
	 * @return PlayerContext  
	 * @date 2020年9月7日下午5:37:16
	 */
	public PlayerContext getOrCreatePlayer(String username, int initServerId) {
		String key = getCacheKey(username, initServerId);
		PlayerContext context = cache.getIfPresent(key);
		if (context == null) {
			context = new PlayerContext();
			cache.put(key, context);
		}
		return context;
	}
	
	private String getCacheKey(String username, int initServerId) {
		String key = username.concat("-").concat(String.valueOf(initServerId));
		return key;
	}
	
	/**
	 * 查询一个玩家
	 * @date 2020年7月17日
	 * @param playerId
	 * @return
	 */
	private Player loadPlayer(String username, int initServerId) {
		Object[] props = new Object[] {Player.PROP_ACCOUNTNAME, Player.PROP_INITSERVERID};
		Object[] objs = new Object[] {username, initServerId};
		Player player = (Player)process.selectByIndex(Player.class, props, objs);
		return player;
	}
	
	/**
	 * 发送消息
	 *   
	 * @return void  
	 * @date 2020年8月24日下午2:57:19
	 */
	public void sendMessage(long playerId, IProtocol protocol){
		final PlayerContext context = getPlayerContext(playerId);
		context.send(protocol);
	}
	
	/**
	 * 增加属性
	 * @return void  
	 * @date 2020年8月24日下午2:57:19
	 */
	public void addProerties(long playerId, int configId, int added){
		final PlayerContext playerContext = getPlayerContext(playerId);
		final Player player = playerContext.getPlayer();
		PropertiesType.getType(configId).add(player, added);
		process.update(player);
	}
	
	/**
	 * 判断属性是否充足
	 * @return void  
	 * @date 2020年8月24日下午2:57:19
	 */
	public boolean checkProerties(long playerId, int configId, int added){
		final PlayerContext playerContext = getPlayerContext(playerId);
		return PropertiesType.getType(configId).check(playerContext.getPlayer(), added);
	}
	
	/////////////////////////////////////////////////////////////
	
	/**
	 * 登陆
	 * @return void  
	 * @date 2020年9月7日下午5:55:28
	 */
	public void login(GameSession session, ReqLogin req) {
		final String username = req.getUserName();
		final int initServerId = req.getServerId();
		//TODO RPC远程调用/ Http调用, 去账号服请求验证
		//boolean bool = checkLogin(username);
		PlayerContext context = getOrCreatePlayer(username, initServerId);
		if(context.isLoaded())
		{//加载新角色
			Player player = this.loadPlayer(username, initServerId);
			context.setPlayer(player);
			//加载其它模块数据
			GameEventBus.instance().post(PlayerLoadEvent.create(context.getPlayerId()));
			GameEventBus.instance().post(PlayerLoadEndEvent.create(context.getPlayerId()));
		}
		if(context.isLogined())
		{//旧角色挤掉
			//context.clearResponseCache();
			context.forceLogout();
		}
		context.setSession(session);
		context.setData();
		context.send(AckLoginResp.create().setCode(0).setStatus(0));
	}
	
	
}
