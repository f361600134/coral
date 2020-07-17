package org.coral.net.core.base;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

/**
 * 会话信息
 */
public class GameSession {
	
	private static final Logger log = LoggerFactory.getLogger(GameSession.class);
	
	/** session id 生产者 */
	private static AtomicInteger generator = new AtomicInteger(1);
	
	/** 玩家id */
	private long playerId;
	/** session id */
	private int id;
	/** socket通道 */
	private Channel channel;
	/** 访问IP */
	private String ip;
	/** 访问端口 */
	private int port;
	/** 用户信息对象 */
	private Object userData ;
	
	
	public GameSession() {
		
	}
	
	public GameSession(Channel ch) {
		this.channel = ch;
		String address = ch.remoteAddress().toString();
		String[] ipAndPort = address.split(":");
		this.ip = ipAndPort[0];
		this.port = Integer.parseInt(ipAndPort[1]);
	}

	public long getPlayerId() {
		return playerId;
	}

	public int getId() {
		return id;
	}

	public Channel getChannel() {
		return channel;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public <T> T getUserData() {
		return (T) userData;
	}
	
	public void initUserData(Object userData) {
		this.userData = userData;
	}
	
	public void push(IProtocol protocol) {
		DataCarrier data = Packet.encode(protocol);
		if (isConnect()) {
//			ByteBuf bytebuf = createBuf(data);
//			BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(bytebuf);
//			channel.writeAndFlush(binaryWebSocketFrame);
			channel.writeAndFlush(data);
			log.info("========push========:{}", data);
		}
	}
	
	private ByteBuf createBuf(DataCarrier obj) {
		ByteBuf byteBuf = Unpooled.buffer(256);
		byteBuf.writeShort(obj.getProtocol());
		byteBuf.writeBytes(obj.getStructure());
		return byteBuf;
	}
	
	public void send(Object message) {
		if (isConnect()) {
			channel.writeAndFlush(message);
		}
	}
	
	public void sendToBuf(Object message) {
		if (isConnect()) {
			channel.write(message);
		}
	}
	
	public void releaseBuf() {
		if (isConnect()) {
			channel.flush();
		}
	}
	
	public boolean isConnect() {
		return channel.isActive();
	}
	
	public void disConnect() {
		channel.close();
	}
	
	public void init(long playerId, Object userData) {
		this.playerId = playerId;
		this.userData = userData;
	}
	
	public String getLocalIp() {
		if (channel != null) {
			InetSocketAddress localAddr = (InetSocketAddress) channel.localAddress();
			return localAddr.getHostName();
		}
		return null;
	}
	
	public boolean isTcpSession() {
		return channel != null && !(channel instanceof DatagramChannel);
	}
	
	
	public void destroy() {
		userData = null;  // 清理用户数据
	}
	
	
	public static GameSession create(Channel ch) {
		GameSession session = new GameSession(ch);
		session.id = Math.abs(generator.getAndIncrement());	// 初始化session id
		return session;
	}
	
	
}
