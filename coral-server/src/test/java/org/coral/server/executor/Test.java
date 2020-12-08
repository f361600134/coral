package org.coral.server.executor;

import java.util.concurrent.FutureTask;

public class Test {
	
	public static void main(String[] args) {
		CalTask ct = new CalTask();
        FutureTask<String> ft = new FutureTask<>(ct);
        new Thread(ft).start();
        try {
            System.out.println(ft.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
