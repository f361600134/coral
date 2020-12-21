package com.maya.game.modules.maill.resp;

import com.google.protobuf.Message;
import com.maya.common.network.NetworkResponse;
import com.maya.game.proto.PBDefine;
import com.maya.game.proto.PBPlayer;

public class AckReceiveEmailResp extends NetworkResponse {
	
	private PBPlayer.AckReceiveEmail.Builder builder;
	
	public AckReceiveEmailResp() {
		this.builder = PBPlayer.AckReceiveEmail.newBuilder();
	}

	public static AckReceiveEmailResp newInstance() {
		return new AckReceiveEmailResp();
	}
	
	public void setCode(int value) {
		this.builder.setCode(value);
	}
	
	@Override
	public int getProtocol() {
		return PBDefine.PBProtocol.AckReceiveEmail_VALUE;
	}

	@Override
	public Message getMessage() {
		return builder.build();
	}
	

}
