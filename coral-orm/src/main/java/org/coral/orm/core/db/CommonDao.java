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
	@Override
	public Collection<BasePo> selectAll() {
		List<BasePo> basePoList = jdbcTemplate.query(poMapper.selectAll, new BeanPropertyRowMapper(poMapper.cls));
		log.debug("select sql:{}", poMapper.selectAll);
		return basePoList;
	}
	
	/**
	 * 根据索引查询, 需要组装
	 * @date 2020年6月29日
	 * @return
	 */
	public Collection<BasePo> selectByIndex(Object[] indexs, Object[] values) {
		log.info("select sql:{}, objs:{}, cls:{}", poMapper.select, indexs, values, poMapper.cls);
		return null;
	}

	@Override
	public BasePo selectByPrimaryKey(Object[] props) {
		log.info("select sql:{}, objs:{}, cls:{}", poMapper.select, props, poMapper.cls);
//		return (BasePo) jdbcTemplate.queryForObject(poMapper.select, objs, poMapper.cls);
		return (BasePo) jdbcTemplate.queryForObject(poMapper.select, new BeanPropertyRowMapper(poMapper.cls), props);
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

	@Override
	public Collection<BasePo> select(Object[] props, Object[] objs) {
		StringBuilder sb = new StringBuilder("SELECT * FROM `").append(poMapper.tbName).append("` WHERE ");
		sb.append(props[0]).append("=?");
		for (int i = 1; i < props.length; i++) {
			sb.append(" and ").append(props[i]).append("=?");
		}
		return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper(poMapper.cls), objs);
	}

}
