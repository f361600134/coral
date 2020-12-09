package org.coral.orm.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.coral.orm.common.OrmConfig;
import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.CommonDao;
import org.coral.orm.core.db.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

@Component
public class Processor implements InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(Processor.class);
	
	@Autowired
	private OrmConfig ormConfig;
	/**
	 * key:po.name
	 * value:po
	 */
	@Autowired
	private Map<String, BasePo> basePoMap;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Map<String, IDao> commonDaoMap;
	
	public void print() {
	}
	
	/**
	 * 查询信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public Collection<BasePo> selectAll(Class<?> clazz, Object[] obj) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.selectAll();
	}
	
	/**
	 * 查询信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public BasePo selectByPrimaryKey(Class<?> clazz, Object value) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.selectByKey(value);
	}
	
	/**
	 * 查询玩家信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public Collection<BasePo> selectByIndex(Class<?> clazz, Object[] props, Object[] objs) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.selectByIndex(props, objs);
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
	
//	/**
//	 * 批量删除玩家信息
//	 * @date 2020年6月30日
//	 * @param po
//	 */
//	public void deleteBatch(List<BasePo> basePos) {
//		Iterator<BasePo> iter = basePos.iterator();
//		BasePo po = iter.hasNext() ? iter.next() : null;
//		if (po == null) {
//			return;
//		}
//		String name = po.getClass().getSimpleName().toLowerCase();
//		IDao dao = commonDaoMap.get(name);
//		dao.deleteBatch(basePos);
//	}
	
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
	private Map<String, List<BasePo>> splitData(List<BasePo> basePos) {
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
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		commonDaoMap = new HashMap<String, IDao>();
		if (ormConfig.isEnable()) {//开启缓存, 使用代理
//			log.debug("初始化dao组件, 当前缓存为[开启]状态");
//			for (String key : basePoMap.keySet()) {
//				BasePo po = basePoMap.get(key);
//				IDao dao = new CommonDaoProxy(po, jdbcTemplate, ormConfig);
//				commonDaoMap.put(key, dao);
//			}
		}else {//不开启缓存,直接加载dao
			log.debug("初始化dao组件, 当前缓存为[关闭]状态");
			for (String key : basePoMap.keySet()) {
				BasePo po = basePoMap.get(key);
				IDao dao = new CommonDao(po, jdbcTemplate);
				commonDaoMap.put(key, dao);
			}
		}
	}

}
