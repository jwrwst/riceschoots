package com.riceschoots.growth.web.service;

import cn.riceschoots.growth.controller.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by DELL on 2018/09/26.
 */
@FeignClient(value = "riceschoots-provider",fallback = HelloServiceFallback.class)
public interface RefactorHelloService extends HelloService {
}
