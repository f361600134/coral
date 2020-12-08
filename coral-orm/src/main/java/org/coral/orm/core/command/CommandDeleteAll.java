package org.coral.orm.core.command;

import org.coral.orm.core.command.AbsCommand.CommandType;
import org.coral.orm.core.db.IDao;

/**
 * 删除所有命令命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandDeleteAll extends AbsCommand implements Executable<Integer>{
	
	private CommandDeleteAll(Object obj, IDao dao) {
		super(obj, dao, CommandType.WRITE);
	}
	
	public static CommandDeleteAll create(Object obj, IDao dao) {
		return new CommandDeleteAll(obj, dao);
	}

	@Override
	public Integer execute() throws Exception {
		return dao.deleteAll();
	}
	
}
