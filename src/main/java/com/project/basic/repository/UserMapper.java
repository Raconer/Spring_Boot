package com.project.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.basic.model.user.User;

@Mapper
@Repository
public interface UserMapper {
    // Create
    int insert(User user);
    // Read
    User get(User user);
    // Update
    // Delete

}
