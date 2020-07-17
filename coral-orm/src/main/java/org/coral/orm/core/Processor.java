package org.coral.orm.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.coral.orm.common.OrmConfig;
import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.CommonDao;
import org.coral.orm.core.db.CommonDaoProxy;
import org.coral.orm.core.db.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
////		System.out.println("==========>" + BasePos);
//		System.out.println("=====1=====>" + basePoMap);
//		System.out.println("=====2=====>" + ormConfig.getConcurrencyLevel());
////		System.out.println("=====2=====>" + commonDao);
//		System.out.println("=====2=====>" + jdbcTemplate);
	}
	
	/**
	 * 查询玩家信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public List<BasePo> select(Class<?> clazz) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.select();
	}
	
	/**
	 * 查询玩家信息
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public BasePo select(Class<?> clazz, Object[] obj) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.select(obj);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void insert(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		dao.insert(po);
	}
	
	/**
	 * 批量添加
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public void insertBatch(List<BasePo> basePos) {
		Iterator<BasePo> iter = basePos.iterator();
		BasePo po = iter.hasNext() ? iter.next() : null;
		if (po == null) {
			return;
		}
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		dao.insertBatch(basePos);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void replace(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		dao.replace(po);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void update(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		dao.update(po);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void delete(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		dao.delete(po);
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
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void deleteBatch(List<BasePo> basePos) {
		Iterator<BasePo> iter = basePos.iterator();
		BasePo po = iter.hasNext() ? iter.next() : null;
		if (po == null) {
			return;
		}
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		dao.deleteBatch(basePos);
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		commonDaoMap = new HashMap<String, IDao>();
		if (ormConfig.isEnable()) {//开启缓存, 使用代理
			log.debug("初始化dao组件, 当前缓存为[开启]状态");
			for (String key : basePoMap.keySet()) {
				BasePo po = basePoMap.get(key);
				IDao dao = new CommonDaoProxy(po, jdbcTemplate, ormConfig);
				commonDaoMap.put(key, dao);
			}
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
