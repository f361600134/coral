package org.coral.orm.core.command;

import java.util.Collection;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.command.AbsCommand.CommandType;
import org.coral.orm.core.db.IDao;

/**
 * 批量删除命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandDeleteBatch extends AbsCommand implements Executable<int[]>{
	
	private CommandDeleteBatch(Object obj, IDao dao) {
		super(obj, dao, CommandType.WRITE);
	}
	
	public static CommandDeleteBatch create(Object obj, IDao dao) {
		return new CommandDeleteBatch(obj, dao);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int[] execute() throws Exception {
		if (obj instanceof Collection) {
			return dao.deleteBatch((Collection<BasePo>)obj);
		}else {
			throw new RuntimeException("deleteBatch error, obj can not match Collection. wrong value:"+obj);
		}
	}
	
}
