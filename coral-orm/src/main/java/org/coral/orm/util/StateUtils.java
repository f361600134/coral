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
	
	public static int state_simple_none = 1 << 0; //未达成普通
	public static int state_simple_noreward = 1 << 1; //已达成普通未领取
	public static int state_simple_rewarded = 1 << 2; //已领取普通奖励
	public static int state_exclusive_none = 1 << 3; //未达成专属
	public static int state_exclusive_noreward = 1 << 4; //已达成专属未领取
	public static int state_exclusive_rewarded = 1 << 5; //全部领取
	
	static int[] arrs = new int[] {state_simple_none, state_simple_noreward, state_simple_rewarded,
				state_exclusive_none, state_exclusive_noreward, state_exclusive_rewarded};
	
	public static void main(String[] args) {
		for (int state : arrs) {
			System.out.println("状态:"+state);
		}
		int curState = 0;
		curState = addState(state_simple_none, curState);
		curState = addState(state_exclusive_none, curState);
		System.out.println("未达成普通及专属"+curState);
		curState = addState(state_simple_noreward, curState);
		curState = removeState(state_simple_none, curState);
		System.out.println("达成普通,未达成专属"+curState);
	}
	
	//普通奖励,专属奖励并存
	public static void test2() {
		//初始状态--普通未达成,专属未达成
		int curState = state_simple_none;
		curState = addState(state_exclusive_none, curState);
		printDesc(curState);
		
		//达成普通，未达成专属
		curState = addState(state_simple_noreward, curState);
		curState = removeState(state_simple_none, curState);
		printDesc(curState);
		
//		//已领取普通奖励
//		curState = addState(state_simple_rewarded, curState);
//		curState = removeState(state_simple_noreward, curState);
//		printDesc(curState);
		
		//达成专属
		curState = addState(state_exclusive_noreward, curState);
		curState = removeState(state_exclusive_none, curState);
		printDesc(curState);
		
		//领取专属
		curState = addState(state_exclusive_rewarded, curState);
		curState = removeState(state_exclusive_noreward, curState);
		printDesc(curState);
		
	}

	//正常流程
	public static void test1() {
		//初始状态--普通未达成,专属未达成
		int curState = state_simple_none;
		curState = addState(state_exclusive_none, curState);
		printDesc(curState);
		
		//达成普通，未达成专属
		curState = addState(state_simple_noreward, curState);
		curState = removeState(state_simple_none, curState);
		printDesc(curState);
		
		//已领取普通奖励
		curState = addState(state_simple_rewarded, curState);
		curState = removeState(state_simple_noreward, curState);
		printDesc(curState);
		
		//达成专属
		curState = addState(state_exclusive_noreward, curState);
		curState = removeState(state_exclusive_none, curState);
		printDesc(curState);
		
		//领取专属
		curState = addState(state_exclusive_rewarded, curState);
		curState = removeState(state_exclusive_noreward, curState);
		printDesc(curState);
		
	}
	static void printDesc(int curState) {
		System.out.println("=========curState========="+curState);
		if (check(state_simple_none, curState)) {
			System.out.println("未达成,不可领取--[未达成]");
		}
		if (check(state_simple_noreward, curState)) {
			System.out.println("可领取普通奖励--[领取]");
		}
//		if (check(state_simple_rewarded, curState)) {
//			System.out.println("已领取普通奖励--[领取]");
//		}
		if (check(state_simple_rewarded, curState) && check(state_exclusive_none, curState)) {
			System.out.println("领取普通奖励, 未达成专属奖励--[继续领取]");
		}
		if (check(state_exclusive_noreward, curState)) {
			System.out.println("已达成专属未领取--[领取]");
		}
		if (check(state_exclusive_rewarded, curState)) {
			System.out.println("全部领取成功--[已领取]");
		}
	}

}
