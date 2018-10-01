package com.riceschoots.growth.web;

import com.riceschoots.growth.web.service.HelloServiceFallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class RiceschootsWebApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RiceschootsWebApplication.class);

	public static void main(String[] args) {
//		SpringApplication.run(RiceschootsWebApplication.class, args);
        SpringApplication application = new SpringApplication(RiceschootsWebApplication.class);
        application.setRegisterShutdownHook(false);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        LOGGER.info("service web consumer started!!!");
	}

	//由于Feign客户端默认的Logger.Level对象定义为NONE级别，该级别不会记录任何Feign调用过程中的信息，
    //所以我们需要调整它的级别
	@Bean
    feign.Logger.Level feignLoggerLevel(){
	    return feign.Logger.Level.FULL;
    }

}
