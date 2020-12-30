package org.coral.server.game.module.chatplus.assist;

import org.coral.server.game.module.chatplus.impl.AbstractChat;
import org.coral.server.game.module.chatplus.impl.FamilyChat;
import org.coral.server.game.module.chatplus.impl.PrivateChat;
import org.coral.server.game.module.chatplus.impl.WorldChat;

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
		public AbstractChat newInstance(long uniquId) {
			return new WorldChat(uniquId);
		}
	}, 
	CH_FAMILY(4) {// 工会聊天
		@Override
		public long getUniqueId(long playerId, long targetId) {
			//通过玩家获取到家族id
			return 0;
		}
		
		@Override
		public AbstractChat newInstance(long uniquId) {
			return new FamilyChat(uniquId);
		}
	},
	CH_PRIVATE(5) {// 私聊
		@Override
		public long getUniqueId(long playerId, long targetId) {
			return super.getUniqueId(playerId, targetId);
		}
		
		@Override
		public AbstractChat newInstance(long uniquId) {
			return new PrivateChat(uniquId);
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
	
	/**
	 * 通过枚举类生成唯一标识
	 * 默认返回频道号, 因为频道号能作为唯一标识.
	 */
	public long getUniqueId(long playerId, long targetId) {
		return getCh();
	}
	
	public AbstractChat newInstance(long uniquId) {
		return null;
	}
	
//	/**
//	 * 获取聊天域id
//	 * 有domainId则获取domainId, 无则默认为频道号id, 唯一即可
//	 * @param player 玩家对象
//	 * @param domainId domainId
//	 * @return 接受者玩家ID
//	 */
//	public long getDomainId(Player player, long domainId) {
//		return getCh();
//	}

//	public Collection<ChatDomain> getAllDomain() {
//		ChatService service = SpringContextHolder.getInstance().getBean(ChatService.class);
//		return service.getOrCreateDomainGroup(getCh()).getAllDomain();
//	}
	
//	/**
//	 * 获取聊天域
//	 * @param playerId
//	 * @return
//	 */
//	public ChatDomain getDomain(Long playerId) {
//		ChatService service = SpringContextHolder.getInstance().getBean(ChatService.class);
//		return service.getOrCreateDomain(getCh(), getCh());
//	}
	
//	/**
//	 * 判断是否可以聊天
//	 * @param player 玩家对象
//	 * @param chat 聊天内容
//	 * @return 错误码
//	 */
//	public int check(Player player, Chat chat) {
//		ConfigChat config = ConfigChatMgr.getConfig(getCh());
//		if (config == null) {
//			return ConfigTipsMgr.Chat_410;  //不存在该频道
//		}
//		if (getDomain(player.getPlayerId()) == null) {
//			return ConfigTipsMgr.Chat_410;  //不存在该频道
//		}
//		if (player.getLevel() <= config.getUnlockLevel()) {
//			return ConfigTipsMgr.Chat_414;	//等级限制
//		}
//		return 0;
//	}
	
//	public abstract Collection<Long> findPlayerIds(ChatDomain domain);
	
//	/**
//	 * 创建聊天实体
//	 * @param player 玩家对象
//	 * @param content 文本
//	 * @param recvId 接受玩家
//	 * @return 聊天对象
//	 */
//	public Chat createChat(Player player, String content, long recvId) {
//		recvId = getDomainId(player, recvId);
//		return Chat.create(player.getPlayerId(), content, getCh(), recvId);
//	}
	
//	/**
//	 * 聊天, 默认缓存, 根据需求确认是否缓存
//	 * @param chat 聊天对象
//	 */
//	public void addChat(Chat chat) {
//		ChatDomain domain = getDomain(chat.getSendId());
//		domain.addChat(chat);
//	}

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
	
}
