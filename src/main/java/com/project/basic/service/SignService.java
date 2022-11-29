package com.project.basic.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.basic.model.user.AuthRes;
import com.project.basic.model.user.AuthUser;
import com.project.basic.model.user.UserInfo;
import com.project.basic.utils.JwtUtil;
import com.project.basic.utils.PasswordUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SignService implements UserDetailsService {

    UserService userService;

    // READ

    public AuthRes auth(UserInfo userInfo){

        UserInfo tempInfo = userService.get(userInfo);

        if(PasswordUtil.equals(userInfo.getPassword(), tempInfo.getPassword())){
            String email = tempInfo.getEmail();
            String token = JwtUtil.create(email);
            String refreshToken = JwtUtil.createRefresh();

            return AuthRes.builder()
                .email(email)
                .username(tempInfo.getUsername())
                .token(token)
                .refreshToken(refreshToken)
                .build();
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = new UserInfo(username);
        userInfo = userService.get(userInfo);

        if(userInfo == null){
            throw new UsernameNotFoundException("not found : " + username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        User user = new User(userInfo.getEmail(), userInfo.getPassword(), grantedAuthorities);
        return new AuthUser(userInfo, user);
    }
    
}
