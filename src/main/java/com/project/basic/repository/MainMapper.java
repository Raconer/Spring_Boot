package com.project.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.basic.model.Base;

@Mapper
@Repository
public interface MainMapper {
    Base getMain();
}