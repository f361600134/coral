package org.coral.server;

import java.math.BigInteger;

public class bitTest {

	public static void main(String[] args) {
		genUniqueId2();
	}
	
	public static void snow() {
		long senderId = 1001, targetId = 2002;
		// 私聊,通过发送方的玩家id,和目标玩家id,组装成唯一id
		if (senderId < targetId) {
			senderId ^= targetId;
			targetId ^= senderId;
			senderId ^= targetId;
		}
		System.out.println("senderId="+senderId+", targetId:"+targetId);
		BigInteger bigInteger = BigInteger.valueOf(senderId).shiftLeft(32).add(BigInteger.valueOf(targetId));
		long primaryKey = bigInteger.longValue();
		System.out.println(bigInteger);
		System.out.println(bigInteger.longValue());
	}
	
	
	/**
	 * 	配对公式
	 * π(a,b)=1/2(a+b)(a+b+1)+b.
	 * 
	 * @return
	 */
	public static void genUniqueId() {
		int a = 2;
		int b = 30;
		int num = (int)(0.5*(a+b)*(a+b+1)+b);
		System.out.println(num);
	}
	
	/**
	 * 
	 * @return
	 */
	public static void genUniqueId2() {
		long senderId = 2000000001, targetId = 2000000002;
		long uniqueId = senderId << 32;
//		System.out.println(uniqueId);
		
		uniqueId += targetId ;
		System.out.println(uniqueId);
		
		
		BigInteger bigInteger = BigInteger.valueOf(senderId).shiftLeft(32).add(BigInteger.valueOf(targetId));
		System.out.println(bigInteger.longValue());
		
//		uniqueId = 2000000003;
//		uniqueId = uniqueId << 32;
//		System.out.println(uniqueId);
	}
	
	

}
