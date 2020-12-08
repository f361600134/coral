package org.coral.server.localvarGc;

import java.util.Vector;

/**
 * 使用JVM跟踪日志, 在线上出现异常时, 及时收集信息, 便于发现问题
 * -Xmx20m 最大堆内存
 * -Xms5m 最小堆内存
 * -Xmn1m 初始新生代内存
 * -Xss128k 栈空间大小
 * _XX:SurvivorRadio=2 新生代/幸存者
 * "-XX:OnOutOfMemoryError=d:printStack.bat %p" 异常时执行脚本
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump 开启堆内存溢出日志, 写入日志到d盘a.dump文件内
 * -XX:NewRadio=老年代/新生代 比例
 * -XX:MaxMetaspaceSize 最大元空间
 * -Xmx20m -Xms5m "-XX:OnOutOfMemoryError=d:printStack.bat %p" -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
 * @auth Jeremy
 * @date 2020年9月28日下午1:53:27
 */
public class DumpOOM {
	public static void main(String[] args) {
		Vector list = new Vector<>();
		for (int i = 0; i < 25; i++) {
			list.add(new byte[1*1024*1024]);
		}
	}
}
