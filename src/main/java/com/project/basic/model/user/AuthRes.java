package com.project.basic.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRes {
    private String email;
    private String username;
    private String token;
    private String refreshToken;   
}
