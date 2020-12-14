package org.coral.server.game.module.chat.assist;

import java.util.Collection;
import java.util.List;

import org.coral.server.game.data.config.ConfigChatMgr;
import org.coral.server.game.data.config.pojo.ConfigChat;
import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.helper.result.ConfigTipsMgr;
import org.coral.server.game.module.chat.domain.Chat;
import org.coral.server.game.module.chat.domain.ChatDomain;
import org.coral.server.game.module.chat.service.ChatServicePlus;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.domain.PlayerContext;
import org.coral.server.game.module.player.service.PlayerService;

import com.google.common.collect.Lists;

/**
 * 聊天类枚举,不同频道,要区别处理
 * 当前聊天转发给所有在线玩家, 缓存的聊天上线获取所有
 * @auth Jeremy
 * @date 2019年5月10日下午2:04:37
 */
public enum ChatEnum {
	
	CH_PROVINCE(1){ // 同省聊天
		@Override
		public Collection<Long> findPlayerId(ChatDomain domain) {
			return null;
		}

		@Override
		public long getRecId(Player player, long domainId) {
			//获取玩家的省标识
			return 0L;
		}
		
		@Override
		public ChatDomain getDomain(Long playerId) {
			//获取玩家所在省id
//			return Context.getChatServicePlus().getProvinceGroup();
			return null;
		}
		
		@Override
		public int check(Player player, Chat chat) {
//			ConfigChatMgr.getConfig(getCh());
			return 0;
		}

		@Override
		public Collection<ChatDomain> getAllDomain() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	},
	CH_SERVER(2){// 跨服聊天
		@Override
		public List<Long> findPlayerId(ChatDomain domain) {
//			IPlayerService playerService = SpringContextHolder.getInstance().getBean(IPlayerService.class);
//			return playerService.getAllPlayer();
			return null;
		}

		@Override
		public long getRecId(Player player, long domainId) {
			//获取玩家的省标识
			return 0L;
		}
		
		@Override
		public ChatDomain getDomain(Long playerId) {
			ChatServicePlus service = SpringContextHolder.getInstance().getBean(ChatServicePlus.class);
			return service.getServerGroup();
		}
		
		@Override
		public Collection<ChatDomain> getAllDomain() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}, 
	CH_WORLD(3){// 世界聊天
		@Override
		public Collection<Long> findPlayerId(ChatDomain domain) {
			PlayerService service = SpringContextHolder.getInstance().getBean(PlayerService.class);
			return service.getPlayerIds();
		}

		@Override
		public long getRecId(Player player, long domainId) {
			return 0L;
		}
		
		@Override
		public ChatDomain getDomain(Long playerId) {
			ChatServicePlus service = SpringContextHolder.getInstance().getBean(ChatServicePlus.class);
			return service.getWorldGroup();
		}
		
		@Override
		public Collection<ChatDomain> getAllDomain() {
			ChatServicePlus service = SpringContextHolder.getInstance().getBean(ChatServicePlus.class);
			return Lists.newArrayList(service.getWorldGroup());
		}
		
	}, 
	CH_FAMILY(4) {// 工会聊天

		@Override
		public long getRecId(Player player, long domainId) {
			//获取玩家的工会id
			return 0L;
		}
		
		@Override
		public ChatDomain getDomain(Long playerId) {
			//获取工会id
//			return Context.getChatServicePlus().getFamilyGroup();
			return null;
		}
		
		@Override
		public int check(Player player, Chat chat) {
			int code =  super.check(player, chat);
			if (code != 0) {
				return code;
			}
			//获取公会
			return code;
		}
		
		@Override
		public Collection<ChatDomain> getAllDomain() {
			// TODO Auto-generated method stub
			return null;
		}
		
	},
	CH_PRIVATE(5) {// 私聊
		@Override
		public Collection<Long> findPlayerId(ChatDomain domain) {
			return Lists.newArrayList(domain.getDomainId());
		}

		@Override
		public ChatDomain getDomain(Long playerId) {
			ChatServicePlus service = SpringContextHolder.getInstance().getBean(ChatServicePlus.class);
			return service.getPrivateChat(playerId);
		}

		@Override
		public long getRecId(Player player, long domainId) {
			return domainId;
		}
		
		@Override
		public int check(Player player, Chat chat) {
			int code =  super.check(player, chat);
			if (code != 0) {
				return code;
			}
			if (chat.getToId() == 0) {
				return ConfigTipsMgr.Chat_418;//找不到聊天对象
			}
			if (chat.getToId() == player.getPlayerId()) {
				return ConfigTipsMgr.Chat_413;//不能自言自语
			}
			return code;
		}
		
		@Override
		public Collection<ChatDomain> getAllDomain() {
			ChatServicePlus service = SpringContextHolder.getInstance().getBean(ChatServicePlus.class);
			return service.getAllPrivateChat();
		}
		
		/**
		 * 聊天
		 */
		public void addChat(Chat chat) {
			//发送者玩家
			ChatDomain sendDomain = getDomain(chat.getSendId());
			sendDomain.addChat(chat, false);
			/*
			 * 接收者玩家
			 * 接受者在线, 不缓存. 不在线则缓存.
			 */
//			Player player = Context.getPlayerService().getPlayer(chat.getToId());
			PlayerService service = SpringContextHolder.getInstance().getBean(PlayerService.class);
			PlayerContext context = service.getPlayerContext(chat.getToId());
			ChatDomain recDomain = getDomain(chat.getToId());
			if (context.isOnline()) {
				recDomain.addChat(chat, false);
			}else {
				recDomain.addChat(chat, true);
			}
			
			
		}
	},
	CH_SYSTEM(6) {// 系统频道
		/**
		 * 系统频道同步聊天内容到世界
		 */
		@Override
		public Collection<Long> findPlayerId(ChatDomain domain) {
			PlayerService playerService = SpringContextHolder.getInstance().getBean(PlayerService.class);
			return  playerService.getPlayerIds();
		}
		
		@Override
		public long getRecId(Player player, long domainId) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Collection<ChatDomain> getAllDomain() {
			return null;
		}

		@Override
		public ChatDomain getDomain(Long playerId) {
			ChatServicePlus service = SpringContextHolder.getInstance().getBean(ChatServicePlus.class);
			return service.getSystmGroup();
		}
		
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
	 * 获取接收id, 玩家id, 省id, 公会id等
	 * @param player 玩家对象
	 * @param domainId domainId
	 * @return 接受者玩家ID
	 */
	public abstract long getRecId(Player player, long domainId);

	public abstract Collection<ChatDomain> getAllDomain();
	
	public abstract ChatDomain getDomain(Long playerId);
	
	/**
	 * 判断是否可以聊天
	 * @param player 玩家对象
	 * @param chat 聊天内容
	 * @return 错误码
	 */
	public int check(Player player, Chat chat) {
		ConfigChat config = ConfigChatMgr.getConfig(getCh());
		if (config == null) {
			return ConfigTipsMgr.Chat_410;  //不存在该频道
		}
		if (getDomain(player.getPlayerId()) == null) {
			return ConfigTipsMgr.Chat_410;  //不存在该频道
		}
		if (player.getLevel() <= config.getUnlockLevel()) {
			return ConfigTipsMgr.Chat_414;	//等级限制
		}
		return 0;
	}
	
	public Collection<Long> findPlayerId(ChatDomain domain) {
		return null;
	}
	
	/**
	 * 创建聊天实体
	 * @param player 玩家对象
	 * @param content 文本
	 * @param recvId 接受玩家
	 * @return 聊天对象
	 */
	public Chat createChat(Player player, String content, long recvId) {
		recvId = getRecId(player, recvId);
		return Chat.create(player.getPlayerId(), content, getCh(), recvId);
	}
	
	/**
	 * 聊天, 默认缓存, 根据需求确认是否缓存
	 * @param chat 聊天对象
	 */
	public void addChat(Chat chat) {
		ChatDomain domain = getDomain(chat.getSendId());
		domain.addChat(chat, true);
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
	
}
