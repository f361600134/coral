package org.coral.server.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置
 * @author Jeremy
 * @date 2020年6月29日
 *
 */
//@ConfigurationProperties(prefix = "coral.game.net") 
public class ServerConfig {
	
	//是否开启缓存
	private String serverIp;
	//缓存时长,单位(minute),超过这个时长会移除缓存
	private int tcpPort;
	//模块缓存数量,按照模块缓存
	private int webscoketPort;
	
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public int getTcpPort() {
		return tcpPort;
	}
	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}
	public int getWebscoketPort() {
		return webscoketPort;
	}
	public void setWebscoketPort(int webscoketPort) {
		this.webscoketPort = webscoketPort;
	}
	
}
