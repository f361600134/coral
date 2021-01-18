package org.coral.server.core.event;

import java.util.List;

import org.coral.server.game.helper.context.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

/**
 * 游戏事件总线
 *支持两种事件机制
 *发布订阅,默认注册. 不能移除掉
 *监听机制,像活动类任务, 只有活动开启时才注册, 活动结束取消注册.
 */
@Component
public class GameEventBus {
	
	private static final Logger logger = LoggerFactory.getLogger(GameEventBus.class);
	
	/**
	 * 伪单例, 获取事件处理器
	 */
	private static GameEventBus gameEventBus;
	
	@Autowired(required = false)
	private List<IObserver> observers;
	
	private EventBus eventBus;
	
	private GameEventBus(){
		this.eventBus = new EventBus(); 
	}
	/**
	 * 注册观察者相关的订阅事件
	 * @param object
	 */
	public void register() {
		observers.forEach(o -> eventBus.register(o));
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
	
	/**
	 * 	发布事件
	 * @param event
	 */
	public static void publishEvent(IEvent event) {
		if (gameEventBus == null) {
			gameEventBus = SpringContextHolder.getInstance().getBean(GameEventBus.class);
		}
		gameEventBus.post(event);
	}
}
