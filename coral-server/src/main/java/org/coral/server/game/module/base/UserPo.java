package org.coral.server.game.module.base;

import org.coral.orm.core.annotation.PO;
import org.coral.orm.core.base.BasePo;

/**
* UserPo
* @author Jeremy
*/
@PO(name = "k_User")
public abstract class UserPo extends BasePo {

	public static final String PROP_ID = "id";
	public static final String PROP_NAME = "name";
	public static final String PROP_AGE = "age";
	public static final String PROP_BIRTHDAY = "birthday";
	
	protected int id;//id
	protected String name;//名字
	protected int age;//年纪
	protected String birthday;//生日
	
	public UserPo(){
		this.name = "";
		this.birthday = "";
	}
	
	/** id **/
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	/** 名字 **/
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	/** 年纪 **/
	public int getAge(){
		return this.age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	/** 生日 **/
	public String getBirthday(){
		return this.birthday;
	}
	
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
	
	
	@Override
	public String toString() {
		return "User [id= "+ id +", name= "+ name +", age= "+ age +", birthday= "+ birthday+"]";
	}
	
	@Override
	public String[] props() {
		return new String[] {
		"`id`",
		"`name`",
		"`age`",
		"`birthday`",
		};
	}

	@Override
	public Object[] propValues() {
		return new Object[] { 
		getId(),
		getName(),
		getAge(),
		getBirthday(),
		};
	}

	@Override
	public String[] ids() {
		return new String[] {
			"`id`",
		};
	}
	
	@Override
	public Object[] idValues() {
		return new Object[] {
			id,
		};
	}
	
	@Override
	public Object key() {
		// 第一主键的数据值, 例如playerId
		//return getId();
		return getId();
	}

	@Override
	public String cacheId() {
		// 缓存的二级ID, 如果不是一对多关系的return null即可
		//return String.valueOf(getId());
		return String.valueOf(getId());
	}

	@Override
	public String keyColumn() {
		// 第一主键的数据库列名
		return ids()[0];
	}
	
}
