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
	 * primary key, 对应数据库的主键
	 * @return
	 */
	abstract public Object key();
	
	/**
	 * 数据库主键的列名
	 * @return
	 */
	abstract public String keyColumn();
	
	/**
	 * 缓存二级ID, 如果返回空默认使用key()
	 * @return
	 */
	abstract public Object cacheId();
	
	/**
	 * 索引列, 对应数据库的索引
	 * @return
	 */
	abstract public String[] indexs();

	/**
	 * 索引列的值
	 * @return
	 */
	abstract public Object[] indexValues();
	
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
