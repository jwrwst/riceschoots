package com.riceschoots.growth.web.service;

import cn.riceschoots.growth.controller.HelloService;
import cn.riceschoots.growth.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by DELL on 2018/09/27.
 */
@Component
public class HelloServiceFallback implements RefactorHelloService {


    @Override
    public String hello() {
        return "fallback of hello";
    }

    @Override
    public String hello(@RequestParam("name") String name) {
        return "error";
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") int age) {
        return new User("未知",0);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "error";
    }
}
