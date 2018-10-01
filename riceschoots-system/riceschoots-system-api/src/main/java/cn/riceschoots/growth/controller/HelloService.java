package cn.riceschoots.growth.controller;

import cn.riceschoots.growth.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created by DELL on 2018/09/26.
 */
//@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name,@RequestHeader("age") int age);

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    String hello(@RequestBody User user);
}
