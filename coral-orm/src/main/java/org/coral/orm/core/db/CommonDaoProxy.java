package org.coral.orm.core.db;

//通用dao代理
/**
 * 1. 查询所有
 * 2. 根据条件查询列表
 * 3. 根据条件查询一条数据
 */
//public class CommonDaoProxy implements IDao{
//	
//	private static final Logger log = LoggerFactory.getLogger(CommonDaoProxy.class);
	
//	//通用dao
//	private CommonDao commonDao;
//	//缓存
//	private Cache<Object, BasePo> cache;
//	
//	/**
//	 * 一对多缓存
//	 * key: 查询条件值， select id,name,serverId from table where serverId = 1; key表示serverId
//	 * value: Set<id> 对应cache的key
//	 */
//	private Multimap<Object, Object> fieldMap;
//	
//	public CommonDaoProxy(BasePo po, JdbcTemplate jdbcTemplate, OrmConfig ormConfig) {
//		this.commonDao = new CommonDao(po, jdbcTemplate);
//		this.fieldMap = HashMultimap.create();
//		this.cache = CacheBuilder.newBuilder()
//				.expireAfterAccess(ormConfig.getDuration(), TimeUnit.MINUTES)// 在给定时间内没有被读/写访问,则清除
//				.maximumSize(ormConfig.getMaximumSize())// 最大容量
//				.concurrencyLevel(ormConfig.getConcurrencyLevel()) // 并发等级
//				//.removalListener(listener) //移除监听器
//				.initialCapacity(ormConfig.getInitialSize())// 初始容量
//				.build();
//	}
//
//	/**
//	 * 默认使用主键,唯一键查找
//	 */
//	@Override
//	public BasePo selectByPrimaryKey(Object[] objs) {
//		String key = key(objs);
//		BasePo basepo = cache.getIfPresent(key);
//		log.info("select by cache, key:{}, po:{}", key, basepo);
//		if (basepo == null) {
//			basepo = commonDao.selectByPrimaryKey(objs);
//			putCache(basepo);
//			log.info("select by db, key:{}, po:{}", key, basepo);
//		}
//		return basepo;
//	}
//	
//	/**
//	 */
//	@Override
//	public List<BasePo> select() {
//		List<BasePo> basePos = commonDao.select();
//		for (BasePo basePo : basePos) {
//			//cache.put(basePo.key(), basePo);
//			putCache(basePo);
//		}
//		return basePos;
//	}
//	
//
//	@Override
//	public int insert(BasePo po) {
//		putCache(po);
//		return commonDao.insert(po);
//	}
//
//	@Override
//	public int replace(BasePo po) {
//		putCache(po);
//		return commonDao.replace(po);
//	}
//
//	@Override
//	public int update(BasePo po) {
//		putCache(po);
//		return commonDao.update(po);
//	}
//
//	@Override
//	public int delete(BasePo po) {
//		cache.invalidate(po.key());
//		return commonDao.delete(po);
//	}
//
//	@Override
//	public int deleteAll() {
//		cache.invalidateAll();
//		return commonDao.deleteAll();
//	}
//
//	@Override
//	public int[] insertBatch(Collection<BasePo> basePos) {
//		for (BasePo basePo : basePos) {
//			putCache(basePo);
//		}
//		return commonDao.insertBatch(basePos);
//	}
//
//	@Override
//	public int[] deleteBatch(Collection<BasePo> basePos) {
//		for (BasePo basePo : basePos) {
//			cache.invalidate(basePo.key());
//		}
//		return commonDao.deleteBatch(basePos);
//	}
//
//	/**
//	 * 根据键列表, 生成唯一键
//	 * @date 2020年6月30日
//	 * @param objs
//	 * @return
//	 */
//	private String key(Object[] objs) {
//		String key = "";
//		for (Object obj : objs) 
//			key = key.concat(String.valueOf(obj));
//		return key;
//	}
//	
//	/**
//	 * 加入缓存
//	 * @date 2020年6月30日
//	 * @param po
//	 */
//	private void putCache(BasePo po) {
//		String key = key(po.idValues());
//		cache.put(key, po);
//		log.info("putCache, key:{}, po:{]", key, po);
//	}
//	
//	/**
//	 * 加入缓存
//	 * @date 2020年6月30日
//	 * @param po
//	 */
//	private void putCache(Collection<BasePo> pos) {
//		pos.stream().forEach(po -> putCache(po));
//	}
//
//	/**
//	 * 根据条件查询
//	 */
//	@Override
//	public Collection<BasePo> select(Object[] props, Object[] objs) {
//		Collection<BasePo> rets = null;
//		Collection<Object> realKeys = fieldMap.get(key(objs));
//		if (realKeys != null) {
//			rets = Lists.newArrayList();
//			rets.addAll(cache.getAllPresent(realKeys).values());
//		}else {
//			rets = commonDao.select(props, objs);
//			putCache(rets);
//		}
//		return rets;
//	}
//
//	@Override
//	public Collection<BasePo> selectByIndex(Object[] props) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	public static void main(String[] args) {
//		
////		String key = "thisisaprimarykey";
////		int code = key.hashCode();
////		System.out.println(code);
////		int id = 10001;
////		int value = StateUtils.addState(code, id);
//		
//		
////		System.out.println(value);
////		for (int i = 1; i < 1000; i++) {
////			boolean bool = StateUtils.check(value, i);
////			if (bool) {
////				System.out.println("=========>i:"+i);
////			}
////		}
//	}
//}
