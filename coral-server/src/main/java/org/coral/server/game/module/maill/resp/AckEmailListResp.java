package com.maya.game.modules.maill.resp;

import com.google.protobuf.Message;
import com.maya.common.network.NetworkResponse;
import com.maya.game.modules.maill.domain.Mail;
import com.maya.game.proto.PBDefine;
import com.maya.game.proto.PBPlayer;

import java.util.Collection;

public class AckEmailListResp extends NetworkResponse {
	
	private PBPlayer.AckEmailList.Builder builder;
	
	public static AckEmailListResp newInstance() {
		return new AckEmailListResp();
	}
	
	public AckEmailListResp() {
		this.builder = PBPlayer.AckEmailList.newBuilder();
	}
	
	public void addEmailInfo(Mail mail){
    	this.builder.addEmails(mail.toProto());
    }
	
	public void addEmailInfos(Collection<Mail> mails){
		for (Mail mail : mails) {
			this.builder.addEmails(mail.toProto());
		}
    }

	@Override
	public int getProtocol() {
		return PBDefine.PBProtocol.AckEmailList_VALUE;
	}

	@Override
	public Message getMessage() {
		return builder.build();
	}

}
