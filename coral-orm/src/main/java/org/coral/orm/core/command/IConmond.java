package org.coral.orm.core.command;

/**
 * O object 需要存储的对象
 * D dao
 * T 返回对象
 * @auth Jeremy
 * @date 2021年1月17日下午11:17:38
 */
@FunctionalInterface
public interface IConmond<O, D> {
	 <T> T execute() throws Exception;
}
