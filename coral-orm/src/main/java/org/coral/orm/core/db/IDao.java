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
public interface IDao {
	
	/**
	 * 查询所有
	 * @return  
	 * @return List<BasePo>  
	 * @date 2020年9月7日下午4:53:48
	 */
	Collection<BasePo> selectAll();
	
	/**
	 * 通过默认主键查询出所有数据
	 * @param obj
	 * @return  
	 * @return BasePo  
	 * @date 2020年9月7日下午4:53:28
	 */
	BasePo selectByKey(Object value);
	
	
	/**
	 * 通过指定索引条件, 查询指定数据.
	 * @return  
	 * @return List<BasePo>  
	 * @date 2020年9月7日下午4:53:48
	 */
	Collection<BasePo> selectByIndex(Object[] values);
	
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
	int insert(BasePo po);
	
	/**
	 * 替换一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:35
	 */
	int replace(BasePo po);
	
	/**
	 * 更新一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:45
	 */
	int update(BasePo po);
	
	/**
	 * 删除一个对象
	 * @param po
	 * @return  
	 * @return int  返回成功个数
	 * @date 2020年11月7日上午9:13:52
	 */
	int delete(BasePo po);
	
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
	int[] insertBatch(Collection<BasePo> basePos);
	
	/**
	 * 批量删除
	 * @param basePos BasePo集合
	 * @return  
	 * @return int[]  
	 * @date 2020年11月7日上午9:14:14
	 */
	int[] deleteBatch(Collection<BasePo> basePos);

}
