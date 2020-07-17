package org.coral.server.game.module.player.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBLogin;

import com.google.protobuf.AbstractMessageLite.Builder;

public class AckLoginResp implements IProtocol{
	
	private PBLogin.AckLogin.Builder builder;
	
	public AckLoginResp() {
		this.builder = PBLogin.AckLogin.newBuilder();
		this.set();
	}
	
	private void set() {
		this.builder.setCode(0);
		this.builder.setStatus(1);
	}
	
	public static AckLoginResp create() {
		return new AckLoginResp();
	}
	
	@Override
	public short protocol() {
		return 2;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}

}
