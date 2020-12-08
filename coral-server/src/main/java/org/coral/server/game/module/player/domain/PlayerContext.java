package org.coral.server.game.module.player.domain;

import java.util.Map;

import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IProtocol;
import org.coral.net.core.base.Packet;
import org.coral.server.game.module.chat.domain.ChatRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * 玩家上下文
 * @auth Jeremy
 * @date 2020年9月7日下午10:48:55
 */
public class PlayerContext {

	private static final Logger log = LoggerFactory.getLogger(PlayerContext.class);

	/** 玩家对象 */
	private Player player;
	/** 玩家会话 */
	private GameSession session;

	/**
	 * key:频道号 value: 聊天约束
	 */
	private Map<Integer, ChatRule> chatRuleMap;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameSession getSession() {
		return session;
	}

	public void setSession(GameSession session) {
		this.session = session;
	}

	public PlayerContext() {
		this.chatRuleMap = Maps.newHashMap();
	}

	public PlayerContext(Player player) {
		this.player = player;
		this.chatRuleMap = Maps.newHashMap();
	}

	public PlayerContext(Player player, GameSession session) {
		this.player = player;
		this.session = session;
		this.chatRuleMap = Maps.newHashMap();
	}

	public void send(IProtocol protocol) {
		if (isOnline()) {
			session.send(Packet.encode(protocol));
			log.info("send message:[{}], playerId:{}, size={}B, data:{}", protocol.protocol(), player.getPlayerId(), protocol, protocol.toBytes().length);
		}
	}

	public boolean isOnline() {
		return session != null && session.isConnect();
	}
	
	public boolean isLoaded() {
		return player!=null;
	}
	
	public boolean isLogined() {
		return session!=null;
	}
	
	public long getPlayerId(){
		return player == null ? 0 : player.getPlayerId();
	}
	
	/**
	 * 被挤掉
	 */
	public void forceLogout()
	{
		if(this.session!=null && this.session.isConnect()) {
			//TODO 推送客户端消息, 下线
			log.info("推送客户端消息, 下线 forceLogout");
			//AckUtility.ResponseDisconnect(56, this.channel);
		}
	}

	// public void push(IProtocol protocol) {
	// short cmd = protocol.protocol();
	// if (isOnline()) {
	// Object data = Packet.encode(protocol);
	// session.send(data);
	// int len = ((DataCarrier) data).getStructure().length;
	// GameLog.debug("推送协议[{}], pid={}, data={}, size={}B", cmd, playerId,
	// protocol, len);
	// } else {
	// if (Protocols.isMustResponse(cmd)) {
	// protoNotSent.add(protocol);
	// }
	// }
	// }

//	/**
//	 * 增加元宝
//	 * 
//	 * @param value
//	 * @param source
//	 * @return void
//	 * @date 2020年8月21日下午2:36:11
//	 */
//	public void addIngot(int value, String source) {
//		// 改变数据
//		player.addIngot(value);
//		// TODO 下发客户端金币更新
//		// 记录日志
//		PlayerLog.obtainReource(player, source, NatureEnum.Unknown.getLogType(), WealthType.Ingot.getSubType(), value, player.getIngot());
//	}

	/**
	 * 频道号
	 * @param channel
	 * @return  
	 * @return ChatRule  
	 * @date 2020年9月8日下午6:45:14
	 */
	public ChatRule getChatRule(int channel) {
		ChatRule chatRule = chatRuleMap.get(channel);
		if (chatRule == null) {
			chatRule = ChatRule.create(channel);
			chatRuleMap.put(channel, chatRule);
		}
		return chatRule;
	}
	
	public void setData() {
		
	}

}
