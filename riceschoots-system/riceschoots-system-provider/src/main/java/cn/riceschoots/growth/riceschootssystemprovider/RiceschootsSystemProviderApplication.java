package cn.riceschoots.growth.riceschootssystemprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RiceschootsSystemProviderApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(RiceschootsSystemProviderApplication.class);

	public static void main(String[] args) {
//		SpringApplication.run(RiceschootsSystemProviderApplication.class, args);
        SpringApplication application = new SpringApplication(RiceschootsSystemProviderApplication.class);
        application.setRegisterShutdownHook(false);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        LOGGER.info("service web provider started!!!");
	}
}
