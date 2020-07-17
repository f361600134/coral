package org.coral.server;

import org.coral.net.core.HandlerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class NetRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(NetRunner.class);
	
	@Autowired private HandlerProcessor processor;
	
//	@Autowired private ServerConfig serverConfig;
//	
//	@Autowired private EchoServer echoServer;
	
	public void run(String... args) throws Exception {
		try {
//			ChannelFuture future = echoServer.start(serverConfig.getServerIp(), serverConfig.getTcpPort());
//	        Runtime.getRuntime().addShutdownHook(new Thread(){
//	            @Override
//	            public void run() {
//	                echoServer.destroy();
//	            }
//	        });
//	        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
//	        future.channel().closeFuture().syncUninterruptibly();
			
//			processor.invoke(null, 1, null);
		} catch (Exception e) {
			log.error("服务器初始化过程出现异常, 启动失败", e);
		}
	}
	
	
}
