package com.project.basic.service;

import org.springframework.stereotype.Service;

import com.project.basic.model.Base;
import com.project.basic.repository.MainMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainService {
    
    MainMapper mainMapper;

    public Base getMain(){
        return mainMapper.getMain();
    }

}
