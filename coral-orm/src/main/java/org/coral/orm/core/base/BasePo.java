package org.coral.orm.core.base;

import java.io.Serializable;

/**
 * 基础持久化对象父类
 */
public abstract class BasePo implements Serializable {
	
	/**
	 * @date 2020年7月16日
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * primary key, 对应数据库的第一主键(玩家ID)
	 * @return
	 */
	abstract public Object key();
	
	/**
	 * 缓存二级ID, 如果返回空默认使用key()
	 * @return
	 */
	abstract public Object cacheId();
	
	/**
	 * 数据库第一主键的列名
	 * @return
	 */
	abstract public String keyColumn();
	
	
	/**
	 * id的列, 对应数据库的主键和索引
	 * @return
	 */
	abstract public String[] ids();

	/**
	 * 所有id列的值
	 * @return
	 */
	abstract public Object[] idValues();
	
	/**
	 * 所有属性列
	 * @return
	 */
	abstract public String[] props();

	/**
	 * 所有属性列的值
	 * @return
	 */
	abstract public Object[] propValues();
	
	
}
