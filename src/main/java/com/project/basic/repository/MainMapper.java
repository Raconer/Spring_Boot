package com.project.basic.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.basic.model.base.Base;
import com.project.basic.model.base.BaseSrch;

@Mapper
@Repository
public interface MainMapper {
    // Create
    int insert(Base base);
    // Read
    int totalCnt(BaseSrch option);
    Base get(int id);
    List<Base> getList(BaseSrch option);
}
