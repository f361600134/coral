package org.coral.orm.core.db.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.base.PoMapper;
import org.coral.orm.core.base.SQLGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommonDao<T extends BasePo> implements IDao<T>{
	
	private static final Logger log = LoggerFactory.getLogger(CommonDao.class);
	
	/**
	 * 	基于spring提供的JdbcTemplate实现.
	 *	仅仅需要关注业务本身 不需要关注事务,连接释放等操作
	 */
	private final JdbcTemplate jdbcTemplate;
	
	/**
	 * po.sql映射, 对注册的POJO生成sql隐射,操作时直接通过映射的sql进行操作
	 */
	private final PoMapper<T> poMapper;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CommonDao(T po, JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.poMapper = new PoMapper(po.getClass());
	}
	
	/**
	 * 查询所有
	* @see org.coral.orm.core.db.dao.IDao#selectAll()
	 */
	@Override
	public Collection<T> selectAll() {
		final String sql = poMapper.getSelectAll();
		final Class<T> clazz = poMapper.getCls();
		log.debug("selectAll sql:{}", sql);
		List<T> basePoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));
		basePoList.forEach(t -> t.afterLoad());
		return basePoList;
	}
	
	/**
	 * 根据索引查询, 需要组装
	 * @date 2020年6月29日
	 * @return
	 */
	@Override
	public T selectByKey(Object value) {
		final String sql = poMapper.getSelectByKey();
		final Class<T> clazz = poMapper.getCls();
		
		log.debug("select sql:{}, objs:{}, cls:{}", sql, value, clazz);
		List<T> basePoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz), value);
		if (basePoList.size() > 1) {
			log.error("Multiple pieces of data correspond to one primary key.cls:{}, sql:{},", clazz, sql);
		}
		if (basePoList.size() == 0) {
			return null;
		}
		T t = basePoList.get(0);
		t.afterLoad();
		return  t;
	}

	/**
	* 通过默认主键, 索引进行查询
	* 不需要指定主键和索引, 默认通过所有索引进行查询
	 */
	@Override
	public Collection<T> selectByIndex(Object[] value) {
		final String sql = poMapper.getSelectByIndex();
		final Class<T> clazz = poMapper.getCls();
		
		log.debug("select sql:{}, cls:{}", sql, clazz);
		Collection<T> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz), value);
		list.forEach(t -> t.afterLoad());
		return list;
	}
	
	/**
	 * 入库
	 * @date 2020年6月29日
	 * @param po
	 * @return
	 */
	public int insert(T po) {
		po.beforeSave();
		final String sql = poMapper.getInsert();
		log.debug("insert sql:{}, po:{}", sql, po);
		return jdbcTemplate.update(sql, po.propValues());
	}

	public int replace(T po) {
		po.beforeSave();
		final String sql = poMapper.getReplace();
		log.debug("replace sql:{}", sql);
		return jdbcTemplate.update(sql, po.propValues());
	}

	public int update(T po) {
		po.beforeSave();
		final String sql = poMapper.getUpdate();
		log.debug("update sql:{}", sql);
		Object[] props = po.propValues();
		Object[] ids = po.indexValues();
		Object[] objects = new Object[props.length + ids.length];
		System.arraycopy(props, 0, objects, 0, props.length);
		System.arraycopy(ids, 0, objects, props.length, ids.length);
		return jdbcTemplate.update(sql, objects);
	}

	/**
	 * 根据主键, 索引删除
	 */
	public int delete(T po) {
		final String sql = poMapper.getDelete();
		final Object[] indexsValue = po.indexValues();
		log.debug("delete sql:{}, idValues:{}", sql, indexsValue);
		return jdbcTemplate.update(sql, indexsValue);
	}

	/**
	 * 删除所有
	 */
	public int deleteAll() {
		final String sql = poMapper.getDeleteAll();
		log.debug("deleteAll sql:{}", sql);
		return jdbcTemplate.update(sql);
	}
	
	/**
	 * 批量添加
	 */
	public int[] insertBatch(Collection<T> basePos) {
		final String sql = poMapper.getInsert();
		List<Object[]> calValues = new ArrayList<Object[]>();
		for (T basePo : basePos) {
			basePo.beforeSave();
			calValues.add(basePo.propValues());
		}
		log.debug("insertBatch sql:{}", sql);
		return jdbcTemplate.batchUpdate(sql, calValues);
	}

	/**
	 * 批量删除
	 */
	public int[] deleteBatch(Collection<T> basePos) {
		final String sql = poMapper.getDelete();
		List<Object[]> calValues = new ArrayList<Object[]>();
		for (T basePo : basePos) {
			calValues.add(basePo.indexValues());
		}
		log.debug("deleteBatch sql:{}", sql);
		return jdbcTemplate.batchUpdate(sql, calValues);
	}

	/**
	 * 通过指定的主键, 值进行查询, 自动生成
	 * @param props 索引字段
	 * @param values 查询值
	 * 	比如:select * from player where playerId = 1
	 * selectByIndex(new String[]{"playerId"}, new Object[]{1})
	 */
	@Override
	public Collection<T> selectByIndex(String[] props, Object[] values) {
		final String tbName = poMapper.getTbName();
		final Class<T> clazz = poMapper.getCls();
		String sql = SQLGenerator.select(tbName, props);
		List<T> basePoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz), values);
		basePoList.forEach(t -> t.afterLoad());
		return basePoList;
	}

	/**
	 * 通过sql语句查询,在以上所有语句不能处理业务时, 使用此方法.
	 * 因为默认sql不缓存, 所以效率比以上操作相比略低.
	 * @param obj
	 * @return  
	 * @return BasePo  
	 * @date 2020年9月7日下午4:53:28
	 */
	@Override
	public Collection<T> selectBySql(String sql) {
		final Class<T> clazz = poMapper.getCls();
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(clazz));
	}
	
}
