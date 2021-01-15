package org.coral.server.utils;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Method;

/**
 * 
 * 方法执行封装
 *
 */
public class MethodInvoker {

	private final MethodAccess invoker;

	private final Object target;

	private final int methodIndex;

	public MethodInvoker(MethodAccess invoker, Object target, int methodIndex) {
		this.invoker = invoker;
		this.target = target;
		this.methodIndex = methodIndex;
	}

	public static MethodInvoker create(Object target, Method method){
		MethodAccess methodAccess = MethodAccess.get(target.getClass());
		int methodIndex = methodAccess.getIndex(method.getName(), method.getParameterTypes());
		MethodInvoker methodInvoker = new MethodInvoker(methodAccess, target, methodIndex);
		return methodInvoker;
	}
	
	/**
	 * 方法调用
	 * @param args
	 * @return
	 */
	public Object invoke(Object... args){
		return invoker.invoke(target, methodIndex, args);
	}
	
}