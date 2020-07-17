package org.coral.net.util;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

public class ProtobufPropertyFilter implements PropertyPreFilter {
	
	public static ProtobufPropertyFilter INST = new ProtobufPropertyFilter();
	
	public boolean apply(JSONSerializer serializer, Object object, String name) {
		if (name.equals("defaultInstanceForType")) {
			return false;
		}
		if (name.equals("initialized")) {
			return false;
		}
		if (name.equals("parserForType")) {
			return false;
		}
		if (name.equals("serializedSize")) {
			return false;
		}
		if (name.equals("unknownFields")) {
			return false;
		}
		if (name.contains("Bytes")) {
			return false;
		}
		if (name.contains("OrBuilderList")) {
			return false;
		}
		return true;
	}

}
