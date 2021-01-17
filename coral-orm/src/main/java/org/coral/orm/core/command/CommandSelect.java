package org.coral.orm.core.command;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.IDao;

/**
 * 根据条件查询命令
 * @author Jeremy
 * @date 2020年8月13日
 *
 */
public class CommandSelect extends AbsCommand<BasePo> implements Executable<BasePo>{
	
//	private Object[] props;
	
	private CommandSelect(Object obj, IDao<BasePo> dao) {
		super(obj, dao, CommandType.READ);
	}
	
	public static CommandSelect create(Object obj, IDao<BasePo> dao) {
		return new CommandSelect(obj, dao);
	}

	@Override
	public BasePo execute() throws Exception {
		return dao.selectByKey(obj);
	}
	
}
