package org.coral.orm.core.command;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.command.AbsCommand.CommandType;
import org.coral.orm.core.db.IDao;

/**
 * 修改命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandUpdate extends AbsCommand implements Executable<Integer>{
	
	private CommandUpdate(Object obj, IDao dao) {
		super(obj, dao, CommandType.WRITE);
	}
	
	public static CommandUpdate create(Object obj, IDao dao) {
		return new CommandUpdate(obj, dao);
	}

	@Override
	public Integer execute() throws Exception {
		if (obj instanceof BasePo) {
			return dao.update((BasePo)obj);
		}else {
			throw new RuntimeException("update error, obj can not match BasePo. wrong value:"+obj);
		}
	}
	
}
