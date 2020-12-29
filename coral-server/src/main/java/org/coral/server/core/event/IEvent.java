package org.coral.server.core.event;

import org.apache.commons.lang3.StringUtils;

public interface IEvent {
	
	/**
	 * 获取事件唯一标识
	 * @return
	 */
	public String getEventId();
	
	/**
	 * 获取事件发送时间, 可以从外部设置值, 如果不设置则默认为发送时间
	 * @return
	 */
	public long getTime();
	
	/**
	 * 判断是否属于同一个事件
	 * @param eventName
	 * @return
	 */
	default public boolean isSameEvent(String eventName) {
		return StringUtils.equals(getEventId(), eventName);
	}
	
}
