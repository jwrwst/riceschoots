package cn.riceschoots.growth.riceschootssystemprovider.controller;

import cn.riceschoots.growth.controller.HelloService;
import cn.riceschoots.growth.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * Created by DELL on 2018/09/26.
 */
@RestController
public class HelloController implements HelloService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public String hello(){
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        //让处理线程等待几秒
        int sleepTime = new Random().nextInt(3000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("/hello,host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
        return "Hello World";
    }

    @Override
    public String hello(@RequestParam("name") String name) {
        return "Hello "+name;
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") int age) {
        return new User(name,age);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "Hello "+user.getName()+","+user.getAge();
    }
}
