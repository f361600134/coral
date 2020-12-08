package org.coral.server.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;


/**
 * 运行时类管理器
 */
public class RuntimeClassManager {

	private static RuntimeClassManager classManager = new RuntimeClassManager();

	public static RuntimeClassManager instance() {
		return classManager;
	}
	
	private Set<Class<?>> allClasses;
	
	
	public void loadClasses(String basePackages) {
		if (allClasses == null) {
			this.allClasses = new HashSet<Class<?>>();
			ClassPathScanner scanner = new ClassPathScanner(true, true, null);
			String[] bps = basePackages.split(",");
			for (String basePackage : bps) {
				Set<Class<?>> packageAllClasses = scanner.getPackageAllClasses(basePackage, true);
				if (packageAllClasses != null && !packageAllClasses.isEmpty()) {
					this.allClasses.addAll(packageAllClasses);
				}
			}
		}
	}
	
	public Collection<Class<?>> getClassByType(Class<?> clazzType) {
		Set<Class<?>> clazzes = new HashSet<Class<?>>();
		for (Class<?> clazz : allClasses) {
			if (clazz != null && !clazz.isInterface() && instanceofClass(clazzType, clazz)) {
				clazzes.add(clazz);
			}
		}
		return clazzes;
	}
	
	public Collection<Class<?>> getClassByAnnotationClass(Class<? extends Annotation> annotationClass) {
		Set<Class<?>> clazzes = new HashSet<Class<?>>();
		for (Class<?> clazz : allClasses) {
			if (clazz != null && !clazz.isInterface()) {
				if (clazz.isAnnotationPresent(annotationClass)) {
					clazzes.add(clazz);
				}
			}
		}
		return clazzes;
	}

	public Collection<Class<?>> getClassByAnnotation(Annotation annotation) {
		Set<Class<?>> clazzes = new HashSet<Class<?>>();
		for (Class<?> clazz : allClasses) {
			if (clazz != null && !clazz.isInterface()) {
				Annotation[] annotations = clazz.getAnnotations();
				if (annotations != null && ArrayUtils.contains(annotations, annotation)) {
					clazzes.add(clazz);
				}
			}
		}
		return clazzes;
	}

	private boolean instanceofInterfaces(Class<?> clazzType, Class<?>... clazzs) {
		for (Class<?> clazz : clazzs) {

			if (clazz == null) {
				return false;
			}
			if (clazz == Object.class) {
				return false;
			}
			if (clazz == clazzType) {
				return true;
			}
			
			Class<?>[] interfaces = clazz.getInterfaces();
			if (interfaces.length > 0) {
				return instanceofInterfaces(clazzType, interfaces);
			}
		}
		return false;
	}
	
	
	private boolean instanceofClass(Class<?> clazzType, Class<?> checkCls) {
		if (checkCls == null) {
			return false;
		}
		if (checkCls == Object.class) {
			return false;
		}
		if (clazzType == checkCls) {
			return false;
		}
		if (Modifier.isAbstract(checkCls.getModifiers())) {
			return false;
		}
		return clazzType.isAssignableFrom(checkCls);
	}

	public ImmutableSet<ClassPath.ClassInfo> getTopLevelClasses(String pachageName) {
		ImmutableSet<ClassPath.ClassInfo> result = null;
		try {
			ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
			result = classPath.getTopLevelClasses(pachageName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
