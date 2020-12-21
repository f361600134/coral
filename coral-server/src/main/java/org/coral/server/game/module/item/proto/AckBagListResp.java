package org.coral.server.game.module.item.proto;

import java.util.Collection;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.data.proto.PBDefine;
import org.coral.server.game.module.item.domain.IItem;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.Message;

public class AckBagListResp implements IProtocol {

	private PBBag.AckBagList.Builder builder;

	public static AckBagListResp newInstance(){
		return new AckBagListResp();
	}
	
    public AckBagListResp() {
        this.builder = PBBag.AckBagList.newBuilder();
    }
    
    public void addItem(Collection<IItem> items) {
		for (IItem item : items) {
            PBBag.ItemInfo itemInfo = item.toProto();
			if (itemInfo == null) 
				continue;
			builder.addBags(itemInfo);
		}
    }

	@Override
	public short protocol() {
		 return PBDefine.PBProtocol.AckUpdateBagList_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}

}
