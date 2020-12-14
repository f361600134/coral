package org.coral.server.game.module.chat.domain;

import org.coral.server.game.data.proto.PBPlayer.ChatInfo;
import org.coral.server.game.module.chat.proto.PBChatInfoBuilder;

/**
 * @description 聊天记录对象, 不存储
 * @author Jereme
 * @date 2020/8/21
 */
public class Chat {

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
	private long toId;

	public Chat() {
	}
	
	public Chat(String _content, int _channel) {
		this.sendId = -1;
		this.content = _content;
		this.channel = _channel;
		this.toId = 0;
	}

	public Chat(long _sendId, String _content, int _channel, long toId) {
		this.sendId = _sendId;
		this.content = _content;
		this.channel = _channel;
		this.toId = toId;
	}
	
	/**
	 * 创建系统聊天
	 * @param _content 文本
	 * @param _channel 频道
	 * @return Chat
	 */
	public static Chat createSystemChat(String _content, int _channel) {
		Chat pojo = new Chat(_content, _channel);
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
	public static Chat create(long _sendId, String _content, int _channel, long _recvId) {
		Chat pojo = new Chat(_sendId, _content, _channel, _recvId);
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
		return toId;
	}
	
	/**
	 * 聊天对象转协议对象
	 * @return ChatInfo
	 */
	public ChatInfo toProto() {
		PBChatInfoBuilder builder = PBChatInfoBuilder.newInstance();
		builder.setInfo(this);
		return builder.build();
	}
	
}
