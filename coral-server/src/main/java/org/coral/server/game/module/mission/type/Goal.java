package org.coral.server.game.module.mission.type;

import org.coral.server.core.annotation.NotUse;

/**
 * 	任务目标
 * @author Jeremy
 *
 */
@NotUse
public class Goal {
	
	/**
	 * 	完成类型
	 */
	private int completeType;
	
	/**
	 * 	完成条件
	 */
	private int completeCondition;
	
	/**
	 * 	完成值
	 */
	private int completeValue;
	
	/**
	 * 	当前进度
	 */
	protected int progress;//任务进度
	
	public Goal() {}
	
	public Goal(int completeType, int completeCondition, int completeValue) {
		super();
		this.completeType = completeType;
		this.completeCondition = completeCondition;
		this.completeValue = completeValue;
		this.progress = 0;
	}

	/** 完成类型 **/
	public int getCompleteType() {
		return completeType;
	}

	public void setCompleteType(int completeType) {
		this.completeType = completeType;
	}

	/** 完成条件 **/
	public int getCompleteCondition() {
		return completeCondition;
	}

	public void setCompleteCondition(int completeCondition) {
		this.completeCondition = completeCondition;
	}

	/** 完成值 **/
	public int getCompleteValue() {
		return completeValue;
	}

	public void setCompleteValue(int completeValue) {
		this.completeValue = completeValue;
	}
	
	/** 任务进度 **/
	public int getProgress(){
		return this.progress;
	}
	
	public void setProgress(int progress){
		this.progress = progress;
	}

	@Override
	public String toString() {
		return "Goal [completeType=" + completeType + ", completeCondition=" + completeCondition + ", completeValue="
				+ completeValue + "]";
	}
	
}
