package org.coral.server.game.module.chat.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.coral.server.game.data.config.ConfigChatModelMgr;
import org.coral.server.game.data.config.pojo.ConfigChatModel;
import org.coral.server.game.data.config.pojo.ConfigConstantPlus;
import org.coral.server.game.data.proto.PBPlayer.ReqChat;
import org.coral.server.game.helper.config.BadWordFilter;
import org.coral.server.game.helper.result.ConfigTipsMgr;
import org.coral.server.game.module.chat.assist.ChatEnum;
import org.coral.server.game.module.chat.domain.Chat;
import org.coral.server.game.module.chat.domain.ChatDomain;
import org.coral.server.game.module.chat.domain.ChatRule;
import org.coral.server.game.module.chat.proto.AckChatResp;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.domain.PlayerContext;
import org.coral.server.game.module.player.helper.PlayerHelper;
import org.coral.server.game.module.player.proto.AckTipsResp;
import org.coral.server.game.module.player.service.PlayerService;
import org.coral.server.utils.StringUtilitys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class ChatServicePlus {
	
	private static final Logger log = LoggerFactory.getLogger(ChatServicePlus.class);
	
	@Autowired private PlayerService playerService;

	/**
	 * key:省id
	 * value:聊天组
	 */
	private static Map<Long, ChatDomain> provinceGroup = Maps.newConcurrentMap();
	// 跨服频道
	private static ChatDomain serverGroup = new ChatDomain(ChatEnum.CH_WORLD.getCh());
	// 世界频道
	private static ChatDomain worldGroup = new ChatDomain(ChatEnum.CH_WORLD.getCh());
	/**
	 * key:工会id
	 * value: 聊天组 
	 */
	private static Map<Long, ChatDomain> familyGroup = Maps.newConcurrentMap();
	/**
	 * key:玩家id
	 * value: 聊天组
	 */
	private static Map<Long, ChatDomain> privateChat = Maps.newConcurrentMap();
	
	// 系统频道,即时发送不缓存
	private static ChatDomain systmGroup = new ChatDomain(ChatEnum.CH_SYSTEM.getCh());
	
	/**
	 * 获得同省聊天
	 * @param provinceId>0
	 * @return
	 */
	public ChatDomain getProvinceGroup(Long provinceId) {
		if (provinceId <= 0) {
			return null;
		}
		ChatDomain group = provinceGroup.get(provinceId);
		if (group == null) {
			group = new ChatDomain(ChatEnum.CH_PROVINCE.getCh(), provinceId );
			provinceGroup.put(provinceId, group);
		}
		return group;
	}
	 
	/**
	 * 获得跨服聊天
	 * @return
	 */
	public ChatDomain getServerGroup() {
		return serverGroup;
	}
	
	/**
	 * 获得世界聊天
	 * @return
	 */
	public ChatDomain getWorldGroup() {
		return worldGroup;
	}
	
	/**
	 * 获得工会聊天
	 * @param familyId > 0
	 */
	public ChatDomain getFamilyGroup(Long familyId) {
		if (familyId <= 0) {
			return null;
		}
		ChatDomain group = familyGroup.get(familyId);
		if (group == null) {
			group = new ChatDomain(ChatEnum.CH_FAMILY.getCh(), familyId);
			familyGroup.put(familyId, group);
		}
		return group;
	}
	
	/**
	 * 获得私聊
	 * @return
	 */
	public ChatDomain getPrivateChat(Long playerId) {
		if (playerId <= 0) {
			return null;
		}
		ChatDomain group = privateChat.get(playerId);
		if (group == null) {
			group = new ChatDomain(ChatEnum.CH_PRIVATE.getCh(), playerId);
			privateChat.put(playerId, group);
		}
		return group;
	}
	
	/**
	 * 获取所有私聊
	 * @return
	 */
	public Collection<ChatDomain> getAllPrivateChat(){
		return privateChat.values();
	}
	
	/**
	 * 获得系统频道
	 * @return
	 */
	public ChatDomain getSystmGroup() {
		return systmGroup;
	}
	
	
//	@Override
//	public void gc(long playerId) {
////		playerChatMap.remove(playerId);
//		privateChat.remove(playerId);
//	}
	
	////////////////业务/////////////////////////
	
	/**
	 * 当登陆成功,推送所有聊天信息
	 */
	public void onLogin(long playerId) {
		try {
			for (ChatEnum chatEnum : ChatEnum.values()) {
				ChatDomain chatDomain = chatEnum.getDomain(playerId);
				if (chatDomain == null) {
					continue;
				}
				AckChatResp ack = chatDomain.toAllProto();
				PlayerHelper.sendMessage(playerId, ack);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 当玩家登出, 释放私聊缓存
	 */
	public void onLogout(long playerId) {
		try {
			log.info("onLogin playerId:{}"+playerId);
//			System.out.println("onLogin playerId:{}"+playerId);
//			gc(playerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  1秒钟事, 聊天2秒同步广播1次
	 */
	public void onSecond() {
		try {
			for (ChatEnum chatEnum : ChatEnum.values()) {
				Collection<ChatDomain> domains = chatEnum.getAllDomain();
				if (domains == null) {
					continue;
				}
				for (ChatDomain chatDomain : domains) {
					if (chatDomain == null || !chatDomain.isBroadcast()) {
						continue;
					}
					AckChatResp resp = chatDomain.toNewerProto();
					if (resp == null) {
						continue;
					}
					Collection<Long> playerIds = chatEnum.findPlayerId(chatDomain);
					if(!playerIds.isEmpty()) {
						PlayerHelper.sendMessage(playerIds, resp);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据聊天模板,发送至聊天
	 * 1. 系统聊天
	 * 2. 功能分享
	 */
	public void onChat(ChatEnum chatEnum, int configId, Object... args) {
		try {
			ConfigChatModel config = ConfigChatModelMgr.getConfig(configId);
			if(config==null) {
				log.info("Cannot found config for chat, configId:{}", configId);
				return;
			}
			String text = config.getModelDesc();
//			List<Object> textArgs = Lists.newArrayList();
//			for(Object arg : args)
//				textArgs.add(arg);
			String content = StringUtilitys.formatString(text, Arrays.asList(args));
			Chat chat = Chat.createSystemChat(content, chatEnum.getCh());
			chatEnum.addChat(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 支持自定义系统聊天,譬如GM内容
	 * 1. 系统聊天
	 * 2. 功能分享
	 */
	public void onChat(ChatEnum chatEnum, String content) {
		try {
			if (content.length() > 100) {//长度限制,拒绝广播
				return;
			}
			Chat chat = Chat.createSystemChat(content, chatEnum.getCh());
			chatEnum.addChat(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 支持自定义系统聊天,譬如GM内容
	 * 1. 系统聊天
	 * 2. 功能分享
	 */
	public void onChat(ChatEnum chatEnum, Long playerId, String content) {
		try {
			if (content.length() > 100) {//长度限制,拒绝广播
				return;
			}
			Chat chat = Chat.create(-1, content, chatEnum.getCh(), playerId);
			if(chatEnum==ChatEnum.CH_SYSTEM) {
				//系统频道直接发送消息
				AckChatResp resp = AckChatResp.newInstance();
				resp.addChat(chatEnum.getCh(), chat.toProto());
//				SendMessageUtil.sendResponse(playerId, resp);
				PlayerHelper.sendMessage(playerId, resp);
				return;
			}
			chatEnum.addChat(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 聊天业务逻辑入口
	 * 新增聊天添加到聊天域, 不实时更新, 定时推送到前端
	 */
	public int chat(ReqChat data, long playerId) {
		int channelId = data.getChatChannel();
		String content = data.getContent();
		long recvId = data.getPlayerId();
		
		List<Object> retList = checkContent(content);
		int code = (int)retList.get(0);
		if (code != 0) {//校验文本
			return code;
		}
		//使用过滤后的文本
		content = retList.get(1).toString();

		PlayerContext context = playerService.getPlayerContext(playerId);
		final Player player = context.getPlayer();
		ChatRule rule = context.getChatRule(channelId);
		long curTime = System.currentTimeMillis();
		if (curTime < rule.getNextSpeakTime() && rule.isAgainst()) { //聊天过快
			//提示玩家剩余x秒后可以聊天, 不允许其聊天
			int lessTime = (int)((rule.getNextSpeakTime() - curTime)/1000);
			AckTipsResp resp = AckTipsResp.newInstance();
			resp.setTipsId(ConfigTipsMgr.Chat_417).addParams(lessTime);
//			SendMessageUtil.sendResponse(playerId, resp);
			PlayerHelper.sendMessage(playerId, resp);
			return ConfigTipsMgr.Chat_411;
		}
		//提示聊天过快, 但依旧允许其聊天
		if (curTime < rule.getNextSpeakTime()) { 
			//玩家频繁违法
			rule.onTrigger();
			AckTipsResp resp = AckTipsResp.newInstance().setTipsId(ConfigTipsMgr.Chat_411);
//			SendMessageUtil.sendResponse(playerId, resp);
			PlayerHelper.sendMessage(playerId, resp);
		}
		
		ChatEnum chatEnum = ChatEnum.getEnum(channelId);
		if (chatEnum == null) {
			return ConfigTipsMgr.Chat_410;	//不存在该频道
		}
		Chat chat = chatEnum.createChat(player, content, recvId);
		code = chatEnum.check(player, chat);
		if (code != 0) {
			return code; 
		}
		chatEnum.addChat(chat);
		rule.onChatSuccess();
		return 0;
	}
	
	/**
	 * 聊天过滤
	 * @return 0:错误码, 1:转换后的文本
	 */
	private List<Object> checkContent(final String content) {
		
		String str = content;
		List<String> tags = Lists.newArrayList();
		//对文字进行分解,玩家输入的文字只能包含图片
		int firstindex = str.indexOf("<");
		int lastindex = str.indexOf(">", firstindex);
		while (firstindex > 0 &&  lastindex > 0) {
			
			String tag = str.substring(firstindex , lastindex+1);
			str = str.replaceFirst(tag, "?");
			tags.add(tag);
			
			firstindex = str.indexOf("<");
			lastindex = str.indexOf(">", firstindex);
		}
		int realLen = str.length() - tags.size();
		
		int code = 0;
		if (StringUtils.isBlank(str)) {
			code = ConfigTipsMgr.Chat_419;//聊天内容为空
		}
		if (content.length() > 100) {//总长度大于100超过限制
			code = ConfigTipsMgr.Chat_415;
		}
		if (realLen > ConfigConstantPlus.chat) {//文字长度大于100超过限制
			code = ConfigTipsMgr.Chat_415;
		}
		if (tags.size() > ConfigConstantPlus.expression) {//玩家只能发送表情标签, 表情大于5, 视为失败
			code = ConfigTipsMgr.Chat_416;
		}
		//文本过滤
		str = BadWordFilter.doFilter(str);
		//还原文本
		for (String tag : tags) {
			str = str.replaceFirst("\\?", tag);
		}
		//最终返回
		List<Object> retList = new ArrayList<>();
		retList.add(code);
		retList.add(str);
		
		return retList;
	}
	
}
