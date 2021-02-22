package org.coral.server.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 这个可以标注在方法 上, 表示方法定义了但是没用上
 * @author Jeremy
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface NotUse {

}
