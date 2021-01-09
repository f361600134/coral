package org.coral.server.game.module.chatfinal.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBDefine.PBProtocol;
import org.coral.server.game.data.proto.PBPlayer;
import org.coral.server.game.data.proto.PBPlayer.ChatInfo;

import com.google.protobuf.AbstractMessageLite.Builder;

public class ResChat implements IProtocol{
	
	private PBPlayer.AckChat.Builder builder;
	
	public ResChat() {
		this.builder = PBPlayer.AckChat.newBuilder();
	}
	
	public void setChatChannel(int value) {
		builder.setChatChannel(value);
	}
	
	public void addChats(ChatInfo value) {
		builder.addChats(value);
	}
	
	public static ResChat create() {
		return new ResChat();
	}
	
	@Override
	public short protocol() {
		return PBProtocol.AckChat_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}

}
