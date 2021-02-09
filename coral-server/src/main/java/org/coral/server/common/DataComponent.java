package org.coral.server.common;

import java.util.Map;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataComponent {

	private static final Logger log = LoggerFactory.getLogger(DataComponent.class);
	
	@Autowired
	private Map<String, BasePo> basePoMap;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 注册指定的数据处理器
	 * @return
	 */
	@Bean 
	public DataProcessorAsyn processor(){
		log.info("BeanComponent loading...");
		return new DataProcessorAsyn(basePoMap, jdbcTemplate);
	}
	
}
