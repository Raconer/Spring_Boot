package com.project.basic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.basic.model.base.Base;
import com.project.basic.model.base.BaseSrch;
import com.project.basic.model.common.SearchList;
import com.project.basic.repository.MainMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainService {
    
    MainMapper mainMapper;

    // Create
    public int insert(Base base){
        return mainMapper.insert(base);
    }
    // Read
    public Base get(int id){
        return mainMapper.get(id);
    }

    public SearchList<Base> getList(BaseSrch options){
        int totalCnt = mainMapper.totalCnt(options);
        options.setTotal(totalCnt);
        options.setPageInfo();
    
        List<Base> baseList = mainMapper.getList(options);
        return new SearchList<>(options, baseList);
    }

}
