package org.coral.server.game.module.item.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.Message;

/**
* AttrInfoBuilder
* @author Jeremy
*/
public class AttrInfoBuilder implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(AttrInfoBuilder.class);
	
	private PBBag.AttrInfo.Builder builder;
	
	public AttrInfoBuilder() {
		this.builder = PBBag.AttrInfo.newBuilder();
	}
	
	public static AttrInfoBuilder newInstance() {
		return new AttrInfoBuilder();
	}
	
	public PBBag.AttrInfo build() {
		return builder.build();
	}
	
	/** 属性,攻击,防御等**/
	public void setConfigId(int value){
		this.builder.setConfigId(value);
	}
	
	/** 属性值**/
	public void setAttrValue(int value){
		this.builder.setAttrValue(value);
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
