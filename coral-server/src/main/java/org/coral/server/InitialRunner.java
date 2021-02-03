package org.coral.server;

import java.util.Date;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.helper.uuid.SnowflakeGenerator;
import org.coral.server.game.module.resource.IResourceGroupService;
import org.coral.server.game.module.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitialRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(InitialRunner.class);
	
//	@Autowired private GameEventBus eventBus;
	
	@Autowired 
	private SnowflakeGenerator generater;
	
	@Autowired 
	private IResourceGroupService resourceService;
	
//	private Processor processor;
//	
////	@Autowired private DataSource ds;
//	
////	@Autowired private UserDao userDao;
	
	@Autowired private DataProcessorAsyn processor;
	
	public void run(String... args) throws Exception {
		try {
//			log.info("generater:{}", generater.nextId());
//			Map<Integer, Integer> rewardMap = Maps.newHashMap(); 
//			rewardMap.put(90001, 1);
//			resourceService.reward(1, rewardMap, NatureEnum.GM);
			
////			dataSourceFactory.druidDataSource();
////			System.out.println(dataSourceFactory.druidDataSource());
////			System.out.println();
////			processor.print();
////			testInsertBatchDiff();
////			processor.select(User.class);
//				testInsert();
////			testInsertBatch();
////			testSelect();
////			System.out.println(userDao.getById(5));
		} catch (Exception e) {
			log.error("服务器初始化过程出现异常, 启动失败", e);
		}
	}
//	
	public void testInsert() {
		for (int i = 1; i < 1000; i++) {
			User user = new User();
			user.setId(i);
			user.setName("ccc");
			user.setAge(25);
			user.setBirthday(new Date().toString());
			try {
				processor.insert(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {//10后,新数据
			System.out.println("开始进入等待中....");
			Thread.sleep(50000);
			System.out.println("等待结束中....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		for (int i = 2001; i < 3000; i++) {
			User user = new User();
			user.setId(i);
			user.setName("ccc");
			user.setAge(25);
			user.setBirthday(new Date().toString());
			try {
				processor.insert(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//	
//	public void testInsertBatch() {
//		List<BasePo> list = Lists.newArrayList();
//		for (int i = 0; i < 1000; i++) {
//			User user = new User();
//			user.setId(i);
//			user.setName("ccc");
//			user.setAge(25);
//			user.setBirthday(new Date().toString());
//			list.add(user);
//		}
//		try {
//			processor.insertBatch(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	//测试批量插入
//	public void testInsertBatchDiff() {
//		List<BasePo> list = Lists.newArrayList();
//		
//		Stu stu = new Stu();
//		stu.setId(1);
//		stu.setName("ccc");
//		stu.setAge(25);
//		stu.setBirthday(new Date().toString());
//		list.add(stu);
//		
//		User user = new User();
//		user.setId(2);
//		user.setName("ccc");
//		user.setAge(25);
//		user.setBirthday(new Date().toString());
//		list.add(user);
//		
//		processor.insertBatch(list);
//	}
//	
////	public void testSelect() {
//////		User user = new User();
//////		user.setId(1);
//////		user.setName("aaa");
//////		user.setAge(25);
//////		user.setBirthday(new Date().toString());
//////		User user = processor.select(User.class, 4);
//////		System.out.println(user);
////		Dao<BasePo> dao = processor.getDao(User.class.getSimpleName().toLowerCase());
////		for (int i = 0; i < 1000; i++) {
////			try {
////				System.out.println(dao.select(ds, i));
//////				User user = processor.select(User.class, i);
//////				System.out.println(user);
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
////		}
////	}
//	
	
}
