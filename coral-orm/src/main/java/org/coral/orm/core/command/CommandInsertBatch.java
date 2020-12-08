package org.coral.orm.core.command;

import java.util.Collection;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.command.AbsCommand.CommandType;
import org.coral.orm.core.db.IDao;

/**
 * 批量插入命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandInsertBatch extends AbsCommand implements Executable<int[]>{
	
	private CommandInsertBatch(Object obj, IDao dao) {
		super(obj, dao, CommandType.WRITE);
	}
	
	public static CommandInsertBatch create(Object obj, IDao dao) {
		return new CommandInsertBatch(obj, dao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int[] execute() throws Exception {
		if (obj instanceof Collection) {
			return dao.insertBatch((Collection<BasePo>)obj);
		}else {
			throw new RuntimeException("insertBatch error, obj can not match Collection. wrong value:"+obj);
		}
	}
	
}
