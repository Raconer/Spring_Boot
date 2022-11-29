package com.project.basic.model.user;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserInfo{
    private int id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    private String username;

    public UserInfo(){}

    public UserInfo(String email){
        this.email = email;
    }
}
