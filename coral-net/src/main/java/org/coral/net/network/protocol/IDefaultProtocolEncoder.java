package org.coral.net.network.protocol;

import org.coral.net.core.base.Packet;

import io.netty.buffer.ByteBuf;

public interface IDefaultProtocolEncoder {
	
	default public void codec(Packet msg, ByteBuf outBuffer) {
		if (msg == null || outBuffer == null) {
			throw new IllegalArgumentException("codec error, data or outBuffer is null");
        }
		int protocolLen = Packet.PROTO_LEN;
		int protocol = msg.cmd();
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
        outBuffer.writeBytes(msg.data());
	}

}
