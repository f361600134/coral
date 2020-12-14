package org.coral.server.game.module.chat.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.coral.server.game.data.config.ConfigChatMgr;
import org.coral.server.game.data.config.pojo.ConfigChat;
import org.coral.server.game.data.proto.PBPlayer.ChatInfo;
import org.coral.server.game.module.chat.domain.Chat;
import org.coral.server.game.module.chat.proto.AckChatResp;
import org.coral.server.game.module.chat.service.IChatChannel;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.utils.ConcurrentFixSizeArrayList;

public abstract class AbstractChat implements IChatChannel{
	
	/**
	 * Unique ID, According to different ids by the different domain.
	 * For the private chat, the domainId is id of the player.
	 * For the family chat, the domainId is id of the family.
	 * For the chat that just has 1 chat domain, the domainId is ChannelId.
	 * ...
	 */
	private long domainId;
	
	/**
	 * chat cache base on FIFO.
	 */
	private List<ChatInfo> chatCache;
	/**
	 * The newer add this queue.
	 */
	private Queue<ChatInfo> chatQueue;
	
	/**
	 * Last broadcast time, it used to record broadcast time.
	 */
	private int lastBroadcastTime;
	
	/**
	 * the size of the queue, it used for quantity limit.
	 */
	private AtomicInteger queueSize;

	/**
	 * @param domainId:also called Unique ID
	 */
	public AbstractChat(long domainId) {
		this.domainId = domainId;
		ConfigChat config = ConfigChatMgr.getConfig(getChannel());
		this.chatCache = new ConcurrentFixSizeArrayList<ChatInfo>(config.getCacheNum());
		this.chatQueue = new ConcurrentLinkedQueue<ChatInfo>();
		this.queueSize = new AtomicInteger(0);
	}
	
	@Override
	public long getDomainId() {
		return domainId;
	}
	/**
	 * 创建聊天实体
	 * @param player 玩家对象
	 * @param content 文本
	 * @param recvId 接受玩家
	 * @return 聊天对象
	 */
	public Chat createChat(Player player, String content, long recvId) {
		recvId = getRecId(player, recvId);
		return Chat.create(player.getPlayerId(), content, getChannel(), recvId);
	}
	
	/**
	 * 添加新聊天
	 * @param chat
	 * @param isCache 是否缓存, true:缓存, false, 不缓存
	 */
	public void addChat(Chat chat) {
		ChatInfo chatInfo = chat.toProto();
		this.chatQueue.add(chatInfo);
		this.queueSize.incrementAndGet();
		this.chatCache.add(chatInfo);
	}
	
	/**
	 * return true if the queueSize greater than specified quantity 
	 * or the current time greater than the last broadcast time.
	 * @author Jereme 
	 */
	public boolean isBroadcast() {
		ConfigChat config = ConfigChatMgr.getConfig(getChannel());
		long nextTime = lastBroadcastTime + config.getBroadcastIntervalTime() * 1000;
		return System.currentTimeMillis() > nextTime || queueSize.get() >  config.getDefaultBroadcastNum();
	}
	
	/**
	 * 缓存的聊天序列话为消息体
	 * @return
	 */
	public AckChatResp toAllProto(){
		Iterator<ChatInfo> iter = chatCache.iterator();
		AckChatResp resp = AckChatResp.newInstance();
		while (iter.hasNext()) {
			ChatInfo chat = (ChatInfo) iter.next();
			resp.addChat(getChannel(), chat);
		}
		return resp;
	}
	
	/**
	 * 新增的聊序列话消息体
	 * @return
	 */
	public AckChatResp toNewerProto(){
		ChatInfo chat = chatQueue.poll();
		if (chat == null) {
			return null;
		}
		queueSize.decrementAndGet();
		AckChatResp resp = AckChatResp.newInstance();
		while(chat != null) {
			resp.addChat(getChannel(), chat);
			chat = chatQueue.poll();
			queueSize.decrementAndGet();
		}
		return resp;
	}

	/**
	 * 获取接收id， 用于获取指定聊天对象域, 如果指定接收对象域, 则返回对象域,否则返回默认对象域
	 * @param player 玩家对象
	 * @param domainId domainId
	 * @return 接受者玩家id
	 */
	public long getRecId(Player player, long recvId) {
		return getDomainId();
	}
}
