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
	 * 查询所有, 默认不从缓存中获取, 直接查库
	 * @date 2020年6月29日
	 * @return
	 */
	public List<BasePo> select() {
		List<BasePo> basePoList = jdbcTemplate.query(poMapper.selectAll, new BeanPropertyRowMapper(poMapper.cls));
		log.debug("sql:{}", poMapper.selectAll);
		return basePoList;
	}

	@Override
	public BasePo select(Object[] objs) {
		log.info("sql:{}, objs:{}, cls:{}", poMapper.select, objs, poMapper.cls);
//		return (BasePo) jdbcTemplate.queryForObject(poMapper.select, objs, poMapper.cls);
		return (BasePo) jdbcTemplate.queryForObject(poMapper.select, new BeanPropertyRowMapper(poMapper.cls), objs);
	}
	
	/**
	 * 入库
	 * @date 2020年6月29日
	 * @param po
	 * @return
	 */
	public int insert(BasePo po) {
		log.debug("sql:{}", poMapper.insert);
		return jdbcTemplate.update(poMapper.insert, po.propValues());
	}

	public int replace(BasePo po) {
		log.debug("sql:{}", poMapper.replace);
		return jdbcTemplate.update(poMapper.replace, po.propValues());
	}

	public int update(BasePo po) {
		log.debug("sql:{}", poMapper.update);
		Object[] props = po.propValues();
		Object[] ids = po.idValues();
		Object[] objects = new Object[props.length + ids.length];
		System.arraycopy(props, 0, objects, 0, props.length);
		System.arraycopy(ids, 0, objects, props.length, ids.length);
		return jdbcTemplate.update(poMapper.update, objects);
	}

	/**
	 * 根据主键, 索引删除
	 */
	public int delete(BasePo po) {
		log.debug("sql:{}, idValues:{}", poMapper.delete, po.idValues());
		return jdbcTemplate.update(poMapper.delete, po.idValues());
	}

	/**
	 * 删除所有
	 */
	public int deleteAll() {
		log.debug("sql:{}", poMapper.deleteAll);
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
		log.debug("sql:{}", poMapper.insert);
		return jdbcTemplate.batchUpdate(poMapper.insert, calValues);
	}

	/**
	 * 批量删除
	 */
	public int[] deleteBatch(Collection<BasePo> basePos) {
		List<Object[]> calValues = new ArrayList<Object[]>();
		for (BasePo basePo : basePos) {
			calValues.add(basePo.idValues());
		}
		log.debug("sql:{}", poMapper.delete);
		return jdbcTemplate.batchUpdate(poMapper.delete, calValues);
	}
}
