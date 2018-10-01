package cn.riceschoots.growth.framework;
import cn.riceschoots.growth.framework.common.DynamicDataSourceRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class})
public class SystemProviderApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemProviderApplication.class);

	public static void main(String[] args) {
//		SpringApplication.run(RiceschootsSystemProviderApplication.class, args);
        SpringApplication application = new SpringApplication(SystemProviderApplication.class);
        application.setRegisterShutdownHook(false);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        LOGGER.info("service web provider started!!!");
	}
}
