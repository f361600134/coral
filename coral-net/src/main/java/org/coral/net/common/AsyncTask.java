package org.coral.net.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

	private static final Logger log = LoggerFactory.getLogger(AsyncTask.class);
	
    @Async("taskAsyncPool")  //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    public void doTask1(int i) throws InterruptedException{
    	log.info(Thread.currentThread().getName()+"Task"+i+" started.");
    }
}