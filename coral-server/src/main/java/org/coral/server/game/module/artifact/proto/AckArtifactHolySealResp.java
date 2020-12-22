package org.coral.server.game.module.artifact.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBDefine;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* AckArtifactHolySealResp
* @author Jeremy
*/
public class AckArtifactHolySealResp implements IProtocol {

//	private static final Logger log = LoggerFactory.getLogger(AckArtifactHolySealResp.class);
//	
//	private PBArtifact.AckArtifactHolySeal.Builder builder;
//	
//	public AckArtifactHolySealResp() {
//		this.builder = PBArtifact.AckArtifactHolySeal.newBuilder();
//	}
//	
//	public static AckArtifactHolySealResp newInstance() {
//		return new AckArtifactHolySealResp();
//	}
//	
//	public PBArtifact.AckArtifactHolySeal build() {
//		return builder.build();
//	}
//	
//	/** 错误码**/
//	public void setCode(int value){
//		this.builder.setCode(value);
//	}
//	
	@Override
	public short protocol() {
		return PBDefine.PBProtocol.AckArtifactHolySeal_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return null;
	}
}
