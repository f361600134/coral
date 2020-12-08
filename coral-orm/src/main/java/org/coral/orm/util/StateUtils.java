package org.coral.orm.util;

/**
 * 状态运算工具类
 * @author Jereme
 *
 */
public class StateUtils {
	
	/**
	 * 判断是否存在状态
	 */
	public static boolean check(int state, int curState) {
		if (state == 0 && state == curState) {
			return true;
		}
		return 0 != (state & curState);
	}
	
	/**
	 * 增加状态
	 */
	public static int addState(int state, int curState) {
		return curState | state;
	}
	
	/**
	 * 清除一个状态
	 */
	public static int removeState(int state, int curState) {
		return curState & ~state;
	}

}
