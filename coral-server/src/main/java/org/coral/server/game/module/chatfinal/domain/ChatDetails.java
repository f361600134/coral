package org.coral.server.game.module.chatfinal.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.coral.server.game.module.chatfinal.proto.PBChatInfoBuilder;
import org.coral.server.utils.TimeUtil;

/**
 * 聊天通用记录, 用于所有频道. 这个对象仅仅是每条聊天的实体信息.
 * 
 * @author Jeremy
 */
public class ChatDetails {
	/**
	 * 发送玩id
	 */
	private long senderId;
	/**
	 * 聊天内容
	 */
	private String content;
	/**
	 * 发送至id channel为 私聊,targetId为玩家id 公会,targetId为工会id
	 */
	private long targetId;
	/**
	 * 发言时间
	 */
	private long sendTime;
	/**
	 * TODO 每个玩家保存自己的聊天记录, 这个删除列表要干掉
	 * 标记玩家删除了此聊天记录
	 */
	private Set<Long> delPlayerSet;
	/**
	 * 聊天子类型 0-普通类型 1-系统消息
	 */
	private int messageType;
	/**
	 * 聊天子类型参数
	 */
	private List<String> params;
	
	public ChatDetails() {
		this.content = "";
		this.sendTime = TimeUtil.now();
		this.params = new ArrayList<>();
		this.delPlayerSet = Collections.newSetFromMap(new ConcurrentHashMap<Long, Boolean>());
	}

	private ChatDetails(long senderId, String content, long targetId) {
		this.senderId = senderId;
		this.content = content;
		this.targetId = targetId;
		this.params = new ArrayList<>();
		this.sendTime = TimeUtil.now();
		this.delPlayerSet = Collections.newSetFromMap(new ConcurrentHashMap<Long, Boolean>());
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public Set<Long> getDelPlayerSet() {
		return delPlayerSet;
	}

	public void setDelPlayerSet(Set<Long> delPlayerSet) {
		this.delPlayerSet = delPlayerSet;
	}

	public long getTargetId() {
		return targetId;
	}

	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	public void addDelPlayer(Long delPlayerId) {
		this.delPlayerSet.add(delPlayerId);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	/**
	 * 创建一条玩家聊天实体信息, 携带发送者id,用于玩家发送的聊天
	 * 
	 * @param sendId   发送者id
	 * @param content  发送内容
	 * @param channel  频道
	 * @param targetId 目标id,不同频道目标id不一致
	 * @return 聊天实体
	 */
	public static ChatDetails create(long senderId, String content, long targetId) {
		return new ChatDetails(senderId, content, targetId);
	}

	/**
	 * 创建一条聊天实体信息, 不携带发送者, 一般用于系统聊天
	 * 
	 * @param content 发送内容
	 * @param channel 频道
	 * @return 聊天实体
	 */
	public static ChatDetails create(String content) {
		return new ChatDetails(-1, content, -1);
	}

//	/**
//	 * 序列化为聊天记录
//	 * @return
//	 */
//	public ChatInfoDto toProto() {
//		ChatInfoDto chatInfoDto = new ChatInfoDto();
//		chatInfoDto.setContent(getContent());
//		chatInfoDto.setMessageType(getMessageType());
//		chatInfoDto.getParams().addAll(getParams());
//		chatInfoDto.setCreateTime((int) (getSendTime() / 1000));
//		PlayerView playerView = PlayerManager.getInstance().getPlayerCache().getPlayerView(getSenderId());
//		if (playerView == null) {
//			return chatInfoDto;
//		}
//		chatInfoDto.setPlayerDto(PlayerHelper.buildPlayerInfoProtocol(playerView));
//		chatInfoDto.setPersonDto(PersonalHelper.buildPersonDto(playerView));
//		return chatInfoDto;
//	}
	/**
	 * 聊天对象转协议对象
	 * @return ChatInfo
	 */
	public PBChatInfoBuilder toProto() {
		PBChatInfoBuilder builder = PBChatInfoBuilder.newInstance();
//		builder.setInfo(this);
		return builder;
	}

}
