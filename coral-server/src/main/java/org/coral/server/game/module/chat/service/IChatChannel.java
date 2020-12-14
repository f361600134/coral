package org.coral.server.game.module.chat.service;

import java.util.Collection;

import org.coral.server.game.module.chat.domain.Chat;
import org.coral.server.game.module.chat.proto.AckChatResp;
import org.coral.server.game.module.player.domain.Player;

public interface IChatChannel {
	
	/**
     * 聊天频道类型
     * @return
     */
	public int getChannel();
	
	/**
     * 获取域id
     * @return
     */
	public long getDomainId();
    
    /**
     * 添加新聊天
     * @return
     */
    public void addChat(Chat chat);
    
    /**
     * 创建聊天实体
     * @param player 发送玩家
     * @param content 聊天内容
     * @param recvId 接受玩家id
     * @return
     */
    public Chat createChat(Player player, String content, long recvId);
    
    /**
     * 是否广播
     * @return true则广播,否则不广播
     */
    public boolean isBroadcast();
    
    /**
     * 当广播消息
     * @return true则广播,否则不广播
     */
    //public boolean onBroadcast();
    
    /**
     * 缓存的聊天序列化消息体, 公共聊天会指定数量的聊天记录,仅用于玩家登陆时获取.
     * @return 
     */
    public AckChatResp toAllProto();
    
    /**
	 * 新增的聊序, 公共聊天, 有新消息时加入, 定时同步到指定玩家
	 * @return
	 */
	public AckChatResp toNewerProto();
	
	/**
	 * 查找可以接收消息的玩家列表
	 * @return
	 */
	public Collection<Long> findPlayerIds();
	
	/**
	 * 校验聊天
	 * @param player
	 * @param chat
	 * @return
	 */
	default public int check(Player player, final Chat chat) {
		return 0;
	}
	
}
