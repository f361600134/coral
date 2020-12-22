package org.coral.server.game.module.artifact.proto;

import org.coral.net.core.base.IProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* PBArtifactMissionInfoBuilder
* @author Jeremy
*/
public class PBArtifactMissionInfoBuilder implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(PBArtifactMissionInfoBuilder.class);
	
//	private PBBag.PBMissionInfo.Builder builder;
//	
//	public PBArtifactMissionInfoBuilder() {
//		this.builder = PBBag.PBMissionInfo.newBuilder();
//	}
//	
//	public static PBArtifactMissionInfoBuilder newInstance() {
//		return new PBArtifactMissionInfoBuilder();
//	}
//	
//	public PBBag.PBMissionInfo build() {
//		return builder.build();
//	}
//	
//	/** 神将任务配置id**/
//	public void setConfigId(int value){
//		this.builder.setConfigId(value);
//	}
//	
//	/** 当前任务进度**/
//	public void setProgress(int value){
//		this.builder.setProgress(value);
//	}
//	
//	/** 状态,是否完成,0:未完成，1：已完成未领取，2：已完成已领取**/
//	public void setState(int value){
//		this.builder.setState(value);
//	}
//	
////	/**
////	 * 任务转协议
////	 * @param mission
////	 * @return
////	 */
////	public static PBArtifactMissionInfoBuilder toProto(Artifact.Mission mission){
////		PBArtifactMissionInfoBuilder builder = PBArtifactMissionInfoBuilder.newInstance();
////		builder.setConfigId(mission.getConfigId());
////		builder.setProgress(mission.getPrograss());
////		builder.setState(mission.getState());
////		return builder;
////	}
//	
//	@Override
//	public int getProtocol() {
//		return 0;
//	}
//
//	@Override
//	public Message getMessage() {
//		return builder.build();
//	}

	@Override
	public short protocol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		// TODO Auto-generated method stub
		return null;
	}
}
