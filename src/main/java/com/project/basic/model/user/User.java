package com.project.basic.model.user;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class User {
    @NotEmpty
    private String email;
    private String password;
    private String username;
}
