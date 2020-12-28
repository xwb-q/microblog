package com.cloudcommons.springcloud.dao;

import com.cloudcommons.springcloud.entities.BgUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BgUserMapper {
    BgUser getUserByusername(String username);
}
