package org.coral.orm.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存配置
 * @author Jeremy
 * @date 2020年6月29日
 *
 */
@ConfigurationProperties(prefix = "coral.persistent.cache") 
public class OrmConfig {
	
//	//是否开启缓存
//	private boolean enable;
//	//缓存时长,单位(minute),超过这个时长会移除缓存
//	private int duration;
//	//模块缓存数量,按照模块缓存
//	private int maximumSize;
//	//初始容量
//	private int initialSize;
//	//并发等级
//	private int concurrencyLevel;
//	
//	public boolean isEnable() {
//		return enable;
//	}
//	public void setEnable(boolean enable) {
//		this.enable = enable;
//	}
//	
//	public int getDuration() {
//		return duration;
//	}
//	public void setDuration(int duration) {
//		this.duration = duration;
//	}
//	public int getMaximumSize() {
//		return maximumSize;
//	}
//	public void setMaximumSize(int maximumSize) {
//		this.maximumSize = maximumSize;
//	}
//	public int getInitialSize() {
//		return initialSize;
//	}
//	public void setInitialSize(int initialSize) {
//		this.initialSize = initialSize;
//	}
//	public int getConcurrencyLevel() {
//		return concurrencyLevel;
//	}
//	public void setConcurrencyLevel(int concurrencyLevel) {
//		this.concurrencyLevel = concurrencyLevel;
//	}
	
}
