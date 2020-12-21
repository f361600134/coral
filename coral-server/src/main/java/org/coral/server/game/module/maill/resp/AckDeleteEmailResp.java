package com.maya.game.modules.maill.resp;

import com.google.protobuf.Message;
import com.maya.common.network.NetworkResponse;
import com.maya.game.proto.PBDefine;
import com.maya.game.proto.PBPlayer;

import java.util.Collection;

public class AckDeleteEmailResp extends NetworkResponse {
	
	private PBPlayer.AckDeleteEmail.Builder builder;
	
	public AckDeleteEmailResp() {
		this.builder = PBPlayer.AckDeleteEmail.newBuilder();
	}

	public static AckDeleteEmailResp newInstance() {
		return new AckDeleteEmailResp();
	}
	
	public void setCode(int code) {
		this.builder.setCode(code);
	}
	
	public void addMailId(long mailId) {
		this.builder.addDeletes(mailId);
	}
	
	public void addMailId(Collection<Long> mailIds) {
		this.builder.addAllDeletes(mailIds);
	}
	
	@Override
	public int getProtocol() {
		return PBDefine.PBProtocol.AckDeleteEmail_VALUE;
	}

	@Override
	public Message getMessage() {
		return builder.build();
	}
	

}
