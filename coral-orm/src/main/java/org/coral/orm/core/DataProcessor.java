package org.coral.orm.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.CommonDao;
import org.coral.orm.core.db.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Maps;

public class DataProcessor implements IDataProcess{
	
	private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);
	
	/**
	 * key: class name, 类名
	 * value: dao 存储dao
	 */
	private Map<String, IDao<?>> commonDaoMap;
	
	@SuppressWarnings("unchecked")
	public <T extends BasePo> IDao<T> getCommonDao(String name) {
		if (StringUtils.isBlank(name)) {
			throw new NullPointerException("name is can not be null:"+name);
		}
		return (IDao<T>)commonDaoMap.get(name);
	}
	
	public <T extends BasePo> IDao<T> getCommonDao(Class<T> clazz) {
		if (clazz == null) {
			throw new NullPointerException("clazz is can not be null:"+clazz);
		}
		String name = clazz.getSimpleName().toLowerCase();
		return getCommonDao(name);
	}
	
	public <T extends BasePo> IDao<T> getCommonDao(T clazz) {
		if (clazz == null) {
			throw new NullPointerException("clazz is can not be null:"+clazz);
		}
		return getCommonDao(clazz.poName());
	}
	
	/**
	 * 构造方法
	 * @param basePoMap
	 * @param jdbcTemplate
	 */
	public DataProcessor(Map<String, BasePo> basePoMap, JdbcTemplate jdbcTemplate) {
		this.commonDaoMap = Maps.newHashMap();
		for (String key : basePoMap.keySet()) {
			BasePo po = basePoMap.get(key);
			IDao<?> dao = new CommonDao<>(po, jdbcTemplate);
			commonDaoMap.put(key, dao);
		}
	}
	
	/**
	 * 查询信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public <T extends BasePo> List<T> selectAll(Class<T> clazz) {
		IDao<T> dao = getCommonDao(clazz);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the clazz:"+clazz);
		}
		return (List<T>) dao.selectAll();
	}
	
	/**
	 * 查询信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public <T extends BasePo> T selectByPrimaryKey(Class<T> clazz, Object value) {
		IDao<T> dao = getCommonDao(clazz);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the clazz:"+clazz);
		}
		return dao.selectByKey(value);
	}
	
	/**
	 * 查询玩家信息, 通过默认索引,这种方式直接获取缓存的sql进行查询
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public <T extends BasePo> List<T> selectByIndex(Class<T> clazz, Object[] objs) {
		IDao<T> dao = getCommonDao(clazz);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the clazz:"+clazz);
		}
		return (List<T>)dao.selectByIndex(objs);
	}
	
	/**
	 * 查询玩家信息, 通过默认索引,这种方式直接获取缓存的sql进行查询
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public <T extends BasePo> T selectOneByIndex(Class<T> clazz, Object[] objs) {
		List<T> ret = this.selectByIndex(clazz, objs);
		if (ret == null || ret.size() <= 0) {
			return null;
		}
		return ret.get(0);
	}
	
	/**
	 * 查询玩家信息, 通过制定字段,这种方式只能通过sql组装进行查询
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public <T extends BasePo> List<T> selectByIndex(Class<T> clazz, String[] props, Object[] objs) {
		IDao<T> dao = getCommonDao(clazz);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the props:"+props);
		}
		return (List<T>)dao.selectByIndex(props, objs);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int insert(T po) {
		IDao<T> dao = getCommonDao(po);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the Pojo:"+po);
		}
		return dao.insert(po);
	}
	
	/**
	 * 批量添加
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public <T extends BasePo> void insertBatch(Collection<T> basePos) {
		Map<String, List<T>> map = splitData(basePos);
		IDao<T> dao = null;
		for (String name : map.keySet()) {
			dao = getCommonDao(name);
			dao.insertBatch(map.get(name));
		}
		map = null;
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int replace(T po) {
		IDao<T> dao = getCommonDao(po);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the Pojo:"+po);
		}
		return dao.replace(po);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int update(T po) {
		IDao<T> dao = getCommonDao(po);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the Pojo:"+po);
		}
		return dao.update(po);
	}
	
	/**
	 * 批量修改玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int updateBatch(Collection<T> pos) {
		pos.forEach(po -> {
			getCommonDao(po).update(po);
		});
		return 0;
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int delete(T po) {
		IDao<T> dao = getCommonDao(po);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the Pojo:"+po);
		}
		return dao.delete(po);
	}
	
	/**
	 * 删除所有
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> void deleteAll(Class<T> clazz) {
		IDao<T> dao = getCommonDao(clazz);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the clazz:"+clazz);
		}
		dao.deleteAll();
	}
	
	/**
	 * 批量删除
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public <T extends BasePo> void deleteBatch(List<T> basePos) {
		Map<String, List<T>> map = splitData(basePos);
		IDao<T> dao = null;
		for (String name : map.keySet()) {
			dao = getCommonDao(name);
			dao.deleteBatch(map.get(name));
		}
		map = null;
	}
	
	/**
	 * 	通过指定sql查询. 虽说指定了sql语句,但需要通过clazz获取处理dao
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public <T extends BasePo> List<T> selectBySql(Class<T> clazz, String sql) {
		IDao<T> dao = getCommonDao(clazz);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the clazz:"+clazz);
		}
		return (List<T>)dao.selectBySql(sql);
	}
	
	/**
	 * 数据分类
	 * @date 2020年8月5日
	 * @param basePos
	 * @return
	 */
	protected <T extends BasePo> Map<String, List<T>> splitData(Collection<T> basePos) {
		//数据分类
		Map<String, List<T>> map = new HashMap<String, List<T>>();
		for (T po : basePos) {
			String name = po.poName();
			List<T> list = map.get(name);
			if (list == null) {
				list = new ArrayList<T>();
				map.put(name, list);
			}
			list.add(po);
		}
		return map;
	}
	
}
