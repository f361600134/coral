package org.coral.server.game.module.chatfinal.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.chatfinal.domain.ChatDetails;
import org.coral.server.game.module.chatfinal.domain.ChatEnum;
import org.coral.server.game.module.chatfinal.service.ChatService;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.utils.Pair;

import com.google.common.collect.Lists;

public class PrivateChat extends AbstractChat{

	public PrivateChat() {
		super(ChatEnum.CH_PRIVATE.getCh());
	}

	/**
	 * 如果双方玩家单独记录, 则根据自身的规则生成唯一的id
	 * 譬如, BigInteger.valueOf(senderId).shiftLeft(64).add(targetId)
	 * 然后根据UniqueId获取到各自的聊天容器, 加入
	 */
	@Override
	public BigInteger getUniqueId(long senderId, long targetId) {
		// 私聊,通过发送方的玩家id,和目标玩家id,组装成唯一id
		long tempId = targetId;
		if (senderId < tempId) {
			senderId ^= tempId;
			tempId ^= senderId;
			senderId ^= tempId;
		}
		BigInteger bigInteger = BigInteger.valueOf(senderId).shiftLeft(64).add(BigInteger.valueOf(tempId));
		return bigInteger;
	}
	
	@Override
	public Collection<Long> findReceiverIds(BigInteger uniqueId) {
		Pair<Long, Long> pair = getOriginalIds(uniqueId);
		return Lists.newArrayList(pair.getLeft(), pair.getRight());
	}
	
	@Override
	public ErrorCode checkChat(long senderId, BigInteger uniqueId) {
		ErrorCode errorCode = super.checkChat(senderId, uniqueId);
		if (!errorCode.isSuccess()) {
			return errorCode;
		}
		Pair<Long, Long> pair = getOriginalIds(uniqueId);
		long friendId = pair.getLeft() == senderId ? pair.getRight() : pair.getLeft();
		if (senderId == friendId) {
			return ErrorCode.CHAT_TARGET_IS_SELF;
		}
		return ErrorCode.SUCCESS;
	}
	
	@Override
	public List<ChatDetails> getAllChatRecord(Player sender, long targetId) {
		if (targetId <= 0) {//获取所有好友聊天记录时,仅获取最新的那条
//            PlayerFriendComponent friendComponent = sender.getComponent(PlayerFriendComponent.class);
//            PlayerFriendBean friendBean = friendComponent.getBean();
//            List<ChatDetails> ret = Lists.newArrayList();
//            for (FriendInfo info : friendBean.getFriendInfos().values()) {
//            	ChatDetails chatDetail = getLastChatRecord(sender, info.getPlayerId());
//                ret.add(chatDetail);
//            }
//			return ret;
			return null;
		}else {
			return super.getAllChatRecord(sender, targetId);
		}
	}
	
	@Override
	public ErrorCode deleteChat(long playerId, long targetId) {
		final BigInteger uniqueId = getUniqueId(playerId, targetId);
		ErrorCode errorCode = this.checkChat(playerId, uniqueId);
		if (!errorCode.isSuccess()) {
			return errorCode;
		}
		ChatService chatService = SpringContextHolder.getInstance().getBean(ChatService.class);
		chatService.deleteChat(uniqueId);
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 聊天添加聊天记录, 如果双方玩家单独记录, 则通过unique获取到双方玩家的聊天记录, 加入进去
	 */
	@Override
	public void addChat(ChatDetails chatDetails, BigInteger uniqueId) {
//		Pair<Long, Long> pair = getOriginalIds(uniqueId);
//		ChatData chatData = getChatContainer(pair.getLeft());
//		chatData.addChatData(chatDetails);
//		ChatData chatData = getChatContainer(pair.getRight());
//		chatData.addChatData(chatDetails);
	}
	
}
