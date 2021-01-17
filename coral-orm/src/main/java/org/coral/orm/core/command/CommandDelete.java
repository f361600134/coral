package org.coral.orm.core.command;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.IDao;

/**
 * 删除命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandDelete extends AbsCommand implements Executable{
	
	private CommandDelete(Object obj, IDao dao) {
		super(obj, dao, CommandType.WRITE);
	}
	
	public static CommandDelete create(Object obj, IDao dao) {
		return new CommandDelete(obj, dao);
	}

	@Override
	public Integer execute() throws Exception {
//		if (obj instanceof BasePo) {
//			return dao.delete((BasePo)obj);
//		}else {
//			throw new RuntimeException("delete error, obj can not match BasePo. wrong value:"+obj);
//		}
		return 0;
	}
	
	
}
