package org.coral.orm.core.db;

import java.util.Collection;
import java.util.List;

import org.coral.orm.core.base.BasePo;

/**
 * 支持的基础操作接口
 * @author Jeremy
 * @date 2020年6月29日
 *
 */
public interface IDao {
	
	BasePo select(Object[] obj);
	
	List<BasePo> select();
	
	int insert(BasePo po);
	
	int replace(BasePo po);
	
	int update(BasePo po);
	
	int delete(BasePo po);
	
	int deleteAll();
	
	int[] insertBatch(Collection<BasePo> basePos);
	
	int[] deleteBatch(Collection<BasePo> basePos);

}
