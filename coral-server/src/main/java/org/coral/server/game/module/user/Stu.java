package org.coral.server.game.module.user;

import org.coral.server.game.module.base.StuPo;
import org.springframework.stereotype.Repository;

/**
* @author Jeremy
*/
@Repository
public class Stu extends StuPo {
	
	public Stu() {

	}
	
	public Stu(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Stu create(String name) {
		Stu stu = new Stu();
		stu.setId(1);
		stu.setAge(1);
		stu.setName(name);
		return stu;
	}

	@Override
	public String toString() {
		return "Stu [id=" + id + ", name=" + name + ", age=" + age + ", birthday=" + birthday + "]";
	}
	
}
