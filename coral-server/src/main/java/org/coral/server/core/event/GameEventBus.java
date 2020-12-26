package org.coral.server.core.event;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;

/**
 * 游戏事件总线
 *支持两种事件机制
 *发布订阅,默认注册. 不能移除掉
 *监听机制,像活动类任务, 只有活动开启时才注册, 活动结束取消注册.
 */
public class GameEventBus {
	
	private static final Logger logger = LoggerFactory.getLogger(GameEventBus.class);
	
	private static GameEventBus gameEventBus = new GameEventBus();
	private EventBus eventBus = new EventBus();
	
	private GameEventBus(){
	}
	
	public static GameEventBus instance(){
		return gameEventBus;
	}

	/**
	 * 注册观察者相关的订阅事件
	 * @param object
	 */
	public void register(Object object) {
		eventBus.register(object);
	}
	
	public void register(Collection<Class<?>> classes) {
		for (Class<?> cls : classes) {
			try {
				Object o = cls.newInstance();
				register(o);
			} catch (Exception e) {
				logger.error("注册观察者过程出现异常", e);
			}
		}
		logger.info("register events number:{}", classes.size());
	}
	
	/**
	 * 发送事件
	 * @param event
	 */
	public void post(IEvent event) {
		eventBus.post(event);
	}
	
	public void post(IEvent ...events) {
		if (events.length <= 0) {
			return;
		}
		for (IEvent event : events) {
			if (event == null) {
				continue;
			}
			post(event);
		}
	}
}
