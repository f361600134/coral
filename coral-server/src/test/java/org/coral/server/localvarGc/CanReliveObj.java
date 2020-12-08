package org.coral.server.localvarGc;

public class CanReliveObj {
	
	public static CanReliveObj obj;
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize obj");
		obj = this;
	}
	
	@Override
	public String toString() {
		return "I am can relive obj";
	}
	
	public static void main(String[] args) throws Exception {
		obj = new CanReliveObj();
		obj = null;
		System.gc();
		Thread.sleep(1000);
		if (obj == null) {
			System.out.println("obj is null");
		}else {
			System.out.println("obj can work");
		}
		System.out.println("The next Gc");
		obj = null;
		
		System.gc();
		Thread.sleep(1000);
		if (obj == null) {
			System.out.println("obj is null");
		}else {
			System.out.println("obj can work");
		}
		
	}

}
