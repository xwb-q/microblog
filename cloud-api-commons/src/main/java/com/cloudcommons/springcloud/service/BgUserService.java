package com.cloudcommons.springcloud.service;

import com.cloudcommons.springcloud.entities.BgUser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface BgUserService extends UserDetailsService {
    BgUser getUserByusername(String username);
}
