package com.maya.game.modules.maill.resp;

import com.google.protobuf.Message;
import com.maya.common.network.NetworkResponse;
import com.maya.game.proto.PBDefine;
import com.maya.game.proto.PBPlayer;

public class AckReadEmailResp extends NetworkResponse {
	
	private PBPlayer.AckReadEmail.Builder builder;
	
	public AckReadEmailResp() {
		this.builder = PBPlayer.AckReadEmail.newBuilder();
	}

	public static AckReadEmailResp newInstance() {
		return new AckReadEmailResp();
	}
	
	public void setId(long value) {
		this.builder.setId(value);
	}
	
	public void setCode(int value) {
		this.builder.setCode(value);
	}
	
	@Override
	public int getProtocol() {
		return PBDefine.PBProtocol.AckReadEmail_VALUE;
	}

	@Override
	public Message getMessage() {
		return builder.build();
	}
	

}
