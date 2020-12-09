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
	 * primary key, 对应数据库的主键,唯一主键.可以为null.
	 * 比如排行榜模块, 主键是复合主键.生成的唯一主键就为空
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
	 * 索引列, 对应数据库的索引,仅用于查询,删除
	 * 索引列  = 主键 + 索引列.
	 * 比如,排行榜表rank{serverId, rankType, playerId, value1, value2}
	 * 服务器id,排行榜类型,playerId为复合主键.那么index就为这三个字段.删除数据就需要使用复合主键
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
