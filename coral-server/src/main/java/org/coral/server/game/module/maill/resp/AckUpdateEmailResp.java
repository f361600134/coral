package com.maya.game.modules.maill.resp;

import com.google.protobuf.Message;
import com.maya.common.network.NetworkResponse;
import com.maya.game.modules.maill.domain.Mail;
import com.maya.game.proto.PBDefine;
import com.maya.game.proto.PBPlayer;

public class AckUpdateEmailResp extends NetworkResponse {
	
	private PBPlayer.AckUpdateEmail.Builder builder;
	
	public AckUpdateEmailResp() {
		this.builder = PBPlayer.AckUpdateEmail.newBuilder();
	}

	public static AckUpdateEmailResp newInstance() {
		return new AckUpdateEmailResp();
	}
	
	public void addMails(Mail mail) {
		this.builder.addEmails(mail.toProto());
	}
	
	@Override
	public int getProtocol() {
		return PBDefine.PBProtocol.AckUpdateEmail_VALUE;
	}

	@Override
	public Message getMessage() {
		return builder.build();
	}
	

}
