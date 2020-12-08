package org.coral.server.game.module.player.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBDefine.PBProtocol;
import org.coral.server.game.data.proto.PBPlayer.AckTips;

import com.google.protobuf.AbstractMessageLite.Builder;

public class AckTipsResp implements IProtocol {

	private AckTips.Builder builder;

	public static AckTipsResp newInstance() {
		return new AckTipsResp();
	}

	public AckTipsResp() {
		this.builder = AckTips.newBuilder();
	}

	/*
	 * 错误码id
	 */
	public AckTipsResp setTipsId(int value) {
		this.builder.setTipsId(value);
		return this;
	}
	
	/*
	 * 错误码参数
	 */
	public AckTipsResp addParams(Object value) {
		this.builder.addParams(value.toString());
		return this;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}

	@Override
	public short protocol() {
		return PBProtocol.AckTips_VALUE;
	}
}
