package org.coral.orm.core.base;

public interface IBasePo {
	
	public String poName();
	
	/**
	 * primary key, 对应数据库的主键,唯一主键.可以为null.
	 * 比如排行榜模块, 主键是复合主键.生成的唯一主键就为空
	 * @return
	 */
	public Object key();
	
	/**
	 * 数据库主键的列名
	 * @return
	 */
	public String keyColumn();
	
	/**
	 * 缓存二级ID, 如果返回空默认使用key()
	 * @return
	 */
	public String cacheId();
	
	/**
	 * 索引列, 对应数据库的索引,仅用于查询,删除
	 * 索引列  = 主键 + 索引列.
	 * 比如,排行榜表rank{serverId, rankType, playerId, value1, value2}
	 * 服务器id,排行榜类型,playerId为复合主键.那么index就为这三个字段.删除数据就需要使用复合主键
	 * @return
	 */
	public String[] indexs();

	/**
	 * 索引列的值
	 * @return
	 */
	public Object[] indexValues();
	
	/**
	 * 所有属性列
	 * @return
	 */
	public String[] props();

	/**
	 * 所有属性列的值
	 * @return
	 */
	public Object[] propValues();
	
	/**
	 * 存储钱操作
	 */
	public void beforeSave();
	
	/**
	 * 加载后操作
	 */
	public void afterLoad();
	
	///////////////////////////////////////
//	default public void save() {}
//	
//	default public void update(){}
//	
//	default public void delete(){}
	
}
