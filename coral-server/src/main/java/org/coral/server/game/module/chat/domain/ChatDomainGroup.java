package org.coral.server.game.module.chat.domain;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 聊天域分组, 按照聊天频道区分, 有些为单频道,有些为多频道
 * @author Administrator
 *
 */
public class ChatDomainGroup {
	
	private int channelType;
	
	private Map<Long, ChatDomain> chatDomainMap;
	
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

	public Map<Long, ChatDomain> getChatDomainMap() {
		return chatDomainMap;
	}

	public void setChatDomainMap(Map<Long, ChatDomain> chatDomainMap) {
		this.chatDomainMap = chatDomainMap;
	}
	
	/**
	 * 获取或创建domain
	 * @param domainId
	 * @return
	 */
	public ChatDomain getOrCreateDomain(long domainId) {
		ChatDomain domain = chatDomainMap.get(domainId);
		if (domain == null) {
			domain = new ChatDomain(channelType, domainId);
			chatDomainMap.put(domainId, domain);
		}
		return domain;
	}
	
	public Collection<ChatDomain> getAllDomain(){
		return chatDomainMap.values();
	}
	
}
