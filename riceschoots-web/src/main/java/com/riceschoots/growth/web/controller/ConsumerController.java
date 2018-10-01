package com.riceschoots.growth.web.controller;

import cn.riceschoots.growth.controller.HelloService;
import cn.riceschoots.growth.entity.User;
import com.riceschoots.growth.web.service.RefactorHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DELL on 2018/09/26.
 */
@RestController
public class ConsumerController {

    @Autowired
    RefactorHelloService refactorHelloService;

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        StringBuilder sb=new StringBuilder();
        sb.append(refactorHelloService.hello()).append("<br/>");
        sb.append(refactorHelloService.hello("MIMI")).append("<br/>");
        sb.append(refactorHelloService.hello("MIMI",20)).append("<br/>");
        sb.append(refactorHelloService.hello(new User("MIMI",20))).append("<br/>");
        return sb.toString();
    }
}
