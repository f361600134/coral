package org.coral.net.network.tcp;

import org.coral.net.core.base.Packet;
import org.coral.net.network.protocol.IDefaultProtocolEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * 游戏协议编码器
 */
public class TcpProtocolEncoder extends MessageToByteEncoder<Packet> implements IDefaultProtocolEncoder {

	private static final Logger log = LoggerFactory.getLogger(TcpProtocolEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
		if (msg!=null) {
			codec(msg, out);
		}else {
			ReferenceCountUtil.retain(msg);
		}	
		log.info("TcpProtocolEncoder out:{}", out);
	}

//	public void codec(Packet msg, ByteBuf outBuffer) {
//		if (msg == null || outBuffer == null) {
//			throw new IllegalArgumentException("codec error, data is null or outBuffer");
//        }
//		int protocolLen = Packet.PROTO_LEN;
//		int protocol = msg.cmd();
//        switch (protocolLen) {//写入长度
//        case 1:
//        	outBuffer.writeByte((byte) protocol);
//            break;
//        case 2:
//            outBuffer.writeShort((short) protocol);
//            break;
//        case 4:
//            outBuffer.writeInt(protocol);
//            break;
//        default:
//            throw new Error("should not reach here");
//        }
//        //写入内容
//        outBuffer.writeBytes(msg.data());
//	}

}
