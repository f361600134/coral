package org.coral.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class ProtoStuffTest {

	public static void main(String[] args) {
		Divide divide = new Divide();
		System.err.println(divide);
		Schema divideSchema = RuntimeSchema.getSchema(Divide.class);
		LinkedBuffer buffer = LinkedBuffer.allocate(512);
		ByteArrayOutputStream byteArrayOutputStream;
		byte[] bytes = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			ProtostuffIOUtil.writeTo(byteArrayOutputStream, divide, divideSchema, buffer);
			bytes = byteArrayOutputStream.toByteArray();
			System.err.println(String.format("length : %d  value %s", bytes.length, new String(bytes)));
			for (byte b : bytes) {
				System.err.print("[" + (char) b + "]");
				System.err.print(b + " ");
			}
			System.err.println();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Lily lily = new Lily();
		ProtostuffIOUtil.mergeFrom(bytes, lily, divideSchema);
		System.err.println(lily);
	}

	public static class Divide {
		String name = "divide";
		String age = "a";
		String age1 = "b";
		String age2 = "c";
		String age3 = "d";
		String age4 = "e";
		
		public Divide() {}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getAge1() {
			return age1;
		}
		public void setAge1(String age1) {
			this.age1 = age1;
		}
		public String getAge2() {
			return age2;
		}
		public void setAge2(String age2) {
			this.age2 = age2;
		}
		public String getAge3() {
			return age3;
		}
		public void setAge3(String age3) {
			this.age3 = age3;
		}
		public String getAge4() {
			return age4;
		}
		public void setAge4(String age4) {
			this.age4 = age4;
		}
		
		
	}

	public static class Lily {
		int age;
		String name;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
