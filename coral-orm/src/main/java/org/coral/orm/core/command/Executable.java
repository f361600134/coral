package org.coral.orm.core.command;

/**
 * 可执行接口
 * @author Jeremy
 * @date 2020年8月13日
 *
 * @param <V>
 */
@FunctionalInterface
public interface Executable<V> {
    V execute() throws Exception;
}