package org.coral.server.game.module.chatplus.proto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBDefine.PBProtocol;
import org.coral.server.game.data.proto.PBPlayer.AckChat;
import org.coral.server.game.data.proto.PBPlayer.ChatInfo;

import com.google.protobuf.AbstractMessageLite.Builder;

public class AckChatResp implements IProtocol {

	private static final Log log = LogFactory.getLog(AckChatResp.class);
	
	private AckChat.Builder builder;

	public static AckChatResp newInstance(){
		return new AckChatResp();
	}
	
    public AckChatResp() {
        this.builder = AckChat.newBuilder();
    }
    
    public void addChat(int channel, ChatInfo chatInfo){
    	this.builder.setChatChannel(channel);
    	this.builder.addChats(chatInfo);
    }
    
//    @Override
//    public int getProtocol() {
//        return PBProtocol.AckChat_VALUE;
//    }
//
//    @Override
//    public GeneratedMessageLite getMessage() {
//        return builder.build();
//    }

	@Override
	public short protocol() {
		return PBProtocol.AckChat_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}
	
}
