package org.coral.server.game.module.chatplus.impl;

import java.util.Collection;

import org.coral.server.game.module.chatplus.domain.ChatDetails;
import org.coral.server.game.module.chatplus.proto.AckChatResp;
import org.coral.server.game.module.player.domain.Player;

public interface IChatChannel {
	
	/**
	 * 获取[频道
     * @return
     */
	public int getChannel();
	
	/**
	 * 获取聊天域唯一id
     * @return
     */
	public long getUniqueId();
    
    /**
     * 创建一个聊天
     * @param player 发送玩家
     * @param content 发送内容
     * @param targetId 目标id, 接受者id
     * @return
     */
    public ChatDetails createChat(long senderId, String content, long targetId);
    
    /**
     */
    public void onBroadcast(ChatDetails chat);
    
    /**
     * 聊天内容序列化唯proto
     * @return 
     */
    public AckChatResp toAllProto();
    
	/**
	 * 查找所有聊天内容
	 * @return
	 */
	public Collection<Long> findPlayerIds();
	
	/**
	 * @param player
	 * @param chat
	 * @return
	 */
	default public int check(Player player, final ChatDetails chat) {
		return 0;
	}
	
	public void delChat(long playerId);
	
}
