package com.cloudcommons.springcloud.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Data
public class Role implements GrantedAuthority, Serializable {
    private Integer id;
    private String name;
    @Override
    public String getAuthority() {
        return this.name;
    }
}
