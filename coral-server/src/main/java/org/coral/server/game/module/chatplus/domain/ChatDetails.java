package org.coral.server.game.module.chatplus.domain;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.coral.server.game.module.chatplus.proto.PBChatInfoBuilder;

import com.google.common.collect.Sets;

/**
 * @description 聊天记录对象
 * @author Jeremy
 * @date 2020/8/21
 */
public class ChatDetails {

	/** 发送玩id*/
	private long sendId;
	/** 内容*/
	private String content;
	/** 渠道*/
	private int channel;
	
	/**
	 * 发送至id
	 * channel为
	 * 私聊,toId为玩家id
	 * 公会,toId为工会id
	 * 世界,跨服,系统toId为0
	 * 同省,toId为省id
	 */
	private long targetId;
	
	/**
	 * 标记玩家删除了此聊天记录
	 */
	private Set<Long> delPlayerSet;

	public ChatDetails() {
	}
	
	public ChatDetails(String _content, int _channel) {
		this.sendId = -1;
		this.content = _content;
		this.channel = _channel;
		this.targetId = 0;
		this.delPlayerSet = Sets.newConcurrentHashSet();
	}

	public ChatDetails(long _sendId, String _content, int _channel, long toId) {
		this.sendId = _sendId;
		this.content = _content;
		this.channel = _channel;
		this.targetId = toId;
		this.delPlayerSet = Sets.newConcurrentHashSet();
	}
	
	/**
	 * 创建系统聊天
	 * @param _content 文本
	 * @param _channel 频道
	 * @return Chat
	 */
	public static ChatDetails createSystemChat(String _content, int _channel) {
		ChatDetails pojo = new ChatDetails(_content, _channel);
		return pojo;
	}


	/**
	 * 创建普通聊天
	 * @param _sendId 发送者id
	 * @param _content 文本
	 * @param _channel 频道
	 * @param _recvId 接收者id
	 * @return Chat
	 */
	public static ChatDetails create(long _sendId, String _content, int _channel, long _recvId) {
		ChatDetails pojo = new ChatDetails(_sendId, _content, _channel, _recvId);
		return pojo;
	}

	public long getSendId() {
		return sendId;
	}

	public String getContent() {
		return content;
	}

	public int getChannel() {
		return channel;
	}

	public void setSendId(int sendId) {
		this.sendId = sendId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
	
	public long getToId() {
		return targetId;
	}
	
	/**
	 * 添加删除玩家
	 * @param playerId
	 */
	public void addDelPlayer(long playerId) {
		this.delPlayerSet.add(playerId);
	}
	
	/**
	 * 聊天对象转协议对象
	 * @return ChatInfo
	 */
	public PBChatInfoBuilder toProto() {
		PBChatInfoBuilder builder = PBChatInfoBuilder.newInstance();
		builder.setInfo(this);
		return builder;
	}

	@Override
	public String toString() {
		return "ChatDetails [sendId=" + sendId + ", content=" + content + ", channel=" + channel + ", targetId="
				+ targetId + ", delPlayerSet=" + delPlayerSet + "]";
	}
	
}
