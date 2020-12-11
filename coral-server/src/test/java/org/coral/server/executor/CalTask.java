package org.coral.server.executor;

import java.util.concurrent.Callable;

public class CalTask implements Callable<String>{

	@Override
	public String call() throws Exception {
		return Thread.currentThread().getName() + ": hello!";
	}

}
