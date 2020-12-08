package org.coral.server.game.module.item.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.data.proto.PBDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.Message;

/**
* AckBagUsePackageResp
* @author Jeremy
*/
public class AckBagUsePackageResp implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(AckBagUsePackageResp.class);
	
	private PBBag.AckBagUsePackage.Builder builder;
	
	public AckBagUsePackageResp() {
		this.builder = PBBag.AckBagUsePackage.newBuilder();
	}
	
	public static AckBagUsePackageResp newInstance() {
		return new AckBagUsePackageResp();
	}
	
	public PBBag.AckBagUsePackage build() {
		return builder.build();
	}
	
	/** 错误码**/
	public void setCode(int value){
		this.builder.setCode(value);
	}

	@Override
	public short protocol() {
		return PBDefine.PBProtocol.AckBagUsePackage_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}
}
