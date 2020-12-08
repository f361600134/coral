package org.coral.orm.core;

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
import org.coral.orm.core.db.CommonDaoProxy;
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
	 * @param clazz
	 * @return
	 */
	public BasePo select(Class<?> clazz, Object[] obj) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.selectByPrimaryKey(obj);
	}
	
	/**
	 * 查询玩家信息
	 * @param clazz
	 * @return
	 */
	public BasePo select(Class<?> clazz, Object[] props, Object[] objs) {
		String name = clazz.getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
		return dao.select(props, objs);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void insert(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
//		dao.insert(po);
		syncQueue.add(CommandInsert.create(po, dao));
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
		//dao.insertBatch(basePos);
		syncQueue.add(CommandInsertBatch.create(po, dao));
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public void replace(BasePo po) {
		String name = po.getClass().getSimpleName().toLowerCase();
		IDao dao = commonDaoMap.get(name);
//		int count = dao.replace(po);
//		System.out.println(count);
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
		//dao.update(po);
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
		//dao.delete(po);
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
		//dao.deleteAll();
		syncQueue.add(CommandDeleteAll.create(null, dao));
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
		//dao.deleteBatch(basePos);
		syncQueue.add(CommandDeleteBatch.create(basePos, dao));
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		syncQueue = new ConcurrentLinkedQueue<Executable<?>>();
		this.scheduledThreadPool = Executors.newScheduledThreadPool(1);
		this.scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				Executable<?> executor = syncQueue.poll();
				log.error("start to executor:{}", executor);
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
		}, 1, 1, TimeUnit.MINUTES);
		
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
