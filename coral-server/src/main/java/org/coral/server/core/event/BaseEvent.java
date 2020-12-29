package org.coral.server.core.event;

import org.coral.server.utils.TimeUtil;

/**
 * 事件基础类, 可用作于公共事件基础类
 * @author Administrator
 *
 */
public abstract class BaseEvent implements IEvent{
	
	/**
	 * 发送时间， 可在外部设置值.
	 */
	private final long time;
	
	public BaseEvent() {
		this.time = TimeUtil.now();
	}
	
	public BaseEvent(long time) {
		this.time = time;
	}
	
	@Override
	public long getTime() {
		return time;
	}
	
}
