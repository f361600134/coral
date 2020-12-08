package org.coral.server.game.module.wealth.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.coral.server.game.helper.ResourceType;

/**
 * @auth Jeremy
 * @date 2020年8月20日下午5:48:01
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WealthIndex {
	
	ResourceType value();
	byte type() default CHECK;

	/*检查*/
	byte CHECK = 0;
	/*奖励*/
	byte REWARD = 1;
	/*消耗*/
	byte COST = 2;
}
