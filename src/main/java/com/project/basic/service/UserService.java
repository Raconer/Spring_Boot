package com.project.basic.service;

import org.springframework.stereotype.Service;

import com.project.basic.model.user.User;
import com.project.basic.repository.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    UserMapper userMapper;
    
    // Create
    public int insert(User user){
        return userMapper.insert(user);
    }
    // Read
    public User get(User user){
        return userMapper.get(user);
    }
    // Update
    // Delete
}
