package org.coral.net.util;
import org.coral.net.core.base.DataCarrier;

import io.netty.buffer.ByteBuf;

public class CodecUtil {
	
	public static void codec(DataCarrier data, ByteBuf outBuffer) {
		if (data == null || outBuffer == null) {
			throw new IllegalArgumentException("codec error, data or outBuffer is null");
        }
		outBuffer.writeBytes(data.getStructure());
		int protocolLen = data.getProtocolLen();
		int protocol = data.getProtocol();
        switch (protocolLen) {
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
	}

}
