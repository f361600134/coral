package org.coral.server.game.module.chatfinal.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.chatfinal.domain.ChatDetails;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.utils.Pair;

public interface IChat {
	
	/**
	 * 获取频道
	 * @return
	 */
	public int getChannel();

	/**
	 * 通过玩家信息,目标id生成唯一的id, 全频道通用, 每个
	 * 
	 * @param sender   发送者玩家对象
	 * @param targetId 发送目标id
	 * @return
	 */
	public BigInteger getUniqueId(long senderId, long targetId);
	
	/**
	 * 获取接收者id列表
	 * 
	 * @param sender
	 * @return
	 */
	public Collection<Long> findReceiverIds(BigInteger uniqueId);
	
	/**
	 * 删除聊天
	 */
	default public ErrorCode deleteChat(long playerId, long targetId) {
		//	默认不支持此操作,除需要外
		return ErrorCode.CONTENT_UNSUPPORT_OPERATION;
	}
	
	/**
	 * 获取原始的id
	 * @return
	 */
	public Pair<Long, Long> getOriginalIds(BigInteger uniqueId);

	/**
	 * 聊天校验
	 * 
	 * @param senderId
	 * @return
	 */
	public ErrorCode checkChat(long senderId, BigInteger uniqueId);
	
	/**
	 * 发送消息
	 * @return
	 */
	public ErrorCode sendMsg(ChatDetails chatDetails);
	
	/**
	 * 获取所有聊天记录
	 * @param sender
	 * @param targetId
	 * @return
	 */
	public List<ChatDetails> getAllChatRecord(Player sender, long targetId);
	
	/**
	 * 获取最新的一条聊天记录
	 * @param sender
	 * @param targetId
	 * @return
	 */
	public ChatDetails getLastChatRecord(Player sender, long targetId);
	
	/**
	 * 获取指定数量的聊天记录
	 * @param sender
	 * @param targetId
	 * @return
	 */
	public List<ChatDetails> getChatRecord(Player sender, long targetId, int num);
	
}
