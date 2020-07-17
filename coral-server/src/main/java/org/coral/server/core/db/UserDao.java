//package org.coral.server.core.db;
//
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//import org.coral.orm.core.annotation.Dao;
//import org.coral.orm.core.db.CommonPresidentDao;
//import org.coral.server.module.player.User;
//
//@Dao(name = "user")//自定义注解,用于替换 
//@Mapper
//public interface UserDao extends CommonPresidentDao{
//	
//    @Select("select * from k_user where id = #{id}")
//    public User getById(@Param("id") int id);
//
//    @Insert("insert into k_user(id, name, age, birthday)values(#{id}, #{name}, #{age}, #{birthday})")
//    public int insert(User t);
//
//}
