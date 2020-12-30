package org.coral.orm.core.command;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.IDao;

/**
 * 替换命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandReplace extends AbsCommand implements Executable<Integer>{
	
	private CommandReplace(Object obj, IDao dao) {
		super(obj, dao, CommandType.WRITE);
	}
	
	public static CommandReplace create(Object obj, IDao dao) {
		return new CommandReplace(obj, dao);
	}

	@Override
	public Integer execute() throws Exception {
		if (obj instanceof BasePo) {
			return dao.replace((BasePo)obj);
		}else {
			throw new RuntimeException("replace error, obj can not match BasePo. wrong value:"+obj);
		}
	}
	
}
