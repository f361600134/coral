package org.coral.server.game.module.artifact.proto;

import org.coral.net.core.base.IProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* AckArtifactListResp
* @author Jeremy
*/
public class AckArtifactListResp implements IProtocol{

	private static final Logger log = LoggerFactory.getLogger(AckArtifactListResp.class);

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
	
//	private PBArtifact.AckArtifactList.Builder builder;
//	
//	public AckArtifactListResp() {
//		this.builder = PBArtifact.AckArtifactList.newBuilder();
//	}
//	
//	public static AckArtifactListResp newInstance() {
//		return new AckArtifactListResp();
//	}
//	
//	public PBArtifact.AckArtifactList build() {
//		return builder.build();
//	}
//	
//	/** 神兵列表**/
//	public void addArtifactlist(PBArtifact.PBArtifactInfo value){
//		this.builder.addArtifactlist(value);
//	}
//	
////	public AckArtifactListResp addArtifactlist(Artifact artifact) {
////		PBArtifactInfoBuilder abuilder = PBArtifactInfoBuilder.toProto(artifact);
////		//任务列表
////		PBArtifactMissionInfoBuilder mbuilder = null;
////		for (Mission mission : artifact.getMissions()) {
////			mbuilder = PBArtifactMissionInfoBuilder.toProto(mission);
////			abuilder.addMissions(mbuilder.build());
////		}
////		this.builder.addArtifactlist(abuilder.build());
////		return this;
////	}
//	
//	@Override
//	public int getProtocol() {
//		return PBDefine.PBProtocol.AckArtifactList_VALUE;
//	}
//
//	@Override
//	public Message getMessage() {
//		return builder.build();
//	}
}
