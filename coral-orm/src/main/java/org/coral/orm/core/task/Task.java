package org.coral.orm.core.task;

/**
 * 可执行接口, 异步处理接口
 * @author Jeremy
 * @date 2020年8月13日
 *
 * @param <V>
 */
@FunctionalInterface
public interface Task {
    void execute() throws Exception;
}