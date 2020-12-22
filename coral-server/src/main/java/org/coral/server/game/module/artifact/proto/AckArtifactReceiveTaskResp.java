package org.coral.server.game.module.artifact.proto;

import org.coral.net.core.base.IProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* AckArtifactReceiveTaskResp
* @author Jeremy
*/
public class AckArtifactReceiveTaskResp implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(AckArtifactReceiveTaskResp.class);
	
//	private PBArtifact.AckArtifactReceiveTask.Builder builder;
//	
//	public AckArtifactReceiveTaskResp() {
//		this.builder = PBArtifact.AckArtifactReceiveTask.newBuilder();
//	}
//	
//	public static AckArtifactReceiveTaskResp newInstance() {
//		return new AckArtifactReceiveTaskResp();
//	}
//	
//	public PBArtifact.AckArtifactReceiveTask build() {
//		return builder.build();
//	}
//	
//	/** 错误码**/
//	public void setCode(int value){
//		this.builder.setCode(value);
//	}
//	
//	
//	
//	@Override
//	public int getProtocol() {
//		return PBDefine.PBProtocol.AckArtifactReceiveTask_VALUE;
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
