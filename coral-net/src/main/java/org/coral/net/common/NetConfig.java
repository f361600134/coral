package org.coral.net.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 网络配置
 * @author Jeremy
 * @date 2020年6月29日
 *
 */
@ConfigurationProperties(prefix = "coral.network.connection") 
public class NetConfig {
	
	//服务器ip
	private String serverIp;
	//tcp端口
	private int tcpPort;
	//tcp服务启用
	private boolean tcpEnable;
	//websocket端口
	private int webscoketPort;
	//websocket服务启用
	private boolean webscoketEnable;
	
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
	public boolean isTcpEnable() {
		return tcpEnable;
	}
	public void setTcpEnable(boolean tcpEnable) {
		this.tcpEnable = tcpEnable;
	}
	public boolean isWebscoketEnable() {
		return webscoketEnable;
	}
	public void setWebscoketEnable(boolean webscoketEnable) {
		this.webscoketEnable = webscoketEnable;
	}
	
}
