package org.coral.server;

import org.coral.net.common.NetConfig;
import org.coral.net.common.TaskThreadPoolConfig;
import org.coral.orm.common.OrmConfig;
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
@EnableConfigurationProperties({ OrmConfig.class , NetConfig.class, TaskThreadPoolConfig.class}) // 开启配置属性支持
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
