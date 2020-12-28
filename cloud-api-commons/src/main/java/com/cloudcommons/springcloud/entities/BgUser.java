package com.cloudcommons.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class BgUser implements UserDetails, Serializable {
    private Long id;
    private String username;
    private String password;
    private String nickname;

    private Integer enabled;

    private String email;

    private String userface;
    private Timestamp regTime;

    private List<Role> roleList;
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
