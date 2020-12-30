package org.coral.server.game.module.chatplus.domain;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.coral.server.game.data.config.ConfigChatMgr;
import org.coral.server.game.data.config.pojo.ConfigChat;
import org.coral.server.game.module.base.ChatRecordPo;
import org.coral.server.game.module.chatplus.proto.AckChatResp;
import org.coral.server.utils.ConcurrentFixSizeArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
* @author Jeremy
*/
public class ChatRecord extends ChatRecordPo {
	/**
	 * chat cache base on FIFO.
	 */
	private List<ChatDetails> chatCache;
	
	public ChatRecord(int channel) {
		this.channel = channel;
		ConfigChat config = ConfigChatMgr.getConfig(channel);
		this.chatCache = new ConcurrentFixSizeArrayList<ChatDetails>(config.getCacheNum());
	}
	
	public ChatRecord(int channel, long uniqueId) {
		this.channel = channel;
		this.uniqueId = uniqueId;
		ConfigChat config = ConfigChatMgr.getConfig(channel);
		this.chatCache = new ConcurrentFixSizeArrayList<ChatDetails>(config.getCacheNum());
	}
	
	public void beforeSave() {
		this.setData(JSON.toJSONString(chatCache));
	}
	
	public void afterLoad() {
		if (!StringUtils.isBlank(getData())) {
			this.chatCache = JSONObject.parseObject(getData(), new TypeReference<ConcurrentFixSizeArrayList<ChatDetails>>() {});
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
	 * @param isCache 是否缓存, true:缓存, false, 不缓存
	 */
	public void addDelPlayer(long playerId) {
		for (ChatDetails chatDetails : chatCache) {
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
			resp.addChat(channel, chat.toProto().build());
		}
		return resp;
	}
	
	public long getUniqueId() {
		return uniqueId;
	}
	
}
