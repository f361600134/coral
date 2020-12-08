package org.coral.server.game.helper.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import org.coral.server.common.ServerConstant;
import org.coral.server.game.data.config.AnnotationConfig;
import org.coral.server.utils.RuntimeClassManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

public class PropertyLoader {

	private static final Logger logger = LoggerFactory.getLogger(PropertyLoader.class);
	
	public static void load() throws Exception {
//		initENV();
		loadConfigData();
		loadSensitiveWordTxt();
	}
	
//	private static void initENV() throws Exception {
//		Config.AppPath = ClassLoader.getSystemResource("").getFile();
//		if(System.getProperty("config_env").equals("local") ||
//			System.getProperty("config_env").equals("company")||
//			System.getProperty("config_env").equals("fsc") ||
//			System.getProperty("config_env").equals("jeremy")||
//			System.getProperty("config_env").equals("cehua")){
//			Config.ResourcePath = Config.AppPath;
//			//FSC for test
////			Config.ResourcePath = "src/main/resources/";
//		}
//		else
//			Config.ResourcePath = Config.AppPath+ "resources" + File.separator;
//		Config.JsonConfigPath = Config.ResourcePath + "configdata" + File.separator;
//		Config.CommonConfigPath = Config.ResourcePath + "common" + File.separator;
//		Config.localConfigPath = Config.ResourcePath + System.getProperty("config_env") + File.separator;
//		LOGGER.info("初始化系统环境  localConfigPath:{}", Config.localConfigPath);
//		
//		RuntimeClassManager classManager = RuntimeClassManager.instance();
//        // 扫描指定包下所有的Java类
//        classManager.loadClasses("com.lszj.game.data.config");
//	}
	
	/**
	 * 加载策划配置数据
	 */
	private static void loadConfigData() throws Exception
	{
		logger.info("初始化策划配置数据");
        Collection<Class<?>> configMgrClasses = RuntimeClassManager.instance().getClassByAnnotationClass(AnnotationConfig.class);
        for (Class<?> cls : configMgrClasses)
        {//load
			try {
				Method m = cls.getMethod("load");
				m.invoke(null);
			} catch (Exception e) {
				logger.error("Load策划配置数据出现异常", e);
			}
		}
        for (Class<?> cls : configMgrClasses)
        {//analyse
			try {
				Method m = cls.getMethod("analyse");
				m.invoke(null);
			} catch (Exception e) {
				logger.error("Analyse策划配置数据出现异常", e);
			}
		}
        for (Class<?> cls : configMgrClasses)
        {//complete
			try {
				Method m = cls.getMethod("complete");
				m.invoke(null);
			} catch (Exception e) {
				logger.error("Complete策划配置数据出现异常", e);
			}
		}
	}
	
	private static void loadSensitiveWordTxt()throws Exception
	{
		Set<String> set = Sets.newHashSet();
		File file = new File(ServerConstant.JsonPath+"SensitiveWord.txt");    //读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");
		try {
			
			if(file.isFile() && file.exists()){      //文件流是否存在
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while((txt = bufferedReader.readLine()) != null){
					String words[] = txt.split(",");
					for (String string : words) {
						set.add(string);
					}
				}
				BadWordFilter.addSensitiveWord(set);
				bufferedReader.close();
			}
		}catch (Exception e) {
			logger.error("loadSensitiveWordTxt error. e:", e);
		}finally {
			read.close();
		}
	}
	
//	public static Document loadRobot(int id) {
//		File file = new File(Config.CommonConfigPath+"Robot.txt");    //读取文件
//		if(!file.exists() || !file.isFile())
//			return null;
//		int line = PlayerInfoModule.ServerRobotStartId-id;
//		InputStreamReader read = null;
//		try {
//			read = new InputStreamReader(new FileInputStream(file),"utf-8");
//			BufferedReader bufferedReader = new BufferedReader(read);
//			String txt = null;
//			Document doc = null;
//			while((txt = bufferedReader.readLine()) != null){
//				if(line==0)
//				{
//					doc = Document.parse(txt);
//					break;
//				}
//				else
//					line--;
//			}
//			bufferedReader.close();
//			return doc;
//		}catch (Exception e) {
//			LOGGER.error("loadRobot 1 error. e:", e);
//		}finally {
//			try {
//				if(read!=null)
//					read.close();
//			} catch (IOException e) {
//				LOGGER.error("loadRobot 2 error. e:", e);
//			}
//		}
//		return null;
//	}
	
}
