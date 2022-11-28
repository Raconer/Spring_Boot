package com.project.basic.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.basic.model.api.common.response.DefDataResponse;
import com.project.basic.model.base.Base;
import com.project.basic.service.MainService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MainRestController {
    
    MainService mainService;

    @GetMapping
    public ResponseEntity<?> getMain(){
        Base base = mainService.getMain();
        return ResponseEntity.ok(new DefDataResponse(HttpStatus.OK, base));
    }
}
