package org.coral.orm.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.coral.orm.common.OrmConfig;
import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.command.CommandDelete;
import org.coral.orm.core.command.CommandDeleteAll;
import org.coral.orm.core.command.CommandDeleteBatch;
import org.coral.orm.core.command.CommandInsert;
import org.coral.orm.core.command.CommandInsertBatch;
import org.coral.orm.core.command.CommandReplace;
import org.coral.orm.core.command.CommandUpdate;
import org.coral.orm.core.command.Executable;
import org.coral.orm.core.db.CommonDao;
import org.coral.orm.core.db.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 	对于查询模块需要立即返回数据信息,所以不需要异步处理
 * 对于修改模块, 无需立即返回结果, 所以异步方式处理
 * @author Jeremy
 * @date 2020年7月31日
 *
 */
@Component
public class ProcessorProxy implements InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(ProcessorProxy.class);
	
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
	
	private Queue<Executable<?>> syncQueue;
	
	private ScheduledExecutorService scheduledThreadPool;
	
	/**
	 * 查询所有
	 * @date 2020年6月30日
	 * @param clazz
	 * @return
	 */
	public Collection<BasePo> selectAll(Class<?> clazz) {
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
	public void insert(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		syncQueue.add(CommandInsert.create(po, dao));
	}
	
//	/**
//	 * 批量添加
//	 * @date 2020年6月30日
//	 * @param basePos
//	 */
//	public void insertBatch(List<BasePo> basePos) {
//		Iterator<BasePo> iter = basePos.iterator();
//		BasePo po = iter.hasNext() ? iter.next() : null;
//		if (po == null) {
//			return;
//		}
//		String name = po.getClass().getSimpleName().toLowerCase();
//		IDao dao = commonDaoMap.get(name);
//		syncQueue.add(CommandInsertBatch.create(basePos, dao));
//	}
	
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
	public void replace(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		syncQueue.add(CommandReplace.create(po, dao));
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void update(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		syncQueue.add(CommandUpdate.create(po, dao));
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void delete(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		syncQueue.add(CommandDelete.create(po, dao));
	}
	
	/**
	 * 删除所有
	 * @date 2020年6月30日
	 * @param po
	 */
	public void deleteAll(Class<?> clazz) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		syncQueue.add(CommandDeleteAll.create(null, dao));
	}
	
//	/**
//	 * 添加玩家信息
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
//		syncQueue.add(CommandDeleteBatch.create(basePos, dao));
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
		syncQueue = new ConcurrentLinkedQueue<Executable<?>>();
		this.scheduledThreadPool = Executors.newScheduledThreadPool(1);
		this.scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				Executable<?> executor = syncQueue.poll();
				//log.error("start to executor:{}", executor);
				while (executor != null) {
					try {
						executor.execute();
					} catch (Exception e) {
						log.error("executor error, executor:{}", executor);
						log.error("executor error, e:{}", e);
					}
					executor = syncQueue.poll();
				}
			}
		}, 1, 3, TimeUnit.SECONDS);
		
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
