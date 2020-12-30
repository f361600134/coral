package org.coral.server.game.module.chatplus.impl;

import java.util.Collection;

import org.coral.net.core.executor.DisruptorStrategy;
import org.coral.server.game.module.chatplus.assist.ChatEnum;

import com.google.common.collect.Lists;

/**
 * 私聊
 * 私聊信息因为存储需求,所以发送方和接收方只保存一份记录.
 * 所以聊天记录的key,由发送方,接收方一并生成
 * @author Jeremy
 */
public class PrivateChat extends AbstractChat{
	
	/**
	 * 私聊, uniqueId为玩家id
	 * @param uniqueId
	 */
	public PrivateChat(long uniqueId) {
		super(ChatEnum.CH_PRIVATE.getCh(), uniqueId);
	}
	
	/**
	 * 获取同步玩家id
	 */
	@Override
	public Collection<Long> findPlayerIds() {
		//通过uniqueId生成发送玩家id以及目标玩家id
		//getUniqueId()
		return Lists.newArrayList();
	}
	
	/**
	 * 删除行为是一个并发修改的过程, 此处加入公共线程内,所有玩家串行执行
	 */
	@Override
	public void delChat(long playerId) {
		DisruptorStrategy.get(DisruptorStrategy.COMMON).execute(() -> {return 0;}, () -> {
			getRecord().addDelPlayer(playerId);
		});
	}
	
}
