package org.coral.server.game.module.chatplus.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.coral.net.core.executor.DisruptorStrategy;
import org.coral.net.core.executor.ExecutorSelector;
import org.coral.server.game.data.config.pojo.ConfigConstantPlus;
import org.coral.server.game.data.proto.PBPlayer.ReqChat;
import org.coral.server.game.helper.config.BadWordFilter;
import org.coral.server.game.helper.result.ConfigTipsMgr;
import org.coral.server.game.module.chatplus.assist.ChatEnum;
import org.coral.server.game.module.chatplus.domain.ChatDetails;
import org.coral.server.game.module.chatplus.domain.ChatDomainGroup;
import org.coral.server.game.module.chatplus.domain.ChatRule;
import org.coral.server.game.module.chatplus.impl.AbstractChat;
import org.coral.server.game.module.player.domain.Player;
import org.coral.server.game.module.player.domain.PlayerContext;
import org.coral.server.game.module.player.proto.AckTipsResp;
import org.coral.server.game.module.player.service.PlayerService;
import org.coral.server.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class ChatService {
	
	private static final Logger log = LoggerFactory.getLogger(ChatService.class);
	
	@Autowired private PlayerService playerService;
	
	/**
	 * key: channelId
	 * value: ChatDomainGroup聊天组
	 */
	private Map<Integer, ChatDomainGroup> domainMap = Maps.newConcurrentMap();
	
	/**
	 * key: playerId
	 * value: ChatRule聊天约束
	 */
	private Map<Long, Map<Integer, ChatRule>> playerRuleMap = Maps.newConcurrentMap();
	
		
	/**
	 * 获取聊天域, 对于家族聊天不同家族不同聊天域,domainId为家族id作为区分, 对于世界等只有一个聊天域,domainId可以设置为频道号
	 * @param channelType
	 * @param domainId
	 * @return
	 */
	public AbstractChat getOrCreateDomain(int channelType, long uniqueId) {
		ChatDomainGroup domainGroup = getOrCreateDomainGroup(channelType);
		return domainGroup.getOrCreateDomain(uniqueId);
	}
	
	/**
	 * 获取聊天域组
	 * @param channelType
	 * @param domainId
	 * @return
	 */
	public ChatDomainGroup getOrCreateDomainGroup(int channelType) {
		ChatDomainGroup domainGroup = domainMap.get(channelType);
		if (domainGroup == null) {
			domainGroup = new ChatDomainGroup(channelType);
			domainMap.put(channelType, domainGroup);
		}
		return domainGroup;
	}
	
	/**
	 * 获取玩家指定渠道的渠道规则
	 * @param playerId
	 * @param channelType
	 * @return  
	 * @return ChatRule  
	 * @date 2020年12月15日下午11:50:44
	 */
	public ChatRule getChatRule(long playerId, int channelType) {
		Map<Integer, ChatRule> chatRuleMap = playerRuleMap.get(playerId);
		if (chatRuleMap == null) {
			chatRuleMap  = Maps.newHashMap();
			playerRuleMap.put(playerId, chatRuleMap);
		}
		ChatRule rule = chatRuleMap.get(channelType);
		if (rule == null) {
			rule = ChatRule.create(channelType);
			chatRuleMap.put(channelType, rule);
		}
		return rule;
	}
	
	////////////////业务/////////////////////////
	
	/**
	 * 当玩家登出, 释放私聊缓存
	 */
	public void onLogout(long playerId) {
		try {
			log.info("onLogin playerId:{}",playerId);
//			System.out.println("onLogin playerId:{}"+playerId);
//			gc(playerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 根据聊天模板,发送至聊天
//	 * 1. 系统聊天
//	 * 2. 功能分享
//	 */
//	public void onChat(ChatEnum chatEnum, int configId, Object... args) {
//		try {
//			ConfigChatModel config = ConfigChatModelMgr.getConfig(configId);
//			if(config==null) {
//				log.info("Cannot found config for chat, configId:{}", configId);
//				return;
//			}
//			String text = config.getModelDesc();
//			String content = StringUtilitys.formatString(text, Arrays.asList(args));
//			ChatDetails chat = ChatDetails.createSystemChat(content, chatEnum.getCh());
//			chatEnum.addChat(chat);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	/**
//	 * 系统聊天
//	 */
//	public void onChat(ChatEnum chatEnum, String content) {
//		try {
//			if (content.length() > 100) {//长度限制,拒绝广播
//				return;
//			}
//			ChatDetails chat = ChatDetails.createSystemChat(content, chatEnum.getCh());
//			chatEnum.addChat(chat);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
//	/**
//	 * 支持自定义系统聊天,譬如GM内容
//	 * 1. 系统聊天
//	 * 2. 功能分享
//	 */
//	public void onChat(ChatEnum chatEnum, Long playerId, String content) {
//		try {
//			if (content.length() > 100) {//长度限制,拒绝广播
//				return;
//			}
//			ChatDetails chat = ChatDetails.create(-1, content, chatEnum.getCh(), playerId);
//			if(chatEnum==ChatEnum.CH_SYSTEM) {
//				//系统频道直接发送消息
//				AckChatResp resp = AckChatResp.newInstance();
//				resp.addChat(chatEnum.getCh(), chat.toProto());
//				playerService.sendMessage(playerId, resp);
//				return;
//			}
//			chatEnum.addChat(chat);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	/**
//	 * 当登陆成功,推送所有聊天信息
//	 */
//	public void onLogin(long playerId) {
//		try {
//			for (ChatEnum chatEnum : ChatEnum.values()) {
//				ChatDomain chatDomain = chatEnum.getDomain(playerId);
//				if (chatDomain == null) {
//					continue;
//				}
//				AckChatResp ack = chatDomain.toAllProto();
//				playerService.sendMessage(playerId, ack);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 请求聊天记录
	 * @return
	 */
	public int reqChatRecordInfo(long playerId, long targetId) {
		
		return 0;
	}
	
	/**
	 * 请求删除聊天
	 * 因为玩家和好友公用一份聊天数据, 所以
	 * @return
	 */
	public int reqDelChatecord(long playerId, int channelId, long targetId) {
		long uniqueId = ChatEnum.getEnum(channelId).getUniqueId(playerId, targetId);
		AbstractChat abstractChat = getOrCreateDomain(channelId, uniqueId);
		abstractChat.delChat(playerId);
		return 0;
	}
	
	/**
	 * 聊天业务逻辑入口
	 * 新增聊天添加到聊天域, 不实时更新, 定时推送到前端
	 */
	public int chat(ReqChat data, long playerId) {
		int channelId = data.getChatChannel();
		String content = data.getContent();
		long targetId = data.getPlayerId();
		
		Pair<Integer, String> pair = checkContent(content);
		int code = pair.getKey();
		if (code != 0) {//校验文本
			return code;
		}
		//使用过滤后的文本
		content = pair.getValue();
		PlayerContext context = playerService.getPlayerContext(playerId);
		final Player player = context.getPlayer();
		
		ChatRule rule = getChatRule(playerId, channelId);
		long curTime = System.currentTimeMillis();
		if (curTime < rule.getNextSpeakTime() && rule.isAgainst()) { //聊天过快
			//提示玩家剩余x秒后可以聊天, 不允许其聊天
			int lessTime = (int)((rule.getNextSpeakTime() - curTime)/1000);
			AckTipsResp resp = AckTipsResp.newInstance();
			resp.setTipsId(ConfigTipsMgr.Chat_417).addParams(lessTime);
			playerService.sendMessage(playerId, resp);
			return ConfigTipsMgr.Chat_411;
		}
		//提示聊天过快, 但依旧允许其聊天
		if (curTime < rule.getNextSpeakTime()) { 
			//玩家频繁违法
			rule.onTrigger();
			AckTipsResp resp = AckTipsResp.newInstance().setTipsId(ConfigTipsMgr.Chat_411);
			playerService.sendMessage(playerId, resp);
		}
		
		ChatEnum chatEnum = ChatEnum.getEnum(channelId);
		if (chatEnum == ChatEnum.CH_NONE) {
			return ConfigTipsMgr.Chat_410;	//不存在该频道
		}
		long uniqueId = chatEnum.getUniqueId(playerId, targetId);
		AbstractChat abstractChat = getOrCreateDomain(channelId, uniqueId);
		ChatDetails chatDetails = abstractChat.createChat(player.getPlayerId(), content, targetId);
		code = abstractChat.check(player, chatDetails);
		if (code != 0) {
			return code; 
		}
		abstractChat.onBroadcast(chatDetails);
		rule.onChatSuccess();
		return 0;
	}
	
	/**
	 * 聊天过滤
	 * @return 0:错误码, 1:转换后的文本
	 */
	private Pair<Integer, String> checkContent(final String content) {
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
		return Pair.of(code, str);
	}
	
}
