package org.coral.net.core.base;

import com.google.protobuf.AbstractMessageLite.Builder;

/**
 * 下发协议接口
 * 
 * @author huachp
 */
public interface IProtocol {
	
	short protocol();
	
	default byte[] toBytes() {
		return getBuilder().build().toByteArray();
	}
	
	Builder<?, ?> getBuilder();
	
}
