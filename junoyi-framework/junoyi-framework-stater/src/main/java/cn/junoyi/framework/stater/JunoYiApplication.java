package cn.junoyi.framework.stater;

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
        springApplication.setBanner((environment, sourceClass, out) -> {
            final String BLUE = "\033[38;5;39m";
            final String WHITE = "\033[97m";
            final String RESET = "\033[0m";

            out.println(BLUE +
                    " _____                              __    __       \n" +
                    "/\\___ \\                            /\\ \\  /\\ \\__    \n" +
                    "\\/__/\\ \\  __  __    ___     ___    \\ `\\`\\\\/'/\\_\\   \n" +
                    "   _\\ \\ \\/\\ \\/\\ \\ /' _ `\\  / __`\\   `\\ `\\ /'\\/\\ \\  \n" +
                    "  /\\ \\_\\ \\ \\ \\_\\ \\/\\ \\/\\ \\/\\ \\L\\ \\    `\\ \\ \\ \\ \\ \\ \n" +
                    "  \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\____/      \\ \\_\\ \\ \\_\\\n" +
                    "   \\/___/  \\/___/  \\/_/\\/_/\\/___/        \\/_/  \\/_/\n" +
                    "                                                   \n" + RESET
            );

            String junoyiVersion = environment.getProperty("junoyi.version","unknown");
            // 打印JunoYi版本
            out.println(WHITE + "JunoYi Framework Version: "+ junoyiVersion + RESET);
            // 打印SpringBoot版本
            out.println(WHITE + "Spring Boot Version: " + SpringApplication.class.getPackage().getImplementationVersion() + RESET);

            out.println(" ");
            out.println(" ");

        });
        springApplication.run(args);
    }
}