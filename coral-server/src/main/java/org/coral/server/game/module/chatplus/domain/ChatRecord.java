package org.coral.server.game.module.chatplus.domain;

import java.util.Iterator;
import java.util.Queue;

import org.apache.commons.lang3.StringUtils;
import org.coral.server.game.module.base.ChatRecordPo;
import org.coral.server.game.module.chatplus.proto.AckChatResp;
import org.coral.server.utils.ConcurrentFixSizeQueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * 为了保证chatCache的线程安全, 对同一个ChatRecord的操作, 只能由一条线程串行执行.
 *  所以,两种方式实现ChatRecord的线程安全
 *  1. 读写锁
 *  2. 队列
 * 
* @author Jeremy
*/
public class ChatRecord extends ChatRecordPo {
	
	private static final long serialVersionUID = 1453628263729756108L;
	/**
	 * chat cache base on FIFO.
	 */
	private Queue<ChatDetails> chatCache;
	
	public ChatRecord(int channel) {
		this.channel = channel;
		//ConfigChat config = ConfigChatMgr.getConfig(channel);
		//this.chatCache = new ConcurrentFixSizeQueue<ChatDetails>(config.getCacheNum());
		this.chatCache = new ConcurrentFixSizeQueue<ChatDetails>(20);
	}
	
	public ChatRecord(int channel, long uniqueId) {
		this.channel = channel;
		this.uniqueId = uniqueId;
		//ConfigChat config = ConfigChatMgr.getConfig(channel);
		//this.chatCache = new ConcurrentFixSizeQueue<ChatDetails>(config.getCacheNum());
		this.chatCache = new ConcurrentFixSizeQueue<ChatDetails>(20);
	}
	
	public void beforeSave() {
		this.setData(JSON.toJSONString(chatCache));
	}
	
	public void afterLoad() {
		if (!StringUtils.isBlank(getData())) {
			this.chatCache = JSONObject.parseObject(getData(), new TypeReference<ConcurrentFixSizeQueue<ChatDetails>>() {});
		}
	}
	
	/**
	 * 添加新聊天
	 * @param chat
	 * @param isCache 是否缓存, true:缓存, false, 不缓存
	 */
	public void addChat(ChatDetails chat) {
		this.chatCache.add(chat);
	}
	
	/**
	 * 玩家删除此聊天,记录
	 * @param chat
	 */
	public void addDelPlayer(long playerId) {
		Iterator<ChatDetails> iter = chatCache.iterator();
		while (iter.hasNext()) {
			ChatDetails chatDetails = (ChatDetails) iter.next();
			chatDetails.addDelPlayer(playerId);
		}
	}
	
	
	/**
	 * 缓存的聊天序列话为消息体
	 * @return
	 */
	public AckChatResp toAllProto(){
		Iterator<ChatDetails> iter = chatCache.iterator();
		AckChatResp resp = AckChatResp.newInstance();
		while (iter.hasNext()) {
			ChatDetails chat = (ChatDetails) iter.next();
			resp.addChat(channel, chat.toProto().build
					());
		}
		return resp;
	}
	
	
	public long getUniqueId() {
		return uniqueId;
	}

	@Override
	public String toString() {
		return "ChatRecord [chatCache=" + chatCache + "]";
	}
	
}
