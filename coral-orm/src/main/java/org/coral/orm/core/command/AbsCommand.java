package org.coral.orm.core.command;

import org.coral.orm.core.db.IDao;

/**
 * 查询命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public abstract class AbsCommand{

	protected Object obj;
	protected IDao dao;
	CommandType commandType;
	
	AbsCommand(Object obj, IDao dao) {
		super();
		this.obj = obj;
		this.dao = dao;
	}
	
	AbsCommand(Object obj, IDao dao, CommandType commandType) {
		super();
		this.obj = obj;
		this.dao = dao;
		this.commandType = commandType;
	}
	
	enum CommandType{
		READ,
		WRITE;
	}
	
}
