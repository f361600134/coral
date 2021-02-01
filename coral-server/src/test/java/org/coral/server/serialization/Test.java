package org.coral.server.serialization;

import org.coral.server.game.module.user.Stu;
import org.coral.server.utils.SerializationUtil;

public class Test {
	
	public static void main(String[] args) {
		Stu stu = new Stu();
		byte[] data = SerializationUtil.serialize(stu);
		Stu stu2 = SerializationUtil.deserialize(data, Stu.class);
		System.out.println(stu2);
	}

}
