package org.coral.orm.core.db.dao;

import java.util.Collection;

/**
 * 支持的基础操作接口
 * 
 * 主键, 索引列表,
 * 1.根据主键查询
 * 2.根据索引查询,查询匹配为最左匹配规则, 所以定好索引查询后.
 * 
 * @author Jeremy
 * @date 2020年6月29日
 *
 */
public interface IDao<T> {
	
	/**
	 * 通过默认主键查询出所有数据
	 * @param obj
	 * @return  
	 * @return BasePo  
	 * @date 2020年9月7日下午4:53:28
	 */
	T selectByKey(Object value);
	
	/**
	 * 查询所有
	 * @return  
	 * @return List<BasePo>  
	 * @date 2020年9月7日下午4:53:48
	 */
	Collection<T> selectAll();
	
	/**
	 * 通过指定索引条件, 查询指定数据.此方法默认使用所有索引
	 * @return  
	 * @return List<BasePo>  
	 * @date 2020年9月7日下午4:53:48
	 */
	Collection<T> selectByIndex(Object[] values);
	
	/**
	 * 通过指定索引条件, 查询指定数据.此方法通过组装索引,值实现
	 * @return  
	 * @return List<BasePo>  
	 * @date 2020年9月7日下午4:53:48
	 */
	Collection<T> selectByIndex(String[] props, Object[] values);
	
	/**
	 * 插入一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:28
	 */
	int insert(T t);
	
	/**
	 * 替换一个对象
	 * @param t
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:35
	 */
	int replace(T t);
	
	/**
	 * 更新一个对象
	 * @param t
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:45
	 */
	int update(T t);
	
	/**
	 * 删除一个对象
	 * @param t
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:52
	 */
	int delete(T t);
	
	/**
	 * 删除所有
	 * @return  
	 * @return int返回成功个数
	 * @date 2020年11月7日上午9:13:58
	 */
	int deleteAll();
	
	/**
	 * 批量插入
	 * @param basePos BasePo集合
	 * @return  
	 * @return int[]  
	 * @date 2020年11月7日上午9:14:04
	 */
	int[] insertBatch(Collection<T> ts);
	
	/**
	 * 批量删除
	 * @param basePos BasePo集合
	 * @return  
	 * @return int[]  
	 * @date 2020年11月7日上午9:14:14
	 */
	int[] deleteBatch(Collection<T> ts);
	
	/**
	 * 通过sql语句查询,在以上所有语句不能处理业务时, 使用此方法.
	 * 因为默认sql不缓存, 所以效率比以上操作相比略低.
	 * @param obj
	 * @return  
	 * @return BasePo  
	 * @date 2020年9月7日下午4:53:28
	 */
	Collection<T> selectBySql(String sql);
	
}
