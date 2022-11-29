package com.project.basic.model.user;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser extends User{
    private int id;
    private String email;

    public AuthUser(UserInfo userInfo, User user){
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.id = userInfo.getId();
        this.email = userInfo.getEmail();
    }
    
}
