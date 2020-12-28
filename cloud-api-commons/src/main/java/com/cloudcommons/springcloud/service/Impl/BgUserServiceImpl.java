package com.cloudcommons.springcloud.service.Impl;

import com.cloudcommons.springcloud.dao.BgUserMapper;
import com.cloudcommons.springcloud.entities.BgUser;
import com.cloudcommons.springcloud.service.BgUserService;
import com.cloudcommons.springcloud.util.RedisOps;
import com.cloudcommons.springcloud.util.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BgUserServiceImpl implements BgUserService, UserDetailsService {

    @Autowired
    private BgUserMapper bgUserMapper;

    @Resource
    private RedisOps redisOps;

    //登陆逻辑
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username==null||username.isEmpty())
            throw new UsernameNotFoundException("用户名不能为空");
        BgUser bgUser = bgUserMapper.getUserByusername(username);
        setRedis(username,bgUser);
        return bgUser;
    }

    public void setRedis(String username,BgUser bgUser){
        String jwtToken = TokenManager.createJwtToken(username, bgUser);
        redisOps.set(username,jwtToken ,60*30);
    }

    @Override
    public BgUser getUserByusername(String username) {
        return bgUserMapper.getUserByusername(username);
    }
}
