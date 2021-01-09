package org.coral.server.game.module.chatfinal.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.chatfinal.domain.ChatData;
import org.coral.server.game.module.chatfinal.domain.ChatDetails;
import org.coral.server.game.module.chatfinal.proto.PBChatInfoBuilder;
import org.coral.server.game.module.chatfinal.proto.ResChat;
import org.coral.server.game.module.chatfinal.service.ChatService;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.domain.PlayerContext;
import org.coral.server.game.module.player.service.IPlayerService;
import org.coral.server.utils.Pair;
import org.coral.server.utils.TimeUtil;

import com.google.common.collect.Lists;

public abstract class AbstractChat implements IChat {

	protected int channel;

	public AbstractChat(int channel) {
		this.channel = channel;
	}

	@Override
	public int getChannel() {
		return channel;
	}

	/**
	 * 聊天校验
	 * 
	 * @param senderId
	 * @return
	 */
	public ErrorCode checkChat(long senderId, BigInteger uniqueId) {
		IPlayerService service = SpringContextHolder.getInstance().getBean(IPlayerService.class);
		PlayerContext sender = service.getPlayerContext(senderId);
		if (sender == null) {
			return ErrorCode.PLAYER_NOT_EXISTS;
		}
//		ChatLimitConfig config = ConfigManager.getInstance().getConfig(ChatLimitConfig.class, getChannel());
//		if (config == null) {
//			return ErrorCode.CONFIG_NOT_EXISTS;
//		} else if (sender.getLevel() < config.getLevelLimit()) {
//			return ErrorCode.CHAT_CONDITION_LEVEL_NOT_REACH;
//		} else if (sender.getVipLevel() < config.getVipLimit()) {
//			return ErrorCode.CHAT_CONDITION_VIP_NOT_REACH;
//		}
		return ErrorCode.SUCCESS;
	}

	/**
	 * 获取原始的id
	 * 
	 * @return
	 */
	public Pair<Long, Long> getOriginalIds(BigInteger uniqueId) {
		BigInteger left = uniqueId.shiftRight(64);
		long leftPlayerId = left.longValue();
		long rightPlayerId = left.shiftLeft(64).xor(uniqueId).longValue();
		return Pair.of(leftPlayerId, rightPlayerId);
	}
	
	@Override
	public ErrorCode sendMsg(ChatDetails chatDetails) {
		if (chatDetails == null) {
			return ErrorCode.CHAT_MESSAGE_IS_EMPTY;
		}
		final long senderId = chatDetails.getSenderId();
		final long targetId = chatDetails.getTargetId();
		final BigInteger uniqueId = getUniqueId(senderId, targetId);
		ErrorCode errorCode = checkChat(senderId, uniqueId);
		if (!errorCode.isSuccess()) {
			return errorCode;
		}
		//添加聊天记录
		this.addChat(chatDetails, uniqueId);
		//序列化返回
		PBChatInfoBuilder chatBuilder = chatDetails.toProto();
		ResChat res = new ResChat();
		res.addChats(chatBuilder.build());
		res.setChatChannel(getChannel());
		Collection<Long> receiverIds = findReceiverIds(uniqueId);
		IPlayerService playerService = SpringContextHolder.getInstance().getBean(IPlayerService.class);
		playerService.sendMessage(receiverIds, res);
		return ErrorCode.SUCCESS;
	}

	/**
	 * 获取所有聊天记录
	 * 
	 * @return
	 */
	public List<ChatDetails> getAllChatRecord(Player sender, long targetId) {
		List<ChatDetails> ret = Lists.newArrayList();
		BigInteger uniqueId = getUniqueId(sender.getPlayerId(), targetId);
		ChatData chatData = getChatContainer(uniqueId);
		if (chatData == null) {
			return Lists.newArrayList();
		}
		Iterator<ChatDetails> iter = chatData.getChatRecordDeque().iterator();
		while (iter.hasNext()) {
			ret.add(iter.next());
		}
		return ret;
	}

	/**
	 * 获取最新的一条聊天记录
	 * 
	 * @param sender
	 * @param targetId
	 * @return
	 */
	public ChatDetails getLastChatRecord(Player sender, long targetId) {
		BigInteger uniqueId = getUniqueId(sender.getPlayerId(), targetId);
		ChatData chatData = getChatContainer(uniqueId);
		return chatData == null ? null : chatData.getNewChat();
	}

	/**
	 * 获取指定数量的聊天记录, 客户端主动拉取.
	 * 
	 * @param sender
	 * @param targetId
	 * @return
	 */
	public List<ChatDetails> getChatRecord(Player sender, long targetId, int num) {
		BigInteger uniqueId = getUniqueId(sender.getPlayerId(), targetId);
		ChatData chatData = getChatContainer(uniqueId);
		if (chatData == null) {
			return Lists.newArrayList();
		}
		chatData.setReadTime(TimeUtil.now());
		return chatData.getChatDataRecords(num);
	}
	
	/**
	 * 获取聊天容器
	 * @param uniqueId
	 * @return
	 */
	public ChatData getChatContainer(BigInteger uniqueId) {
		ChatService chatService = SpringContextHolder.getInstance().getBean(ChatService.class);
		ChatData chatData = chatService.getRecordData(uniqueId, getChannel());
		return chatData;
	}

	/**
	 * 添加聊天记录
	 */
	public void addChat(ChatDetails chatDetails, BigInteger uniqueId) {
		ChatData chatData = getChatContainer(uniqueId);
		chatData.addChatData(chatDetails);
	}
}
