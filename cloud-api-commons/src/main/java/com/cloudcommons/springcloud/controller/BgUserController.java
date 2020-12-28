package com.cloudcommons.springcloud.controller;

import com.cloudcommons.springcloud.service.BgUserService;
import com.cloudcommons.springcloud.service.Impl.BgUserServiceImpl;
import com.cloudcommons.springcloud.util.RedisOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/testCommons")
public class BgUserController {
//    @Autowired
//    private BgUserService userService;

    @Resource
    private RedisOps redisOps;

    @RequestMapping("/test")
    public void testRedis(){
//        redisOps.set("x","123",30);
    }
}
