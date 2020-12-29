package org.coral.server.game.module.chat.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBPlayer.ChatInfo;
import org.coral.server.game.module.chat.domain.Chat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.AbstractMessageLite.Builder;

public class PBChatInfoBuilder implements IProtocol{
	
	private static final Logger log = LoggerFactory.getLogger(PBChatInfoBuilder.class);

	private ChatInfo.Builder builder;

	public static PBChatInfoBuilder newInstance(){
		return new PBChatInfoBuilder();
	}
	
    public PBChatInfoBuilder() {
        this.builder = ChatInfo.newBuilder();
    }
    
    public void setInfo(Chat chat){
//    	PBPlayerProfileBuilder pBuilder = PBPlayerProfileBuilder.newInstance();
//    	OtherPlayer otherPlayer = Context.getOtherPlayerService().getOtherPlayerById(chat.getSendId());
//    	if (otherPlayer != null) {
//    		pBuilder.setInfo(otherPlayer);
//		}else {
//			log.info("Can not found otherplayer 1, id:{}"+chat.getSendId());;
//		}
//    	this.builder.setContent(chat.getContent());
//    	this.builder.setPlayerInfo(pBuilder.build());
//    	
//    	if (chat.getChannel() == ChatEnum.CH_PRIVATE.getCh()) {//私聊对象
//    		pBuilder = PBPlayerProfileBuilder.newInstance();
//        	otherPlayer =  Context.getOtherPlayerService().getOtherPlayerById(chat.getToId());
//        	if (otherPlayer != null) {
//            	pBuilder.setInfo(otherPlayer);
//    		}else {
//    			log.info("Can not found otherplayer 2, id:{}"+chat.getSendId());;
//    		}
//		}
//    	this.builder.setRecPlayerInfo(pBuilder.build());
    }
    
    public ChatInfo build() {
    	return this.builder.build();
    }

	@Override
	public short protocol() {
		return 0;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}
	
}
