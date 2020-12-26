package com.cloudcommons.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;

    private Integer enabled;

    private String email;

    private String userface;
    private Timestamp regTime;
}
