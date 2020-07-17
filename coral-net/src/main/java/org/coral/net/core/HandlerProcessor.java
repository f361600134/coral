package org.coral.net.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.coral.net.core.annotation.Cmd;
import org.coral.net.core.base.Commander;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IHandler;
import org.coral.net.core.base.Packet;
import org.coral.net.util.MessageOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.GeneratedMessageLite;

@Component
public class HandlerProcessor implements InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(HandlerProcessor.class);
	
	@Autowired
	private List<IHandler> handlerList;
	
	private Map<Integer, Commander> commanderMap;
	
	/**
	 * 加载完成后, 初始化handler
	 */
	public void afterPropertiesSet() throws Exception {
		long startTime = System.currentTimeMillis();
		commanderMap = new HashMap<Integer, Commander>();
		for (IHandler handler : handlerList) {
			Method[] methods = handler.getClass().getDeclaredMethods();
			for (Method method : methods) {
				Cmd cmd = method.getAnnotation(Cmd.class);
				if (cmd == null) continue;
				//检查重复协议号
				if (commanderMap.containsKey(cmd.id())) {
					//log.error("协议号[{}]重复, 请检查!!!", cmd.id());
					throw new RuntimeException("发现重复协议号:"+cmd.id());
				}
				commanderMap.put(cmd.id(), new Commander(handler, cmd.mustLogin(), method));
			}
		}
		log.info("The initialization message is complete and takes [{}] milliseconds.", (System.currentTimeMillis() - startTime));
	}
	
	/**
	 * 协议调用
	 * 
	 * @param session	玩家会话信息
	 * @param cmd		协议号
	 * @param bytes		协议体
	 * @throws Exception
	 */
	public void invoke(GameSession session, Packet packet) throws Exception {
		int cmd = packet.cmd();
		Commander commander = commanderMap.get(cmd);
		if (commander != null) {
			long begin = System.currentTimeMillis();

			byte[] bytes = packet.data();
			
			Method parser = commander.getProtobufParser();
			GeneratedMessageLite<?, ?> params = (GeneratedMessageLite<?, ?>) parser.invoke(null, bytes);
			
			log.debug("收到协议[{}], pid={}, params={}, size={}B",
					cmd, session.getPlayerId(), MessageOutput.create(params), bytes.length);

			commander.getMethod().invoke(commander.getHandler(), session, params);

			long used = System.currentTimeMillis() - begin;
			// 协议处理超过1秒
			if (used > 1000) {
				log.error("协议[{}]处理慢!!!耗时{}ms", cmd, used);
			}

		}
	}
	
	public Map<Integer, Commander> getCommanderMap() {
		return commanderMap;
	}

	public Commander getCommander(int cmd) {
		return commanderMap.get(cmd);
	}
	
//	@Async("taskAsyncPool")
//	public void aaa() {
//		System.out.println("===========2========="+Thread.currentThread().getName());
//	}

}
