package org.coral.server.game.module.chatfinal.domain;

import java.util.HashMap;
import java.util.Map;

import org.coral.server.game.module.chatfinal.impl.FamilyChat;
import org.coral.server.game.module.chatfinal.impl.IChat;
import org.coral.server.game.module.chatfinal.impl.PrivateChat;
import org.coral.server.game.module.chatfinal.impl.WorldChat;

/**
 * 聊天类枚举,不同频道,要区别处理
 * 当前聊天转发给所有在线玩家, 缓存的聊天上线获取所有
 * @auth Jeremy
 * @date 2019年5月10日下午2:04:37
 */
public enum ChatEnum {
	
	CH_NONE(0){//不存在的频道
	},
	CH_PROVINCE(1){ // 同省聊天
	},
	CH_SERVER(2){// 跨服聊天
	}, 
	CH_WORLD(3){// 世界聊天
		@Override
		public IChat newInstance() {
			return new WorldChat();
		}
	}, 
	CH_FAMILY(4) {// 工会聊天
		@Override
		public IChat newInstance() {
			return new FamilyChat();
		}
	},
	CH_PRIVATE(5) {// 私聊
		@Override
		public IChat newInstance() {
			return new PrivateChat();
		}
	},
	CH_SYSTEM(6) {// 系统频道
		
	};
	private int ch; // 频道

	/**
	 * 私有枚举构造器
	 * @param ch 频道
	 */
	private ChatEnum(int ch) {
		this.ch = ch;
	}

	public int getCh() {
		return ch;
	}
	
	public IChat newInstance() {
		return null;
	}
	
	public IChat getChatType() {
		return channelTypeMap.get(getCh());
	}
	
	/**
	 * 获取聊天枚举
	 * @param ch 频道
	 * @return 聊天枚举
	 */
	public static ChatEnum getEnum(int ch) {
		for (ChatEnum chatEnum : values()) {
			if (ch == chatEnum.getCh()) {
				return chatEnum;
			}
		}
		return null;
	}
	
	public static Map<Integer, IChat> channelTypeMap = new HashMap<>();
	static {
		for (ChatEnum chatEnum : ChatEnum.values()) {
			channelTypeMap.put(chatEnum.getCh(), chatEnum.newInstance());
		}
	}
	
}
