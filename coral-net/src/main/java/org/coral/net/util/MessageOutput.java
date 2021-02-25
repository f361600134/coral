package org.coral.net.util;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.GeneratedMessageLite;

public class MessageOutput {
	
	private GeneratedMessageLite<?, ?> messageLite;
	
	public static MessageOutput create(GeneratedMessageLite<?, ?> messageLite) {
		MessageOutput output = new MessageOutput();
		output.messageLite = messageLite;
		return output;
	}
	
	public static String toString(GeneratedMessageLite<?, ?> messageLite) {
		return JSON.toJSONString(messageLite, ProtobufPropertyFilter.INST);
	}
	
	@Override
	public String toString() {
		return toString(messageLite);
	}
}
