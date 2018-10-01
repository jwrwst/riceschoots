package com.riceschoots.growth.web.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by DELL on 2018/09/26.
 */
//@Configuration
public class FeignConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        Feign.Builder feignBuilder = Feign.builder();
        return feignBuilder;
    }
}
