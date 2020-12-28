package com.cloudcommons.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
    public User(Long id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
