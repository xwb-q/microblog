package com.cloudcommons.springcloud.service.Impl;

import com.cloudcommons.springcloud.dao.UserMapper;
import com.cloudcommons.springcloud.entities.User;
import com.cloudcommons.springcloud.service.UserService;
import com.cloudcommons.springcloud.util.RedisOps;
import com.cloudcommons.springcloud.util.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.LinkedHashMap;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private RedisOps redisOps;

    @Override
    public String login(String username, String password) {
        String jwttoken = redisOps.get(username);
        User user = null;
        String dbps = "";
        if(jwttoken!=null){
            Claims claims = TokenManager.parseJWT(jwttoken);
//            Object o1 = TokenManager.parseJWT(jwttoken).get("username");
//            Object o2 = TokenManager.parseJWT(jwttoken).get("password");
            dbps = (String)TokenManager.parseJWT(jwttoken).get("password");
        }
        else{
            System.out.println("jwttoken 为 空");
            user = userMapper.getUserByUsername(username);
            dbps = user.getPassword();
            jwttoken = TokenManager.createJwtToken(username, user);
        }
        if(dbps.equals(DigestUtils.md5DigestAsHex(password.getBytes())) ){
//            String jwtToken = TokenManager.createJwtToken(username, user);
            redisOps.set(jwttoken,username,30*60);//[token,username]
            return jwttoken;
        }
        return null;
    }

    @Override
    public String regist(User user) {
        //密码加密
        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        //生成新用户  密码已经加密
        user = new User(user.getUsername(),md5password);

        //将密码加密得用户写进数据库
        userMapper.regist(user);
        //利用用户名和 加密了密码得User得到token
        String jwttoken = TokenManager.createJwtToken(user.getUsername(),user);
        redisOps.set(jwttoken,user.getUsername(),30*60);//[token , username]
        return jwttoken;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
