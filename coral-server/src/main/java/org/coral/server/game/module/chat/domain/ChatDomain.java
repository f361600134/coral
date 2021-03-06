package org.coral.server.game.module.chat.domain;


import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.coral.server.game.data.config.ConfigChatMgr;
import org.coral.server.game.data.config.pojo.ConfigChat;
import org.coral.server.game.data.proto.PBPlayer.ChatInfo;
import org.coral.server.game.module.chat.proto.AckChatResp;
import org.coral.server.utils.ConcurrentFixSizeArrayList;

/**
 * 聊天域
 * @author Jeremy
 */
public class ChatDomain {
	
	
	/**
	 * channel for chat
	 */
	private int channel;
	
	/**
	 * Unique ID, According to different ids by the different domain.
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
	
	
	public ChatDomain(int channel) {
		this.channel = channel;
		ConfigChat config = ConfigChatMgr.getConfig(channel);
		this.chatCache = new ConcurrentFixSizeArrayList<ChatInfo>(config.getCacheNum());
		this.chatQueue = new ConcurrentLinkedQueue<ChatInfo>();
		this.queueSize = new AtomicInteger(0);
	}
	
	public ChatDomain(int channel, long domainId) {
		this.channel = channel;
		this.domainId = domainId;
		ConfigChat config = ConfigChatMgr.getConfig(channel);
		this.chatCache = new ConcurrentFixSizeArrayList<ChatInfo>(config.getCacheNum());
		this.chatQueue = new ConcurrentLinkedQueue<ChatInfo>();
		this.queueSize = new AtomicInteger(0);
	}
	
	/**
	 * 添加新聊天
	 * @param chat
	 * @param isCache 是否缓存, true:缓存, false, 不缓存
	 */
	public void addChat(Chat chat, boolean isCache) {
		ChatInfo chatInfo = chat.toProto();
		this.chatQueue.add(chatInfo);
		this.queueSize.incrementAndGet();
		if (isCache) {
			this.chatCache.add(chatInfo);
		}
	}
	
	/**
	 * return true if the queueSize greater than specified quantity 
	 * or the current time greater than the last broadcast time.
	 * @author Jereme 
	 */
	public boolean isBroadcast() {
		ConfigChat config = ConfigChatMgr.getConfig(channel);
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
			resp.addChat(channel, chat);
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
		AckChatResp resp = AckChatResp.newInstance();
		while(chat != null) {
			resp.addChat(channel, chat);
			chat = chatQueue.poll();
		}
		return resp;
	}
	
	public long getDomainId() {
		return domainId;
	}
	
}
