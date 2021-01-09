package org.coral.server.game.module.chatfinal.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.coral.orm.core.DataProcessor;
import org.coral.server.game.data.config.pojo.ConfigConstantPlus;
import org.coral.server.game.data.proto.PBPlayer.ReqChat;
import org.coral.server.game.helper.config.BadWordFilter;
import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.chatfinal.domain.ChatData;
import org.coral.server.game.module.chatfinal.domain.ChatDetails;
import org.coral.server.game.module.chatfinal.domain.ChatEnum;
import org.coral.server.game.module.chatfinal.domain.ChatRule;
import org.coral.server.game.module.chatfinal.impl.IChat;
import org.coral.server.game.module.player.proto.AckTipsResp;
import org.coral.server.game.module.player.service.PlayerService;
import org.coral.server.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class ChatService {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
	
	@Autowired private PlayerService playerService;
	@Autowired private DataProcessor processor;
	
	/**
	 * key: channelId
	 * value: ChatDomainGroup聊天组
	 */
	private Cache<BigInteger, ChatData> chatRecordMap = CacheBuilder.newBuilder()
			.expireAfterAccess(1, TimeUnit.HOURS)// 在给定时间内没有被读/写访问,则清除
			.maximumSize(1000000)//	最大条目,超过这个聊天记录, 根据根绝LRU特点移除
			.removalListener((notification)->{//移除监听,因超时被移除的聊天数据,持久化到数据库
				ChatData data = (ChatData)notification.getValue();
				try {
					processor.update(data);
				} catch (Exception e) {
					logger.error("removal Listener error, bean:{}", data);
				}
			})
			.build();
	
	/**
	 * key: playerId
	 * value: ChatRule聊天约束
	 */
	private Map<Long, Map<Integer, ChatRule>> playerRuleMap = Maps.newConcurrentMap();
	
	/**
	 * 获取或创建一条记录
	 * 1. 先从本地cache获取
	 * 2.本地获取不到,从数据库获取
	 * 3.数据库获取不到创建
	 * @param id
	 * @param channel
	 * @return
	 */
	public ChatData getOrCreate(BigInteger id, int channel) {
		ChatData bean = getRecordData(id, channel);
		if (bean == null) {
			bean = ChatData.create(id, channel);
			chatRecordMap.put(bean.getUniqueId(), bean);
		}
		return bean;
	}

	/**
	 * 获取一条聊天, 不存在则从数据库加载,
	 * 
	 * @param uniqueId
	 * @param channel
	 * @return
	 */
	public ChatData getRecordData(BigInteger uniqueId, int channel) {
		ChatData bean = chatRecordMap.getIfPresent(uniqueId);
		if (bean == null) {
			IChat ChatType = ChatEnum.getEnum(channel).getChatType();
			Pair<Long, Long> pair = ChatType.getOriginalIds(uniqueId);
			bean = loadOne(pair.getLeft(), pair.getRight());
			if (bean != null) {
				this.chatRecordMap.put(uniqueId, bean);
			}
		}
		return bean;
	}

	public void deleteChat(BigInteger id) {
		chatRecordMap.invalidate(id);
	}

	/**
	 * 根绝leftKey和rightkey从数据库加载聊天.
	 * @param leftKey
	 * @param rightKey
	 * @return
	 */
	private ChatData loadOne(long leftKey, long rightKey) {
		try {
			List<ChatData> chatDataList = processor.selectByIndex(ChatData.class, new Object[] {leftKey, rightKey});
			if (chatDataList == null || chatDataList.size() <= 0) {
				return null;
			}
			return chatDataList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			logger.info("onLogin playerId:{}",playerId);
//			System.out.println("onLogin playerId:{}"+playerId);
//			gc(playerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
		IChat chatType = ChatEnum.getEnum(channelId).getChatType();
		ErrorCode errorCode = chatType.deleteChat(playerId, targetId);
		return errorCode.getCode();
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
		
		ChatRule rule = getChatRule(playerId, channelId);
		long curTime = System.currentTimeMillis();
		if (curTime < rule.getNextSpeakTime() && rule.isAgainst()) { //聊天过快
			//提示玩家剩余x秒后可以聊天, 不允许其聊天
			int lessTime = (int)((rule.getNextSpeakTime() - curTime)/1000);
			AckTipsResp resp = AckTipsResp.newInstance();
			resp.setTipsId(ErrorCode.CHAT_TIME_LIMIT);
			resp.addParams(lessTime);
			playerService.sendMessage(playerId, resp);
			return ErrorCode.CHAT_CD.getCode();
		}
		//提示聊天过快, 但依旧允许其聊天
		if (curTime < rule.getNextSpeakTime()) { 
			//玩家频繁违法
			rule.onTrigger();
			AckTipsResp resp = AckTipsResp.newInstance();
			resp.setTipsId(ErrorCode.CHAT_CD);
			playerService.sendMessage(playerId, resp);
		}
		
		ChatEnum chatEnum = ChatEnum.getEnum(channelId);
		if (chatEnum == ChatEnum.CH_NONE) {
			return ErrorCode.CHAT_CHANNEL_NOT_EXISTS.getCode();	//不存在该频道
		}
		
		IChat chatType = chatEnum.getChatType();
		if (chatType == null) {
			return ErrorCode.CHAT_CHANNEL_NOT_EXISTS.getCode();	//不存在该频道
		}
		
		ChatDetails chatDetails = ChatDetails.create(playerId, content, targetId);
		ErrorCode errorCode = chatType.sendMsg(chatDetails);
		rule.onChatSuccess();
		return errorCode.getCode();
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
			code = ErrorCode.CHAT_MESSAGE_IS_EMPTY.getCode();//聊天内容为空
		} 
		if (content.length() > 100) {//总长度大于100超过限制
			code = ErrorCode.CHAT_MESSAGE_TOO_LONG.getCode();
		}
		if (realLen > ConfigConstantPlus.chat) {//文字长度大于100超过限制
			code = ErrorCode.CHAT_MESSAGE_TOO_LONG.getCode();
		}
//		if (tags.size() > ConfigConstantPlus.expression) {//玩家只能发送表情标签, 表情大于5, 视为失败
//			code = ConfigTipsMgr.Chat_416;
//		}
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
