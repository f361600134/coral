package org.coral.server.game.module.item.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.data.proto.PBDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
* AckQuickSellItemEquipResp
* @author Jeremy
*/
public class AckQuickSellItemEquipResp implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(AckQuickSellItemEquipResp.class);
	
	private PBBag.AckQuickSellItemEquip.Builder builder;
	
	public AckQuickSellItemEquipResp() {
		this.builder = PBBag.AckQuickSellItemEquip.newBuilder();
	}
	
	public static AckQuickSellItemEquipResp newInstance() {
		return new AckQuickSellItemEquipResp();
	}
	
	public PBBag.AckQuickSellItemEquip build() {
		return builder.build();
	}
	
	/** 错误码**/
	public void setCode(int value){
		this.builder.setCode(value);
	}
	
	@Override
	public short protocol() {
		return PBDefine.PBProtocol.AckQuickSellItemEquip_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}
}
