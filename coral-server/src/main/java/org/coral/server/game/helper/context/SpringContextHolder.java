package org.coral.server.game.helper.context;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware{

	private static SpringContextHolder _instance = null;
	
	private ApplicationContext springContext;
	
	public static SpringContextHolder getInstance() {
		if(_instance==null)
		{
			_instance = new SpringContextHolder();
		}
		return _instance;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException
	{
		_instance = this;
		springContext = arg0;
	}
		
	public ApplicationContext getSpringContext() {
		return springContext;
	}
	
	public <T> T getBean(Class<T> tClass) {
		return this.springContext.getBean(tClass);
	}
	
	/**
	 * 根据注解类型获取实体集合
	 * 
	 * @param classType
	 * @return
	 */
	public Collection<Object> getBeansWithAnnotation(Class<? extends Annotation> classType) {
		return this.springContext.getBeansWithAnnotation(classType).values();
	}
}
