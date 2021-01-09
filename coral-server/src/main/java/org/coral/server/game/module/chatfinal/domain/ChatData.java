package org.coral.server.game.module.chatfinal.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.commons.lang3.StringUtils;
import org.coral.server.game.data.config.ConfigChatMgr;
import org.coral.server.game.data.config.pojo.ConfigChat;
import org.coral.server.game.module.base.ChatDataPo;
import org.coral.server.game.module.chatfinal.impl.IChat;
import org.coral.server.utils.Pair;
import org.coral.server.utils.TimeUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
* @author Jeremy
*/
public class ChatData extends ChatDataPo {
	
	/**
	 * 唯一id, 由两个long类型数值生成
	 */
	private BigInteger uniqueId;

	/**
	 * 聊天记录列表
	 */
	private Deque<ChatDetails> chatRecordDeque;
	
	/**
	 * 缓存数量限制
	 */
	private transient int cacheLimit;
	
//	public ChatData() {
//		this.uniqueId = BigInteger.ZERO;
//		this.chatRecordDeque = new ConcurrentLinkedDeque<>();
//	}
	
	private ChatData(BigInteger uniqueId, int channel) {
		this.uniqueId = uniqueId;
		this.channel = channel;
		this.readTime = TimeUtil.now();
		this.uniqueId = uniqueId;
		this.chatRecordDeque = new ConcurrentLinkedDeque<>();
		
		ConfigChat config = ConfigChatMgr.getConfig(channel);
		this.cacheLimit = config.getCacheNum();
	}

	public BigInteger getUniqueId() {
		return uniqueId;
	}

	public Deque<ChatDetails> getChatRecordDeque() {
		return chatRecordDeque;
	}
	
	/**
	 * 新增一条记录
	 * 
	 * @param chatRecord
	 */
	public void addChatData(ChatDetails chatDetails) {
		chatRecordDeque.add(chatDetails);
		while (chatRecordDeque.size() >= cacheLimit) {
			chatRecordDeque.poll();
		}
	}
	
	/**
	 * 获取指定数量的聊天记录, 逆序
	 * 
	 * @param num
	 * @return
	 */
	public List<ChatDetails> getChatDataRecords(int num) {
		List<ChatDetails> chatRecordList = new ArrayList<>();
		if (num < 0) {
			return chatRecordList;
		}
		Iterator<ChatDetails> iterator = chatRecordDeque.descendingIterator();
		int index = 0;
		int addNum = 0;
		while (iterator.hasNext()) {
			ChatDetails chatRecord = iterator.next();
			index++;
			if (index < num) {
				continue;
			}
			chatRecordList.add(chatRecord);
			addNum++;
			if (addNum > 20) {
				break;
			}
		}
		return chatRecordList;
	}
	
	/**
	 * 获取最新的聊天记录
	 * 
	 * @return
	 */
	public ChatDetails getNewChat() {
		if (chatRecordDeque.isEmpty()) {
			return null;
		}
		return chatRecordDeque.peekLast();
	}
	
	
	public static ChatData create(BigInteger id, int channel) {
		return new ChatData(id, channel);
	}
	
	@Override
	public void beforeSave() {
		IChat chatType = ChatEnum.getEnum(getChannel()).getChatType();
		Pair<Long, Long> pair = chatType.getOriginalIds(uniqueId);
		this.leftKey = pair.getLeft();
		this.rightKey = pair.getRight();
		this.data = JSON.toJSONString(chatRecordDeque);
	}
	
	@Override
	public void afterLoad() {
		IChat chatType = ChatEnum.getEnum(getChannel()).getChatType();
		this.uniqueId = chatType.getUniqueId(this.leftKey, this.rightKey);
		if (!StringUtils.isBlank(getData())) {
			this.chatRecordDeque = JSONObject.parseObject(getData(), new TypeReference<ConcurrentLinkedDeque<ChatDetails>>() {});
		}else {
			this.chatRecordDeque = new ConcurrentLinkedDeque<>();
		}
	}
	
}
