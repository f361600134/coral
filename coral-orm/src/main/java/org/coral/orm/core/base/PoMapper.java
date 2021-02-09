package org.coral.orm.core.base;

import org.coral.orm.core.annotation.PO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库POJO, SQL映射
 * 默认缓存单表操作,批量操作数据动态生成
 * 此方法仅只能生成单表操作的数据, 不能生成批量操作数据,
 */
public class PoMapper<T extends BasePo> {
	private static final Logger log = LoggerFactory.getLogger(PoMapper.class);

//	被Spring包装过的class类, 
	private final Class<T> cls;
	//	真正的class类
	private Class<?> realCls;
	private String tbName;
	private String selectAll;
	private String selectByIndex;
	private String selectByKey;
	private String deleteAll;
	private String delete;
	private String update;
	private String insert;
	private String replace;
	
	/**
	 * 默认生成sql缓存
	 * cls 是 springboot启动时加载的包装类型.
	 * cls.getSuperclass() 是本类型
	 * cls.getSuperclass().getSuperclass()是父类
	 * eg:我想获得 org.coral.server.module.player.Player
	 * cls为org.coral.server.module.player.Player$$EnhancerBySpringCGLIB$$6ee1534a
	 * cls.getSuperclass()为org.coral.server.module.player.Player
	 * cls.getSuperclass().getSuperclass()为org.coral.server.module.base.PlayerPo, 这个才是我们想到的注解类
	 *	最终通过cls.getSuperclass().getSuperclass()我们才可以获取到注解类PO.clsaa
	 * @date 2020年6月22日
	 * @param cls
	 */
	public PoMapper(Class<T> cls) {
		this.cls = cls;
		try {
			//Class<?> superCls = cls.getSuperclass().getSuperclass();
			this.realCls = cls.getSuperclass().getSuperclass();
			PO po = realCls.getAnnotation(PO.class);
			if (po == null) {
				log.error("BasePo未找到PO注解, className:{}", realCls.getName());
				return;
			}
			this.tbName = po.name();
			BasePo ins = cls.newInstance();
			
			this.selectAll = SQLGenerator.selectAll(tbName);
			this.deleteAll = SQLGenerator.deleteAll(tbName);
			this.selectByIndex = SQLGenerator.select(tbName, ins.indexs());
			this.selectByKey = SQLGenerator.select(tbName, ins.keyColumn());
			this.delete = SQLGenerator.delete(tbName, ins.indexs());
			this.update = SQLGenerator.update(tbName, ins.props(), ins.indexs());
			this.insert = SQLGenerator.insert(tbName, ins.props());
			this.replace = SQLGenerator.replace(tbName, ins.props());
			
		} catch (Exception e) {
			log.error("BasePo对象反射出错:{}", cls, e);
		}
	}

	public Class<T> getCls() {
		return cls;
	}

	public Class<?> getRealCls() {
		return realCls;
	}

	public String getTbName() {
		return tbName;
	}

	public String getSelectAll() {
		return selectAll;
	}

	public String getSelectByIndex() {
		return selectByIndex;
	}

	public String getSelectByKey() {
		return selectByKey;
	}

	public String getDeleteAll() {
		return deleteAll;
	}

	public String getDelete() {
		return delete;
	}

	public String getUpdate() {
		return update;
	}

	public String getInsert() {
		return insert;
	}

	public String getReplace() {
		return replace;
	}
	
}
