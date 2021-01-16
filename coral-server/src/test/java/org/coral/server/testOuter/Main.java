package org.coral.server.testOuter;

public class Main {
	
	public static void main(String[] args) {
		testSimple();
		System.out.println("=========testBreak===========");
		testBreak();
		System.out.println("=========testOuter===========");
		testOuter();
	}

	
	public static void testSimple() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println("i=" + i + ", j=" + j);
			}
		}
	}
	
	public static void testBreak() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//不要outer，仅仅用break
				if(i == 1) {
					break;
				}
				System.out.println("i=" + i + ", j=" + j);
			}
		}
	}
	
	public static void testOuter() {
		outer:	for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//设置outer的判断条件
				if(i == 1) {
					break outer;
				}
				System.out.println("i=" + i + ", j=" + j);
			}
		}
	}
	
	
}
