package org.coral.orm.core.command;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.IDao;

/**
 * 查询命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public abstract class AbsCommand<T extends BasePo>{

	protected Object obj;
	protected IDao<T> dao;
	CommandType commandType;
	
	AbsCommand(Object obj, IDao<T> dao) {
		super();
		this.obj = obj;
		this.dao = dao;
	}
	
	AbsCommand(Object obj, IDao<T> dao, CommandType commandType) {
		super();
		this.obj = obj;
		this.dao = dao;
		this.commandType = commandType;
	}
	
}
