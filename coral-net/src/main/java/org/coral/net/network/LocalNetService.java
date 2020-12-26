package org.coral.net.network;

import javax.annotation.PreDestroy;

import org.coral.net.common.NetConfig;
import org.coral.net.core.base.IServerHandler;
import org.coral.net.network.bootstrap.IServer;
import org.coral.net.network.tcp.TcpServerStarter;
import org.coral.net.network.websocket.WebSocketServerStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 本地网络服务
 */
@Component
public class LocalNetService implements InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(LocalNetService.class);
	
	@Autowired private NetConfig config;
	
	@Autowired private IServerHandler serverHandler;
	
	private IServer tcpServer;
	private IServer websocketServer;
	
	public LocalNetService() {}
	
	/**
	 * 启动
	 * @date 2020年7月9日
	 * @throws Exception
	 */
	public void startup() throws Exception {
		if (tcpServer != null) {
			tcpServer.startServer();
		}
		if (websocketServer != null) {
			websocketServer.startServer();
		}
	}
	
	/**
	 * 关闭
	 * @date 2020年7月9日
	 * @throws Exception
	 */
	public void shutdown() throws Exception {
		if (tcpServer != null) {
			tcpServer.stopServer();
		}
		if (websocketServer != null) {
			websocketServer.stopServer();
		}
	}
	
//	private void prepareShutdownThread() {
//		Runtime.getRuntime().addShutdownHook(new ShutdownTask());
//	}
//	
//	
//	class ShutdownTask extends Thread {
//
//		@Override
//		public void run() {
//			try {
////				ServerHandler.this.serverStatus(false);
////				PlayerManager pManager = PlayerManager.instance();
////				pManager.kickAllPlayers();
////				
////				GameScheduler schd = GameContext.instance().getSched();
////				schd.pauseJob(CommonJobStart.AUTO_SAVE_JOB_NAME);
////				
////				IData dataSync = GameContext.instance().gameData();
////				Response resp0 = dataSync.executeBatchSave();
////				Response resp1 = dataSync.executeBatchSave();
//				
////				if (resp0 != null) 
////					resp0.sync(5000L);
////				if (resp1 != null) 
////					resp1.sync(5000L); 
//			} catch (Exception e) {
//				log.error("关服处理出现异常", e);
//			}
//		}
//		
//	}
	
	@PreDestroy
	public void preDestroy() {
		try {
			shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		if (config.isTcpEnable()) {
			tcpServer = new TcpServerStarter(serverHandler, config.getServerIp(), config.getTcpPort());
//			tcpServer.startServer();
		}
		if (config.isWebscoketEnable()){
			websocketServer = new WebSocketServerStarter(serverHandler, config.getServerIp(), config.getWebscoketPort());
//			websocketServer.startServer();
		}
		startup();
	}

}
