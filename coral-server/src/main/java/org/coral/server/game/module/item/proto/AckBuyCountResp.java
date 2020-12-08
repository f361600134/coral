package org.coral.server.game.module.item.proto;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.data.proto.PBDefine;

import com.google.protobuf.AbstractMessageLite.Builder;

public class AckBuyCountResp implements IProtocol {

	private PBBag.AckBuyCount.Builder builder;

	public static AckBuyCountResp newInstance(){
		return new AckBuyCountResp();
	}

    public AckBuyCountResp() {
        this.builder = PBBag.AckBuyCount.newBuilder();
    }

    public void setCode(int code){
	    this.builder.setCode(code);
    }
    
//    public void addItem(Collection<IItem> items) {
//		for (IItem item : items) {
//			ItemInfo itemInfo = item.toProto();
//			if (itemInfo == null)
//				continue;
//
//			builder.addBags(itemInfo);
//		}
//    }

	@Override
	public short protocol() {
		 return PBDefine.PBProtocol.AckBuyCount_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		 return builder;
	}
	
}
