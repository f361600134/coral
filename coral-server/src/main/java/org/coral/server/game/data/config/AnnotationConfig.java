package org.coral.server.game.data.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置管理器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationConfig {
	
	/**
	 * json名字
	 * @return  
	 * @return String  
	 * @date 2020年9月3日下午6:41:53
	 */
	Class<?> name() default IConfig.class;
	
}
