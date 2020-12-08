package org.coral.orm.core.command;

import java.util.Collection;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.IDao;

/**
 * 查询所有命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandSelectAll extends AbsCommand implements Executable<Collection<BasePo>>{
	
	private Object props;
	
	private CommandSelectAll(Object props, Object obj, IDao dao) {
		super(obj, dao, CommandType.READ);
	}
	
	
	public static CommandSelectAll create(Object props, Object obj, IDao dao) {
		return new CommandSelectAll(props, obj, dao);
	}

	@Override
	public Collection<BasePo> execute() throws Exception {
		if (props instanceof Object[]
				&& obj instanceof Object[]) {
			return dao.select((Object[])props, (Object[])obj);
		}
		return null;
	}
	
}
