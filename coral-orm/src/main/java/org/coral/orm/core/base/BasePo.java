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
public abstract class BasePo implements IBasePo, Serializable {
	
	/**
	 * @date 2020年7月16日
	 */
	private static final long serialVersionUID = 1L;

	public String poName() {
		return this.getClass().getSimpleName();
	}
	
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
