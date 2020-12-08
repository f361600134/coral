package org.coral.server.game.module.item.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* PBRewardStateInfoBuilder
* @author Jeremy
*/
public class PBRewardStateInfoBuilder implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(PBRewardStateInfoBuilder.class);
	
	private PBBag.PBRewardStateInfo.Builder builder;
	
	public PBRewardStateInfoBuilder() {
		this.builder = PBBag.PBRewardStateInfo.newBuilder();
	}
	
	public static PBRewardStateInfoBuilder newInstance() {
		return new PBRewardStateInfoBuilder();
	}
	
	public PBBag.PBRewardStateInfo build() {
		return builder.build();
	}
	
	/** 配置id**/
	public void setConfigId(int value){
		this.builder.setConfigId(value);
	}
	
	/** 状态,0:未领取,1:可领取,2:已领取**/
	public void setState(int value){
		this.builder.setState(value);
	}
	
	@Override
	public short protocol() {
		return 0;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}
}
