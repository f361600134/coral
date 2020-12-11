package org.coral.server.executor.aysn;

import org.coral.net.common.AsyncTask;
import org.coral.net.network.LocalNetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AysnTest extends TestCase{ 

	private static final Logger log = LoggerFactory.getLogger(LocalNetService.class);
	
	@Autowired
	private AsyncTask asyncTask;
	
	@Test public void contextLoads() {
		AsyncTaskTest();
	}

	public void AsyncTaskTest()  {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(1000);
				asyncTask.doTask1(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("All tasks finished.");
	}
}
