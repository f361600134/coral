package org.coral.net.core.base;

import java.lang.reflect.Method;

/**
 * 消息封装
 * @author Jeremy
 * @date 2020年7月4日
 */
public class Commander {
	
	private final IController controller;
	private final boolean mustLogin;
	private final Method method;
	private final Method protobufParser;

	public Commander(IController controller, boolean mustLogin, Method method) throws Exception {
		this.controller = controller;
		this.mustLogin = mustLogin;
		this.method = method;
		Class<?> paramType = method.getParameterTypes()[1];
		this.protobufParser = paramType.getMethod("parseFrom", byte[].class);
	}

	public boolean isMustLogin() {
		return mustLogin;
	}

	public IController getController() {
		return controller;
	}

	public Method getMethod() {
		return method;
	}

	public Method getProtobufParser() {
		return protobufParser;
	}

}