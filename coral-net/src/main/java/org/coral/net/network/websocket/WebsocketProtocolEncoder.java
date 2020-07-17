package org.coral.net.network.websocket;

import java.util.List;

import org.coral.net.core.base.DataCarrier;
import org.coral.net.network.protocol.IDefaultProtocolEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.ReferenceCountUtil;

public class WebsocketProtocolEncoder extends MessageToMessageEncoder<DataCarrier> implements IDefaultProtocolEncoder {

	private static final Logger log = LoggerFactory.getLogger(WebsocketProtocolEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, DataCarrier data, List<Object> out) throws Exception {
		if (data != null) {
			ByteBuf byteBuf = ctx.alloc().ioBuffer();
			codec(data, byteBuf);
			if (byteBuf != null && byteBuf.isReadable()) {
				BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(byteBuf);
				out.add(binaryWebSocketFrame);
			} else {
				// 释放空间
				byteBuf.release();
				log.info("WebsocketProtocolEncoder error, byteBuf is null or not use to be Readable");
			}
		} else {
			ReferenceCountUtil.retain(data);
			out.add(data);
		}
	}

}
