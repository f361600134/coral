package org.coral.orm.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.coral.orm.core.db.dao.CommonDao;

/**
 * Dao持久化对象注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dao {
	/**Dao name*/
	Class value() default CommonDao.class;
}
