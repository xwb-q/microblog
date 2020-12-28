package com.cloudcommons.springcloud.controller;

import com.cloudcommons.springcloud.entities.User;
import com.cloudcommons.springcloud.service.UserService;
import com.cloudcommons.springcloud.util.MsgResponse;
import com.cloudcommons.springcloud.util.RedisOps;
import com.cloudcommons.springcloud.util.TokenManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/commonsUser")
public class UserController {

    private UserService userService;

    @Resource
    private RedisOps redisOps;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/userregist")
    public MsgResponse regist(User user){
        String token = userService.regist(user);
        HashMap<String,String> map = new HashMap<>();
        if(token!=null){
            map.put("token",token);
            return MsgResponse.success("regist successful",map);
        }else
            return MsgResponse.error("regist failed");
    }

    @RequestMapping("/userlogin")
    public MsgResponse login(String username,String password){
        System.out.println(username+"---"+password);
        HashMap<String,String> map = new HashMap<>();
        String token = userService.login(username, password);
        if(token!=null){
            map.put("token",token);
            return MsgResponse.success("login successful",map);
        }
        return MsgResponse.error("login error,check username or password");
    }

    @RequestMapping("/testToken")
    public MsgResponse testToken(@RequestHeader("token") String token){
        System.out.println(token);
        boolean res = TokenManager.findJwtToken(redisOps, token);
        if(res)
            return MsgResponse.success("find token: ",token);
        else return MsgResponse.error("not find token");
    }

}
