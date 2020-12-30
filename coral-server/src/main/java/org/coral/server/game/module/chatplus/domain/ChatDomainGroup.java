package org.coral.server.game.module.chatplus.domain;

import java.util.Collection;
import java.util.Map;

import org.coral.orm.core.DataProcessor;
import org.coral.server.game.helper.context.SpringContextHolder;
import org.coral.server.game.module.chatplus.assist.ChatEnum;
import org.coral.server.game.module.chatplus.impl.AbstractChat;

import com.google.common.collect.Maps;

/**
 * 聊天域分组, 按照聊天频道区分, 有些为单频道,有些为多频道
 * @author Administrator
 *
 */
public class ChatDomainGroup {
	
	private int channelType;
	
	private Map<Long, AbstractChat> chatDomainMap;
	
	public ChatDomainGroup(int channelType) {
		this.channelType = channelType;
		this.chatDomainMap = Maps.newConcurrentMap();
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public Map<Long, AbstractChat> getChatDomainMap() {
		return chatDomainMap;
	}

	public void setChatDomainMap(Map<Long, AbstractChat> chatDomainMap) {
		this.chatDomainMap = chatDomainMap;
	}
	
	/**
	 * 获取或创建domain
	 * @param domainId
	 * @return
	 */
	public AbstractChat getOrCreateDomain(long uniqueId) {
		AbstractChat domain = chatDomainMap.get(uniqueId);
		if (domain == null) {
			domain = ChatEnum.getEnum(channelType).newInstance(uniqueId);
			if (domain != null) {
				DataProcessor process = SpringContextHolder.getInstance().getBean(DataProcessor.class);
				ChatRecord record = process.selectByPrimaryKey(ChatRecord.class, uniqueId);
				domain.init(record);
				chatDomainMap.put(uniqueId, domain);
			}
		}
		return domain;
	}
	
	public Collection<AbstractChat> getAllDomain(){
		return chatDomainMap.values();
	}
	
}
