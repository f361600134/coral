package org.coral.net.core.base;

import java.lang.reflect.Method;

import org.coral.net.core.reflect.MethodInvoker;

/**
 * 消息封装
 * @author Jeremy
 * @date 2020年7月4日
 */
public class Commander {
	
//	private final IController controller;
//	private final Method method;
	
	private final MethodInvoker invoker;
	private final boolean mustLogin;
	private final Method protobufParser;

	public Commander(IController controller, boolean mustLogin, Method method) throws Exception {
		//this.controller = controller;
		//this.method = method;
		this.invoker = MethodInvoker.create(controller, method);
		this.mustLogin = mustLogin;
		Class<?> paramType = method.getParameterTypes()[1];
		this.protobufParser = paramType.getMethod("parseFrom", byte[].class);
	}
	
	public static Commander create(IController controller, boolean mustLogin, Method method) throws Exception {
		return new Commander(controller, mustLogin, method);
	}

	public boolean isMustLogin() {
		return mustLogin;
	}

	
	public MethodInvoker getInvoker() {
		return invoker;
	}
//	public IController getController() {
//		return controller;
//	}
//
//	public Method getMethod() {
//		return method;
//	}

	public Method getProtobufParser() {
		return protobufParser;
	}

}