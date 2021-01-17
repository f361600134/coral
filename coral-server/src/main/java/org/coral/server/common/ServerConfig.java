package org.coral.server.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置
 * @author Jeremy
 * @date 2020年6月29日
 *
 */
@ConfigurationProperties(prefix = "coral.game.server") 
public class ServerConfig {
	
	//服务器ip
	private int serverId;

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	
	
}
