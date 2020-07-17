package org.coral.net.core.base;

/**
 * 数据发送载体
 */
public class DataCarrier {

	private byte protocolLen; // 协议长度
	private int protocol; // 协议标识
	private byte[] structure; // 结构体
	//private InetSocketAddress sender; // 发送方

	public static DataCarrier protocolLen2Bytes(int protocol, byte[] structure) {
		DataCarrier carrier = new DataCarrier(2); // 协议标识长度short
		carrier.protocol = protocol;
		carrier.structure = structure;
		return carrier;
	}
	
	public static DataCarrier protocolLen4Bytes(int protocol, byte[] structure) {
		DataCarrier carrier = new DataCarrier(4); // 协议标识长度int
		carrier.protocol = protocol;
		carrier.structure = structure;
		return carrier;
	}
	
	DataCarrier(int protocolFieldLen) {
		this.protocolLen = (byte) protocolFieldLen;
	}
	
	public int getProtocol() {
		return protocol;
	}

	public byte getProtocolLen() {
		return protocolLen;
	}

	public byte[] getStructure() {
		return structure;
	}

//	public void sendAddr(InetSocketAddress sender) {
//		this.sender = sender;
//	}
//	
//	public InetSocketAddress getSender() {
//		return sender;
//	}
	
}
