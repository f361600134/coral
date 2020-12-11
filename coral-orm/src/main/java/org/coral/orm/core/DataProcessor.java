package org.coral.orm.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.CommonDao;
import org.coral.orm.core.db.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Maps;

public class DataProcessor{
	
	private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);
	
	private Map<String, IDao> commonDaoMap;
	
	public IDao getCommonDao(String name) {
		return commonDaoMap.get(name);
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
			IDao dao = new CommonDao(po, jdbcTemplate);
			commonDaoMap.put(key, dao);
		}
		log.info("Processor init===============");
	}
	
	public void print() {
	}
	
	/**
	 * 查询信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> selectAll(Class<?> clazz) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return (List<T>) dao.selectAll();
	}
	
	/**
	 * 查询信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T selectByPrimaryKey(Class<?> clazz, Object value) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		if (dao == null) {
			throw new NullPointerException("Can not find dao by the name:"+name);
		}
		return (T)dao.selectByKey(value);
	}
	
	/**
	 * 查询玩家信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> selectByIndex(Class<?> clazz, Object[] props, Object[] objs) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return (List<T>)dao.selectByIndex(props, objs);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public int insert(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.insert(po);
	}
	
	/**
	 * 批量添加
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public void insertBatch(List<BasePo> basePos) {
		Map<String, List<BasePo>> map = splitData(basePos);
		IDao dao = null;
		for (String name : map.keySet()) {
			dao = commonDaoMap.get(name);
			dao.insertBatch(map.get(name));
		}
		map = null;
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public int replace(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.replace(po);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public int update(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.update(po);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public int delete(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.delete(po);
	}
	
	/**
	 * 删除所有
	 * @date 2020年6月30日
	 * @param po
	 */
	public void deleteAll(Class<?> clazz) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		dao.deleteAll();
	}
	
	/**
	 * 批量删除
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public void deleteBatch(List<BasePo> basePos) {
		Map<String, List<BasePo>> map = splitData(basePos);
		IDao dao = null;
		for (String name : map.keySet()) {
			dao = commonDaoMap.get(name);
			dao.deleteBatch(map.get(name));
		}
		map = null;
	}
	
	/**
	 * 数据分类
	 * @date 2020年8月5日
	 * @param basePos
	 * @return
	 */
	protected Map<String, List<BasePo>> splitData(List<BasePo> basePos) {
		//数据分类
		Map<String, List<BasePo>> map = new HashMap<String, List<BasePo>>();
		for (BasePo basePo : basePos) {
			String name = basePo.getClass().getSimpleName().toLowerCase();
			List<BasePo> list = map.get(name);
			if (list == null) {
				list = new ArrayList<BasePo>();
				map.put(name, list);
			}
			list.add(basePo);
		}
		return map;
	}
	
}
