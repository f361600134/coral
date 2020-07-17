package org.coral.server.game.module.player.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBLogin;

import com.google.protobuf.AbstractMessageLite.Builder;

public class ReqLogin implements IProtocol{
	
	private PBLogin.ReqLogin.Builder builder;
	
	public ReqLogin() {
		this.builder = PBLogin.ReqLogin.newBuilder();
		this.set();
	}
	
	public void set() {
		builder.setAl(1);
		builder.setChannel(1);
		builder.setDevice("Device");
		builder.setLoginSid(1);
		builder.setServerId(1);
		builder.setSessionKey("SessionKey");
		builder.setUserName("UserName");
		builder.setVersion("1.0.0");
	}
	
	
	public static ReqLogin create() {
		return new ReqLogin();
	}
	
	@Override
	public short protocol() {
		return 1;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}

}
