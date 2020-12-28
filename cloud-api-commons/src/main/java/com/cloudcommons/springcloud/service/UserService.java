package com.cloudcommons.springcloud.service;

import com.cloudcommons.springcloud.entities.User;

public interface UserService {
    String login(String username,String password);
    String regist(User user);
    User getUserByUsername(String username);
}
