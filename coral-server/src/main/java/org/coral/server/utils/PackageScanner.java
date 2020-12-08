package org.coral.server.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

/**
 * 包搜索
 */
public class PackageScanner {
	
	private static final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	private static final MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
	//资源的格式  ant匹配符号格式
	private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	
	/**
	 * 扫描指定包中所有的类(包括子类)
	 * @param packageNames 包名支持权限定包名和ant风格匹配符(同spring)
	 * @return 
	 */
	public static Set<Class<?>> scanPackages(String... packageNames){
		return scanPackages(null, packageNames);
	}
	
	
	/**
	 * 扫描指定包中所有的类(包括子类)
	 * @param filter 过滤器
	 * @param packageNames 包名支持权限定包名和ant风格匹配符(同spring)
	 * @return 
	 */
	public static Set<Class<?>> scanPackages(Filter<Class<?>> filter, String... packageNames){
		Set<Class<?>> clazzCollection = new HashSet<Class<?>>();
		for (String packageName : packageNames) {
			try {
				// 搜索资源
				String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ resolveBasePackage(packageName) + "/" + DEFAULT_RESOURCE_PATTERN;
				Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
				
				for(Resource resource : resources){
					
					String className = "";
					
					try {
						if (!resource.isReadable()) {
							continue;
						}
						// 判断是否静态资源
						MetadataReader metaReader = metadataReaderFactory.getMetadataReader(resource);
						className = metaReader.getClassMetadata().getClassName();
						
						Class<?> clazz = Class.forName(className);
						if(filter != null){
							if(!filter.accept(clazz)){
								continue;
							}
						}
						
						clazzCollection.add(clazz);
					} catch (ClassNotFoundException e) {
						throw new RuntimeException(String.format(String.format("类 [%s] 不存在!", className)), e);
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(String.format(String.format("扫描包 [%s] 出错!", packageName)), e);
			}
		}
		return clazzCollection;
		
	}
	
	/**
	 * 将包名转换成目录名(com.xxx-->com/xxx)
	 * @param basePackage 包名
	 * @return
	 */
	private static String resolveBasePackage(String basePackage) {
		String placeHolderReplace = SystemPropertyUtils.resolvePlaceholders(basePackage);//${classpath}替换掉placeholder 引用的变量值
		return ClassUtils.convertClassNameToResourcePath(placeHolderReplace);
	}
	
}
