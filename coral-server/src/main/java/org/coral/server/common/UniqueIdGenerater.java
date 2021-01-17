package org.coral.server.common;

import org.coral.server.game.helper.uuid.SnowflakeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UniqueIdGenerater {
	
	private static final Logger logger = LoggerFactory.getLogger(UniqueIdGenerater.class);
	
	@Autowired
	private ServerConfig serverConfig;
	
	/**
	 * 注册UUID Generater
	 * @return
	 */
	@Bean
	public SnowflakeGenerator bean(){
		logger.info("UniqueIdGenerater loading...{}", serverConfig.getServerId());
		return new SnowflakeGenerator(serverConfig.getServerId());
	}

}
