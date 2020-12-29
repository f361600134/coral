package org.coral.server.game.module.chat.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBDefine.PBProtocol;
import org.coral.server.game.data.proto.PBPlayer.AckChat;
import org.coral.server.game.data.proto.PBPlayer.ChatInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

public class AckChatResp implements IProtocol {

	private static final Logger log = LoggerFactory.getLogger(AckChatResp.class);
	
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
