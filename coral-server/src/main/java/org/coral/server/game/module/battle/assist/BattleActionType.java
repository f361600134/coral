package org.coral.server.game.module.battle.assist;

/**
 * 战斗回合类型
 * @author Administrator
 *
 */
public enum BattleActionType {
	
	/**
	 * 未定义操作,未知操作
	 */
	UNRECOGNIZED(-1),
    /**
     * <pre>
     * 下一回合
     * </pre>
     *
     * <code>NEXT_ROUND = 0;</code>
     */
    NEXT_ROUND(0),
    /**
     * <pre>
     * 使用技能
     * </pre>
     *
     * <code>USE_SKILL = 1;</code>
     */
    USE_SKILL(1),
    /**
     * <pre>
     * 改变血量
     * </pre>
     *
     * <code>CHANGE_HP = 2;</code>
     */
    CHANGE_HP(2),
    /**
     * <pre>
     * 添加BUFF
     * </pre>
     *
     * <code>ADD_BUFF = 3;</code>
     */
    ADD_BUFF(3),
    /**
     * <pre>
     * BUFF改变血量
     * </pre>
     *
     * <code>BUFF_CHANGE_HP = 4;</code>
     */
    BUFF_CHANGE_HP(4),
    /**
     * <pre>
     * 移除BUFF
     * </pre>
     *
     * <code>CLEAN_BUFF = 5;</code>
     */
    CLEAN_BUFF(5),
    /**
     * <pre>
     * 复活
     * </pre>
     *
     * <code>RELIVE = 6;</code>
     */
    RELIVE(6),
    /**
     * <pre>
     * 伤害反弹
     * </pre>
     *
     * <code>DAMAGE_REBOUDN = 7;</code>
     */
    DAMAGE_REBOUDN(7),
	;
	
	/**
	 * 战斗操作类型
	 */
	private final int type;
	private BattleActionType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

}
