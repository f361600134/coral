package org.coral.orm.core.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.coral.orm.core.annotation.Column;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 基础持久化对象父类
 */
public abstract class BasePo implements Serializable {
	
	/**
	 * @date 2020年7月16日
	 */
	private static final long serialVersionUID = 1L;

	public String poName() {
		return this.getClass().getSimpleName();
	}
	
	/**
	 * primary key, 对应数据库的主键,唯一主键.可以为null.
	 * 比如排行榜模块, 主键是复合主键.生成的唯一主键就为空
	 * @return
	 */
	abstract public Object key();
	
	/**
	 * 数据库主键的列名
	 * @return
	 */
	abstract public String keyColumn();
	
	/**
	 * 缓存二级ID, 如果返回空默认使用key()
	 * @return
	 */
	abstract public String cacheId();
	
	/**
	 * 索引列, 对应数据库的索引,仅用于查询,删除
	 * 索引列  = 主键 + 索引列.
	 * 比如,排行榜表rank{serverId, rankType, playerId, value1, value2}
	 * 服务器id,排行榜类型,playerId为复合主键.那么index就为这三个字段.删除数据就需要使用复合主键
	 * @return
	 */
	abstract public String[] indexs();

	/**
	 * 索引列的值
	 * @return
	 */
	abstract public Object[] indexValues();
	
	/**
	 * 所有属性列
	 * @return
	 */
	abstract public String[] props();

	/**
	 * 所有属性列的值
	 * @return
	 */
	abstract public Object[] propValues();
	
	public void beforeSave() {
		Class<?> cls = getClass();
		Class<?> superCls = cls.getSuperclass();
		Field[] files = cls.getDeclaredFields();
		
		for (Field field : files) {
			Column column = field.getAnnotation(Column.class);
			if (column == null) {
				continue;
			}
			//	注解值为空,表示无需序列化
			final String columnName = column.value();
			if (StringUtils.isBlank(columnName)) {
				continue;
			}
			//	如果禁止序列化, 则跳过
			if (Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			//	通过定义的columnName,反射获取到字段,设置内容
			try {
				//	获取当前注解的值
				field.setAccessible(true);
				Object obj = field.get(this);
				
				//	转Json
				String value = JSON.toJSONString(obj);
				
				//	设置到父类
				Field superField = superCls.getDeclaredField(columnName);
				superField.setAccessible(true);
				superField.set(this, value);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * 加载后操作
	 */
	public void afterLoad() {
		Class<?> clazz = this.getClass();
		Class<?> superClazz = clazz.getSuperclass();
		Field[] files = clazz.getDeclaredFields();
		
		for (Field field : files) {
			Column column = field.getAnnotation(Column.class);
			if (column == null) {
				continue;
			}
			String columnName =	column.value();
			if (StringUtils.isBlank(columnName)) {
				continue;
			}
			//	通过定义的columnName,反射获取到字段,设置内容
			try {
				//	获取到父类的值
				Field superField = superClazz.getDeclaredField(columnName);
				superField.setAccessible(true);
				String value = (String)superField.get(this);
				if (StringUtils.isBlank(value)) {
					continue;
				}
				//	Json转对象
				Type type = field.getGenericType();
				Object obj = JSONObject.parseObject(value, type);
				
				//	设置到子类
				field.setAccessible(true);
				field.set(this, obj);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
}
