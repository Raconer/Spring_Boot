package com.project.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.basic.model.user.UserInfo;

@Mapper
@Repository
public interface UserMapper {
    // Create
    int insert(UserInfo user);
    // Read
    UserInfo get(UserInfo user);
    // Update
    // Delete

}
