package org.coral.net.network.protocol;

import org.coral.net.core.base.DataCarrier;

import io.netty.buffer.ByteBuf;

public interface IDefaultProtocolEncoder {
	
	default public void codec(DataCarrier data, ByteBuf outBuffer) {
		if (data == null || outBuffer == null) {
			throw new IllegalArgumentException("codec error, data or outBuffer is null");
        }
		int protocolLen = data.getProtocolLen();
		int protocol = data.getProtocol();
        switch (protocolLen) {//写入长度
        case 1:
        	outBuffer.writeByte((byte) protocol);
            break;
        case 2:
            outBuffer.writeShort((short) protocol);
            break;
        case 4:
            outBuffer.writeInt(protocol);
            break;
        default:
            throw new Error("should not reach here");
        }
        //写入内容
        outBuffer.writeBytes(data.getStructure());
	}

}
