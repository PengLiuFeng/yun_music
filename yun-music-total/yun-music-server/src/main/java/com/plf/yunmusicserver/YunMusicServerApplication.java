package com.plf.yunmusicserver;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.plf.*")
@EnableAsync
@EnableScheduling
@MapperScan("com.plf.yunmusicserver.dao")
@EnableAspectJAutoProxy
//@EnableApolloConfig
public class YunMusicServerApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment environment = SpringApplication.run(YunMusicServerApplication.class, args).getEnvironment();
        log.info(
                "\n---------------------------------------------\n\t" +
                        "【服务名】:{}\n\t" +
                        "【端口】:{}\n\t" +
                        "【日志级别】:{}\n\t" +
                        "【启动时间】:{}\n\t" +
                        "\n---------------------------------------------\n\t"
                , environment.getProperty("spring.application.name")
                , environment.getProperty("server.port")
                , environment.getProperty("logging.level.root")
                , LocalDateTime.now()
        );
    }

}
