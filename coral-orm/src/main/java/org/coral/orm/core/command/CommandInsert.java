package org.coral.orm.core.command;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.command.AbsCommand.CommandType;
import org.coral.orm.core.db.IDao;

/**
 * 插入命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandInsert extends AbsCommand implements Executable<Integer>{
	
	private CommandInsert(Object obj, IDao dao) {
		super(obj, dao, CommandType.WRITE);
	}
	
	public static CommandInsert create(Object obj, IDao dao) {
		return new CommandInsert(obj, dao);
	}

	@Override
	public Integer execute() throws Exception {
		if (obj instanceof BasePo) {
			return dao.insert((BasePo)obj);
		}else {
			throw new RuntimeException("insert error, obj can not match BasePo. wrong value:"+obj);
		}
	}
	
}
