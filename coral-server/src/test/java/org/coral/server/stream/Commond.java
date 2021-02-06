package org.coral.server.stream;

import org.coral.orm.core.base.BasePo;
import org.coral.orm.core.db.dao.IDao;
import org.coral.orm.core.db.process.DataProcessorAsyn;

public class Commond {

	/**
	 * 此种方式, 传入类型即为返回类型
	 * 如果此时把Executable换成为Executor, 则会报错
	 * @param <T>
	 * @param e
	 * @return
	 * @throws Exception
	 */
	public <T> T execute(Executable<T> e) throws Exception {
		return e.execute();
	}
	
	public static void main(String[] args) {
		try {
			Commond commond = new Commond();
			int number = commond.execute(()->{return 1+1;});
			System.out.println(number);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
