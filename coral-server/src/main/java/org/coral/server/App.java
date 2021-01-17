package org.coral.server;

import org.coral.net.common.NetConfig;
import org.coral.net.common.TaskThreadPoolConfig;
import org.coral.orm.common.OrmConfig;
import org.coral.server.common.ServerConfig;
import org.coral.server.game.helper.config.PropertyLoader;
import org.coral.server.utils.RuntimeClassManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
//@EnableScheduling
@SpringBootApplication
@ComponentScan({"org.coral"})
@EnableConfigurationProperties({ 
	OrmConfig.class , NetConfig.class, 
	ServerConfig.class, TaskThreadPoolConfig.class}) // 开启配置属性支持
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		RuntimeClassManager classManager = RuntimeClassManager.instance();
            // 扫描指定包下所有的Java类
            classManager.loadClasses("org.coral.server");
			PropertyLoader.load();
			
			//注册事件
			//GameEventBus.instance().register(classManager.getClassByType(GameEventSubscribe.class));
			
			//启动程序
			SpringApplication.run(App.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
