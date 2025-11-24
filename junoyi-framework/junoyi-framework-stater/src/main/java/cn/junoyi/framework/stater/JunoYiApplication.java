package cn.junoyi.framework.stater;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;

/**
 * JunoYi框架应用启动入口类
 *
 * @author Fan
 */
public class JunoYiApplication {

    /**
     * 启动JunoYi应用程序
     * @param primarySource 应用程序的主配置类或组件扫描的根类
     * @param args 命令行参数
     */
    public static void run(Class<?> primarySource, String[] args){
        SpringApplication springApplication = new SpringApplication(primarySource);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}