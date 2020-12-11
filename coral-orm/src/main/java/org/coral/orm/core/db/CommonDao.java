package org.coral.orm.core.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.base.PoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommonDao implements IDao{
	
	private static final Logger log = LoggerFactory.getLogger(CommonDao.class);
	
	private JdbcTemplate jdbcTemplate;
	
	//posql映射
	private PoMapper poMapper;
	
	public CommonDao(BasePo po, JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.poMapper = new PoMapper(po.getClass());
	}
	
	/**
	 * 查询所有
	* TODO 该方法的实现功能
	* @see org.coral.orm.core.db.IDao#selectAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<BasePo> selectAll() {
		log.debug("selectAll sql:{}", poMapper.selectAll);
		@SuppressWarnings("rawtypes")
		List<BasePo> basePoList = jdbcTemplate.query(poMapper.selectAll, new BeanPropertyRowMapper(poMapper.cls));
		return basePoList;
	}
	
	/**
	 * 根据索引查询, 需要组装
	 * @date 2020年6月29日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BasePo selectByKey(Object value) {
		log.debug("select sql:{}, objs:{}, cls:{}", poMapper.selectByKey, value, poMapper.cls);
		
		@SuppressWarnings("rawtypes")
		List<BasePo> basePoList = jdbcTemplate.query(poMapper.selectByKey, new BeanPropertyRowMapper(poMapper.cls), value);
		if (basePoList.size() > 1) {
			log.error("Multiple pieces of data correspond to one primary key.cls:{}, sql:{},", poMapper.cls, poMapper.selectByKey);
		}
		if (basePoList.size() == 0) {
			return null;
		}
		return basePoList.get(0) ;
	}

	/**
	 * 
	* TODO 该方法的实现功能
	* props 暂时没用上，考虑索引组合使用，生成sql
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection<BasePo> selectByIndex(Object[] props, Object[] value) {
		log.debug("select sql:{}, objs:{}, cls:{}", poMapper.selectByIndex, props, poMapper.cls);
		//return (BasePo) jdbcTemplate.queryForObject(poMapper.selectByIndex, new BeanPropertyRowMapper(poMapper.cls), props);
		return jdbcTemplate.query(poMapper.selectByIndex, new BeanPropertyRowMapper(poMapper.cls), value);
	}
	
	/**
	 * 入库
	 * @date 2020年6月29日
	 * @param po
	 * @return
	 */
	public int insert(BasePo po) {
		log.debug("insert sql:{}", poMapper.insert);
		return jdbcTemplate.update(poMapper.insert, po.propValues());
	}

	public int replace(BasePo po) {
		log.debug("replace sql:{}", poMapper.replace);
		return jdbcTemplate.update(poMapper.replace, po.propValues());
	}

	public int update(BasePo po) {
		log.debug("update sql:{}", poMapper.update);
		Object[] props = po.propValues();
		Object[] ids = po.indexValues();
		Object[] objects = new Object[props.length + ids.length];
		System.arraycopy(props, 0, objects, 0, props.length);
		System.arraycopy(ids, 0, objects, props.length, ids.length);
		return jdbcTemplate.update(poMapper.update, objects);
	}

	/**
	 * 根据主键, 索引删除
	 */
	public int delete(BasePo po) {
		log.debug("delete sql:{}, idValues:{}", poMapper.delete, po.indexValues());
		return jdbcTemplate.update(poMapper.delete, po.indexValues());
	}

	/**
	 * 删除所有
	 */
	public int deleteAll() {
		log.debug("deleteAll sql:{}", poMapper.deleteAll);
		return jdbcTemplate.update(poMapper.deleteAll);
	}
	
	/**
	 * 批量添加
	 */
	public int[] insertBatch(Collection<BasePo> basePos) {
		List<Object[]> calValues = new ArrayList<Object[]>();
		for (BasePo basePo : basePos) {
			calValues.add(basePo.propValues());
		}
		log.debug("insertBatch sql:{}", poMapper.insert);
		return jdbcTemplate.batchUpdate(poMapper.insert, calValues);
	}

	/**
	 * 批量删除
	 */
	public int[] deleteBatch(Collection<BasePo> basePos) {
		List<Object[]> calValues = new ArrayList<Object[]>();
		for (BasePo basePo : basePos) {
			calValues.add(basePo.indexValues());
		}
		log.debug("deleteBatch sql:{}", poMapper.delete);
		return jdbcTemplate.batchUpdate(poMapper.delete, calValues);
	}

}
