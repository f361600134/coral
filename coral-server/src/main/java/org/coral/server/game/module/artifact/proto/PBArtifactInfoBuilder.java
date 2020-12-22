package org.coral.server.game.module.artifact.proto;

import org.coral.net.core.base.IProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* PBArtifactInfoBuilder
* @author Jeremy
*/
public class PBArtifactInfoBuilder implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(PBArtifactInfoBuilder.class);
	
//	private PBArtifact.PBArtifactInfo.Builder builder;
//	
//	public PBArtifactInfoBuilder() {
//		this.builder = PBArtifact.PBArtifactInfo.newBuilder();
//	}
//	
//	public static PBArtifactInfoBuilder newInstance() {
//		return new PBArtifactInfoBuilder();
//	}
//	
//	public PBArtifact.PBArtifactInfo build() {
//		return builder.build();
//	}
//	
//	/** 神兵配置id**/
//	public void setConfigId(int value){
//		this.builder.setConfigId(value);
//	}
//	
//	/** 普通等级**/
//	public void setLevel(int value){
//		this.builder.setLevel(value);
//	}
//	
//	/** 经验**/
//	public void setExp(int value){
//		this.builder.setExp(value);
//	}
//	
//	/** 精炼等级**/
//	public void setRefineLv(int value){
//		this.builder.setRefineLv(value);
//	}
//	
//	/** 技能等级**/
//	public void setSkillLv(int value){
//		this.builder.setSkillLv(value);
//	}
//	
//	/** 状态0:未激活,1:已激活**/
//	public void setState(int value){
//		this.builder.setState(value);
//	}
//	
//	/** 神器任务信息**/
//	public void addMissions(PBBag.PBMissionInfo value){
//		this.builder.addMissions(value);
//	}
//	
//	/** 神器任务信息**/
//	public void addAllMissions(Collection<PBBag.PBMissionInfo> value){
//		this.builder.addAllMissions(value);
//	}
//	
//	/** 皮肤id**/
//	public void setSkinId(int value){
//		this.builder.setSkinId(value);
//	}
//	
//	/** 已使用刻印石数量**/
//	public void setUseStampStoneNum(int value){
//		this.builder.setUseStampStoneNum(value);
//	}
//	
////	/**
////	 * 生成proto
////	 * @param artifact
////	 * @return
////	 */
////	public static PBArtifactInfoBuilder toProto(Artifact artifact) {
////		PBArtifactInfoBuilder builder = PBArtifactInfoBuilder.newInstance();
////		builder.setConfigId(artifact.getConfigId());
////		builder.setLevel(artifact.getLevel());
////		builder.setExp(artifact.getExp());
////		builder.setRefineLv(artifact.getRefineLv());
////		builder.setSkillLv(artifact.getSkillLv());
////		builder.setState(artifact.getState());
////		builder.setSkinId(artifact.getSkinId());
////		builder.setHolySealLv(artifact.getHolySealLv());
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
