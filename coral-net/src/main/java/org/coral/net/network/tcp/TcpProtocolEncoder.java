package org.coral.net.network.tcp;

import org.coral.net.core.base.DataCarrier;
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
public class TcpProtocolEncoder extends MessageToByteEncoder<DataCarrier> implements IDefaultProtocolEncoder {

	//private static final Logger log = LoggerFactory.getLogger(TcpProtocolEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext ctx, DataCarrier data, ByteBuf out) throws Exception {
		if (data!=null) {
			codec(data, out);
		}else {
			ReferenceCountUtil.retain(data);
		}
		
	}

}
