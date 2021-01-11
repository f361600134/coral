package org.coral.orm.core;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.command.CommandDelete;
import org.coral.orm.core.command.CommandDeleteAll;
import org.coral.orm.core.command.CommandDeleteBatch;
import org.coral.orm.core.command.CommandInsert;
import org.coral.orm.core.command.CommandInsertBatch;
import org.coral.orm.core.command.CommandReplace;
import org.coral.orm.core.command.CommandUpdate;
import org.coral.orm.core.command.Executable;
import org.coral.orm.core.db.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 	对于查询模块需要立即返回数据信息,所以不需要异步处理
 * 对于修改模块, 无需立即返回结果, 所以异步方式处理
 * @author Jeremy
 * @date 2020年7月31日
 *
 */
public class DataProcessorAsyn extends DataProcessor{
	
	private static final Logger log = LoggerFactory.getLogger(DataProcessorAsyn.class);
	
	/**
	 * 任务队列
	 */
	private Queue<Executable<?>> syncQueue;
	
	/**
	 * 定时任务线程池
	 */
	private ScheduledExecutorService scheduledThreadPool;
	
	public DataProcessorAsyn(Map<String, BasePo> basePoMap, JdbcTemplate jdbcTemplate){
		super(basePoMap, jdbcTemplate);
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
		}, 1, 1, TimeUnit.MINUTES);
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int insert(T po) {
		IDao<T> dao = super.getCommonDao(po);
		syncQueue.add(CommandInsert.create(po, dao));
		return 0;
	}
	
	/**
	 * 批量添加
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public<T extends BasePo> void insertBatch(List<T> basePos) {
		Map<String, List<T>> map = splitData(basePos);
		IDao<T> dao = null;
		for (String name : map.keySet()) {
			dao = super.getCommonDao(name);
			syncQueue.add(CommandInsertBatch.create(map.get(name), dao));
		}
		map = null;
	}
	
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int replace(T po) {
		IDao<T> dao = super.getCommonDao(po);
		syncQueue.add(CommandReplace.create(po, dao));
		return 0;
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int update(T po) {
		IDao<T> dao = super.getCommonDao(po);
		syncQueue.add(CommandUpdate.create(po, dao));
		return 0;
	}
	
	/**
	 * 添加玩家信息
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> int delete(T po) {
		IDao<T> dao = super.getCommonDao(po);
		syncQueue.add(CommandDelete.create(po, dao));
		return 0;
	}
	
	/**
	 * 删除所有
	 * @date 2020年6月30日
	 * @param po
	 */
	public <T extends BasePo> void deleteAll(Class<T> clazz) {
		IDao<T> dao = super.getCommonDao(clazz);
		syncQueue.add(CommandDeleteAll.create(null, dao));
	}
	
	/**
	 * 批量删除
	 * @date 2020年6月30日
	 * @param basePos
	 */
	public <T extends BasePo> void deleteBatch(List<T> basePos) {
		Map<String, List<T>> map = super.splitData(basePos);
		IDao<T> dao = null;
		for (String name : map.keySet()) {
			dao = super.getCommonDao(name);
			syncQueue.add(CommandDeleteBatch.create(map.get(name), dao));
		}
		map = null;
	}
	
}
