package org.coral.orm.core.db;

import java.util.Collection;

import org.coral.orm.core.base.BasePo;

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
public interface IDao<T extends BasePo> {
	
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
	
//	/**
//	 * 通过指定条件, 以及值进行查询
//	 * @param objs
//	 * @param values
//	 * @return  
//	 * @return BasePo  
//	 * @date 2020年9月7日下午4:54:12
//	 */
//	Collection<BasePo> select(Object[] objs, Object[] values);
	
	/**
	 * 插入一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:28
	 */
	int insert(T po);
	
	/**
	 * 替换一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:35
	 */
	int replace(T po);
	
	/**
	 * 更新一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:45
	 */
	int update(T po);
	
	/**
	 * 删除一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:52
	 */
	int delete(T po);
	
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
	int[] insertBatch(Collection<T> basePos);
	
	/**
	 * 批量删除
	 * @param basePos BasePo集合
	 * @return  
	 * @return int[]  
	 * @date 2020年11月7日上午9:14:14
	 */
	int[] deleteBatch(Collection<T> basePos);
	
//	/**
//	 * 通过sql语句查询
//	 * @param obj
//	 * @return  
//	 * @return BasePo  
//	 * @date 2020年9月7日下午4:53:28
//	 */
//	T selectBySql(String sql);

}
