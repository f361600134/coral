package org.coral.server.game.module.player.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IProtocol;
import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.data.proto.PBLogin.ReqLogin;
import org.coral.server.game.helper.ResourceType;
import org.coral.server.game.helper.log.NatureEnum;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.domain.PlayerContext;
import org.coral.server.game.module.player.proto.AckLoginResp;
import org.coral.server.game.module.resource.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;

@Component
public class PlayerService implements IPlayerService, IResourceService {

	private static final Logger logger = LogManager.getLogger(PlayerService.class);

	@Autowired
	private DataProcessorAsyn process;

	/**
	 * key: 玩家id value: 玩家上下文
	 */
	private Map<Long, PlayerContext> playerMap = Maps.newConcurrentMap();

	/**
	 * 玩家登陆缓存信息 key: 玩家账号 username value: PlayerContext
	 */
	private Cache<String, PlayerContext> cache = CacheBuilder.newBuilder()
			.expireAfterAccess(10, TimeUnit.MINUTES)// 在给定时间内没有被读/写访问,则清除
			.maximumSize(2 << 10)// 最大容量
			.initialCapacity(2 << 4)// 初始容量
			.build();

	/**
	 * 添加一个新的玩家上下文对象
	 * 
	 * @param context
	 * @return void
	 * @date 2020年9月8日下午6:42:11
	 */
	public void addContext(PlayerContext context) {
		this.playerMap.put(context.getPlayerId(), context);
	}

	/**
	 * 通过玩家账号获取一个玩家对象
	 * 
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
	 * 
	 * @date 2020年7月17日
	 * @param playerId
	 * @return
	 */
	private Player loadPlayer(String username, int initServerId) {
//		Object[] props = new Object[] { Player.PROP_ACCOUNTNAME, Player.PROP_INITSERVERID };
		Object[] objs = new Object[] { username, initServerId };
		List<Player> players = process.selectByIndex(Player.class,objs);
		return players.isEmpty() ? null : players.get(0);
	}

	////////////////////// 以下是业务//////////////////////////

	/**
	 * 登陆
	 * 
	 * @return void
	 * @date 2020年9月7日下午5:55:28
	 */
	public void login(GameSession session, ReqLogin req) {
		final String username = req.getUserName();
		final int initServerId = req.getServerId();
		// TODO RPC远程调用/ Http调用, 去账号服请求验证
		// boolean bool = checkLogin(username);
		PlayerContext context = getOrCreatePlayer(username, initServerId);
		if (context.isLoaded()) {// 加载新角色
			Player player = this.loadPlayer(username, initServerId);
			context.setPlayer(player);
			// 加载其它模块数据
//			GameEventBus.instance().post(PlayerLoadEvent.create(context.getPlayerId()));
//			GameEventBus.instance().post(PlayerLoadEndEvent.create(context.getPlayerId()));
		}
		if (context.isLogined()) {// 旧角色挤掉
			//context.clearResponseCache();
			context.forceLogout();
		}
		context.setSession(session);
		context.setData();
		context.send(AckLoginResp.create().setCode(0).setStatus(0));
	}

///////////////////////////以下是接口//////////////////////////////////
	
	/**
	 * 通过玩家id获取一个玩家上下文
	 * 
	 * @date 2020年7月17日
	 * @param playerId
	 * @return
	 */
	@Override
	public PlayerContext getPlayerContext(Long playerId) {
		return playerMap.get(playerId);
	}

	/**
	 * 获取所有在线玩家id
	 * 
	 * @return void
	 * @date 2020年8月24日下午2:57:19
	 */
	@Override
	public Collection<Long> getPlayerIds() {
		return playerMap.keySet();
	}

	/**
	 * 获取所有在线玩家id
	 * 
	 * @return void
	 * @date 2020年8月24日下午2:57:19
	 */
	@Override
	public Collection<PlayerContext> getPlayerContexts() {
		return playerMap.values();
	}
	
	/**
	 * 发送消息
	 * @return void
	 * @date 2020年8月24日下午2:57:19
	 */
	public void sendMessage(long playerId, IProtocol protocol) {
		final PlayerContext context = getPlayerContext(playerId);
		if (context != null) {
			context.send(protocol);
		}
	}
	
	@Override
	public void sendMessage(Collection<Long> playerIds, IProtocol protocol) {
		playerIds.forEach(playerId -> sendMessage(playerId, protocol) );
	}

	@Override
	public int resType() {
		return ResourceType.Property.getType();
	}

	@Override
	public boolean checkAdd(long playerId, Integer configId, Integer value) {
		return true;//属性默认不限制
	}

	@Override
	public boolean checkEnough(long playerId, Integer configId, Integer value) {
		final PlayerContext playerContext = getPlayerContext(playerId);
		//return PropertiesType.getType(configId).check(playerContext.getPlayer(), value);
		return playerContext.getPlayer().getProperties(configId) >= value;
	}

	@Override
	public void reward(long playerId, Integer configId, Integer value, NatureEnum nEnum) {
		final PlayerContext playerContext = getPlayerContext(playerId);
		final Player player = playerContext.getPlayer();
		player.addProperties(configId, value);
		//PropertiesType.getType(configId).add(player, value);
		process.update(player);
	}

	@Override
	public void cost(long playerId, Integer configId, Integer value, NatureEnum nEnum) {
		final PlayerContext playerContext = getPlayerContext(playerId);
		final Player player = playerContext.getPlayer();
		//PropertiesType.getType(configId).reduce(player, value);
		player.reduceProperties(configId, value);
		process.update(player);
	}

	@Override
	public void cost(long playerId, Long uniqueId, NatureEnum nEnum) {
		throw new UnsupportedOperationException("玩家属性不支持该操作");
	}
	
}
