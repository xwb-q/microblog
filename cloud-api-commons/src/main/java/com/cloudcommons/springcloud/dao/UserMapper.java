package com.cloudcommons.springcloud.dao;

import com.cloudcommons.springcloud.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    boolean login(String username,String password);
    boolean regist(User user);
    User getUserByUsername(String username);
}
