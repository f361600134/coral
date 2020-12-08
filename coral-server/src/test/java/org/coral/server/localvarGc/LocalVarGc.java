package org.coral.server.localvarGc;

public class LocalVarGc {
	
	/**
	 * 初始化6M堆内存,数组被a引用无法回收
	 *   
	 * @return void  
	 * @date 2020年9月27日下午4:41:24
	 */
	public void LocalVarGc1() {
		byte[] b = new byte[6*1024*1024];
		System.gc();
	}
	
	/**
	 * 初始化6M堆内存,数组被a引用无法回收, 存在于局部变量, a不能替代其局部变量表的字(槽位)
	 * 无法被回收
	 * @return void  
	 * @date 2020年9月27日下午4:41:24
	 */
	public void LocalVarGc1_1() {
		byte[] b = new byte[6*1024*1024];
		int a = 10;
		System.gc();
	}
	
	/**
	 * 调用LocalVarGc1, 方法内GC不会回收, 返回后,LocalVarGc1方法移除栈桢, 对应的局部变量也被销毁, 所以可以被回收
	 *   
	 * @return void  
	 * @date 2020年9月27日下午5:13:37
	 */
	public void LocalVarGc1_2() {
		LocalVarGc1();
		System.gc();
	}
	
	/**
	 * 初始化6M堆内存, 变量a超出了其作用域, 
	 * 但变量存在局部变量表,并指向这个数组,所以无法被回收
	 * @return void  
	 * @date 2020年9月27日下午4:42:10
	 */
	public void LocalVarGc2() {
		{
			byte[] b = new byte[6*1024*1024];
		}
		System.gc();
	}
	
	/**
	 * 初始化6M堆内存, 变量a超出了其作用域失效, 被变量a占用其槽位,变量a被销毁,所以可以被回收
	 * @return void  
	 * @date 2020年9月27日下午4:44:11
	 */
	public void LocalVarGc2_2() {
		{
			byte[] b = new byte[6*1024*1024];
		}
		int a = 10;
		System.gc();
	}
	
	public static void main(String[] args) {
		LocalVarGc vargc = new LocalVarGc();
//		vargc.LocalVarGc1();
//		vargc.LocalVarGc1_1();
		vargc.LocalVarGc1_2();
		
//		vargc.LocalVarGc2();
//		vargc.LocalVarGc2_2();
	}

}
