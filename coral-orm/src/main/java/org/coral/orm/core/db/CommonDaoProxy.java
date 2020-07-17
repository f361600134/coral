package org.coral.orm.core.db;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.coral.orm.common.OrmConfig;
import org.coral.orm.core.base.BasePo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

//通用dao代理
public class CommonDaoProxy implements IDao{
	
	private static final Logger log = LoggerFactory.getLogger(CommonDaoProxy.class);
	
	//通用dao
	private CommonDao commonDao;
	//缓存
	private Cache<Object, BasePo> cache;
	
	public CommonDaoProxy(BasePo po, JdbcTemplate jdbcTemplate, OrmConfig ormConfig) {
		this.commonDao = new CommonDao(po, jdbcTemplate);
		this.cache = CacheBuilder.newBuilder()
				.expireAfterAccess(ormConfig.getDuration(), TimeUnit.MINUTES)// 在给定时间内没有被读/写访问,则清除
				.maximumSize(ormConfig.getMaximumSize())// 最大容量
				.concurrencyLevel(ormConfig.getConcurrencyLevel()) // 并发等级
				//.removalListener(listener) //移除监听器
				.initialCapacity(ormConfig.getInitialSize())// 初始容量
				.build();
	}

	@Override
	public List<BasePo> select() {
		List<BasePo> basePos = commonDao.select();
		for (BasePo basePo : basePos) {
			//cache.put(basePo.key(), basePo);
			putCache(basePo);
		}
		return basePos;
	}
	
	/**
	 * 默认使用主键,唯一键查找
	 */
	@Override
	public BasePo select(Object[] objs) {
		String key = key(objs);
		BasePo basepo = cache.getIfPresent(key);
		log.info("select by cache, key:{}, po:{}", key, basepo);
		if (basepo == null) {
			basepo = commonDao.select(objs);
			putCache(basepo);
			log.info("select by db, key:{}, po:{}", key, basepo);
		}
		return basepo;
	}
	

	@Override
	public int insert(BasePo po) {
		//cache.put(key(po.idValues()), po);
		putCache(po);
		return commonDao.insert(po);
	}

	@Override
	public int replace(BasePo po) {
		//cache.put(po.key(), po);
		putCache(po);
		return commonDao.replace(po);
	}

	@Override
	public int update(BasePo po) {
		//cache.put(po.key(), po);
		putCache(po);
		return commonDao.update(po);
	}

	@Override
	public int delete(BasePo po) {
		cache.invalidate(po.key());
		return commonDao.delete(po);
	}

	@Override
	public int deleteAll() {
		cache.invalidateAll();
		return commonDao.deleteAll();
	}

	@Override
	public int[] insertBatch(Collection<BasePo> basePos) {
		for (BasePo basePo : basePos) {
			//cache.put(basePo.key(), basePo);
			putCache(basePo);
		}
		return commonDao.insertBatch(basePos);
	}

	@Override
	public int[] deleteBatch(Collection<BasePo> basePos) {
		for (BasePo basePo : basePos) {
			cache.invalidate(basePo.key());
		}
		return commonDao.deleteBatch(basePos);
	}

	/**
	 * 根据键列表, 生成唯一键
	 * @date 2020年6月30日
	 * @param objs
	 * @return
	 */
	private String key(Object[] objs) {
		String key = "";
		for (Object obj : objs) 
			key = key.concat(String.valueOf(obj));
		return key;
	}
	
	/**
	 * 加入缓存
	 * @date 2020年6月30日
	 * @param po
	 */
	private void putCache(BasePo po) {
		String key = key(po.idValues());
		cache.put(key, po);
		log.info("putCache, key:{}, po:{]", key, po);
	}
}
