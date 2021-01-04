package org.coral.server.game.module.chatplus.impl;

import java.util.Collection;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.chatplus.domain.ChatDetails;
import org.coral.server.game.module.chatplus.domain.ChatRecord;
import org.coral.server.game.module.chatplus.proto.AckChatResp;
import org.coral.server.game.module.chatplus.proto.PBChatInfoBuilder;
import org.coral.server.game.module.player.service.IPlayerService;

public abstract class AbstractChat implements IChatChannel{
	
	/**
	 * Unique ID, According to different ids by the different domain.
	 */
	private long uniqueId;
	
	private ChatRecord record;

	public AbstractChat(int channel, long uniqueId) {
		this.uniqueId = uniqueId;
		this.record = new ChatRecord(channel, uniqueId);
	}
	
	public void init(ChatRecord record) {
		this.record = record;
		this.record.afterLoad();
	}
	
	@Override
	public int getChannel() {
		return record.getChannel();
	}
	
	public long getUniqueId() {
		return uniqueId;
	}
	
	public ChatRecord getRecord() {
		return record;
	}
	
	public ChatDetails createChat(long senderId, String content, long targetId) {
		return ChatDetails.create(senderId, content, getChannel(), targetId);
	}
	
	@Override
	public AckChatResp toAllProto() {
		return record.toAllProto();
	}
	
	@Override
	public void onBroadcast(ChatDetails chat) {
		this.record.addChat(chat);
		PBChatInfoBuilder proto = chat.toProto();
		Collection<Long> onlinePlayerIds = findPlayerIds();
		IPlayerService playerService = SpringContextHolder.getInstance().getBean(IPlayerService.class);
		playerService.sendMessage(onlinePlayerIds, proto);
	}
	
	/**
	 * 默认不支持删除聊天, 子类自己实现
	 */
	@Override
	public void delChat(long playerId) {
		//默认不支持删除记录
	}
}
