package org.coral.server;

import java.util.Date;

import org.coral.orm.core.db.process.DataProcessorAsyn;
import org.coral.server.game.module.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTest extends TestCase{ 
	
//	private Processor processor;
//	
////	@Autowired private DataSource ds;
//	
////	@Autowired private UserDao userDao;
//	
	@Autowired private DataProcessorAsyn processor;
	
	
	@Test public void contextLoads() {
		System.out.println("============");
		testInsert();
//		testSelectByKey();
//		testSelectAll();
//		selectByPrimaryKey();
//		testSelectByIndex();
//		testSelect();
//		testReplace();
//		testUpdate();
//		testInsertBatch();
//		testInsertBatchDiff();
//		delete();
//		deleteBatch();
//		deleteAll();
//		testselectAll();
		try {
			Thread.sleep(25000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//测试插入
	public void testInsert() {
		User user = new User();
		user.setId(11);
		user.setName("ccc");
		user.setAge(25);
		user.setBirthday(new Date().toString());
		processor.insert(user);
	}
//	
//	//测试替换
//	public void testReplace() {
//		User user = new User();
//		user.setId(11);
//		user.setName("ddd");
//		user.setAge(25);
//		user.setBirthday(new Date().toString());
//		processor.replace(user);
//	}
//	
//	//测试修改
//	public void testUpdate() {
//		User user = new User();
//		user.setId(11);
//		user.setName("eee");
//		user.setAge(25);
//		user.setBirthday(new Date().toString());
//		processor.update(user);
//	}
//	
//	public void testSelectAll() {
//		List<User> users = processor.selectAll(User.class);
//		System.out.println("users=============>"+users.size());
//	}
//	//测试查询
//	public void selectByPrimaryKey() {
//		User user = processor.selectByPrimaryKey(User.class, 11);
//		System.out.println("user=============>"+user);
//	}
//	
//	//测试查询
//	public void testSelectByIndex() {
//		Collection<BasePo> item = processor.selectByIndex(Item.class, new Object[]{Item.PROP_PLAYERID}, new Object[] {1});
//		System.out.println("user=============>"+item);
//	}
//	
//	//测试批量插入
//	public void testInsertBatch() {
//		List<BasePo> list = Lists.newArrayList();
//		for (int i = 1; i < 10; i++) {
//			User user = new User();
//			user.setId(i);
//			user.setName("ccc");
//			user.setAge(25);
//			user.setBirthday(new Date().toString());
//			list.add(user);
//		}
//		processor.insertBatch(list);
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
//		user.setId(1);
//		user.setName("ccc");
//		user.setAge(25);
//		user.setBirthday(new Date().toString());
//		list.add(user);
//		
//		processor.insertBatch(list);
//	}
//	
//	//测试删除
//	public void delete() {
//		User user = new User();
//		user.setId(11);
//		user.setName("ccc");
//		user.setAge(25);
//		user.setBirthday(new Date().toString());
//		processor.delete(user);
//	}
//	
//	//测试批量删除
//	private void deleteBatch() {
//		List<BasePo> list = Lists.newArrayList();
//		for (int i = 1000; i < 2000; i++) {
//			User user = new User();
//			user.setId(i);
//			user.setName("ccc");
//			user.setAge(25);
//			user.setBirthday(new Date().toString());
//			list.add(user);
//		}
//		processor.deleteBatch(list);
//	}
//	
//	//测试删除所有
//	public void deleteAll() {
//		processor.deleteAll(User.class);
//	}
}
