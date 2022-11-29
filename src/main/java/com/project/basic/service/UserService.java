package com.project.basic.service;

import org.springframework.stereotype.Service;

import com.project.basic.model.user.UserInfo;
import com.project.basic.repository.UserMapper;
import com.project.basic.utils.PasswordUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService  {

    UserMapper userMapper;
  
    
    // Create
    public int insert(UserInfo userInfo){
        String password = userInfo.getPassword();
        userInfo.setPassword(PasswordUtil.encoding(password));
        return userMapper.insert(userInfo);
    }
    // Read
    public UserInfo get(UserInfo userInfo){
        return userMapper.get(userInfo);
    }

    
    // Update
    // Delete
}
