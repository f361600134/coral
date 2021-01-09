package org.coral.server.game.module.chatfinal.service;

import org.coral.server.game.module.chatplus.assist.ChatEnum;

/**
 * 聊天对外接口
 * @author Jeremy
 *
 */
public interface IChatService {
	
	/**
	 * 根据聊天模板,发送至聊天
	 * 1. 系统聊天
	 * 2. 功能分享
	 */
	public void onChat(ChatEnum chatEnum, int configId, Object... args);
	
	/**
	 *  聊天调用, 用于跨服聊天同步聊天内容
	 * @param chatEnum
	 * @param content
	 */
	public void onChat(ChatEnum chatEnum, String content) ;
	
	/**
	 * 聊天调用, 用于跨服聊天同步聊天内容
	 * @param chatEnum
	 * @param playerId
	 * @param content
	 */
	public void onChat(ChatEnum chatEnum, Long playerId, String content);

}
