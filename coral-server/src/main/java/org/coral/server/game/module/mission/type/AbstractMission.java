package org.coral.server.game.module.mission.type;

import org.coral.server.game.module.mission.domain.MissionEnum;
import org.coral.server.game.module.mission.domain.MissionState;

/**
 * 	任务对象, 默认任务抽象类
 * @author Jeremy
 */
public abstract class AbstractMission implements IMission {

	protected int configId;		//	任务ID
	protected int state;		//	任务状态:0=未完成;1=已完成;2=已領取;
	protected int progress;		//	任务进度
	protected long recvTime;	//	任务接取时间
	protected long additional; 	//	额外参数,部分任务会用上
	
	public AbstractMission(int configId) {
		this.configId = configId;
		this.progress = 0;
		this.state = MissionState.STATE_NONE.getValue();
	}
	
	/** 任务ID **/
	public int getConfigId(){
		return this.configId;
	}
	
	public void setConfigId(int configId){
		this.configId = configId;
	}
	
	/** 任务状态:0=未完成;1=已完成;2=已領取; **/
	public int getState(){
		return this.state;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	/** 任务进度 **/
	public int getProgress(){
		return this.progress;
	}
	
	public void setProgress(int progress){
		this.progress = progress;
	}
	
	/** 任务接取时间 **/
	public long getRecvTime(){
		return this.recvTime;
	}
	
	public void setRecvTime(long recvTime){
		this.recvTime = recvTime;
	}
	
	@Override
	public void setAdditional(long additional) {
		this.additional = additional;
	}
	
	@Override
	public long getAdditional() {
		return additional;
	}
	
	@Override
	public String toString() {
		return "Mission[ configId= "+ configId +", state= "+ state +", progress= "+ progress +", recvTime= "+ recvTime +"]";
	}
	
	/**
	 * 任务进度
	 */
	public boolean progressMission(int progressDelta) {
		int progress = getProgress();
		progress += progressDelta;
		progress = Math.min(progress, getCompleteValue());
		setProgress(progress);

		if(isNone() && this.isMissionCanComplete())
		{//状态为未完成且当前可以完成
			setState(MissionState.STATE_COMPLETE.getValue());
		}
		return true;
	}
	
	/**
	 *  判断任务是否可以完成
	 * @return
	 */
	private boolean isMissionCanComplete(){
		if(getCompleteType() == MissionEnum.TYPE_DEFAULT.getType() 
				&& getProgress()<getCompleteValue()) {
			setProgress(getCompleteValue());	//无完成条件的,直接完成
		}
		return getProgress()>=getCompleteValue();
	}
	
}
