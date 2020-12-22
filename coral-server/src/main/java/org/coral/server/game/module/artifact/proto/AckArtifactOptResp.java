package org.coral.server.game.module.artifact.proto;

import org.coral.net.core.base.IProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* AckArtifactOptResp
* @author Jeremy
*/
public class AckArtifactOptResp implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(AckArtifactOptResp.class);

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
	
//	private PBArtifact.AckArtifactOpt.Builder builder;
//	
//	public AckArtifactOptResp() {
//		this.builder = PBArtifact.AckArtifactOpt.newBuilder();
//	}
//	
//	public static AckArtifactOptResp newInstance() {
//		return new AckArtifactOptResp();
//	}
//	
//	public PBArtifact.AckArtifactOpt build() {
//		return builder.build();
//	}
//	
//	/** 神兵配置id**/
//	public void setConfigId(int value){
//		this.builder.setConfigId(value);
//	}
//	
//	/** 1:升级,2:精炼,3:技能升级,4:重铸**/
//	public void setOptType(int value){
//		this.builder.setOptType(value);
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
//		return PBDefine.PBProtocol.AckArtifactOpt_VALUE;
//	}
//
//	@Override
//	public Message getMessage() {
//		return builder.build();
//	}
}
