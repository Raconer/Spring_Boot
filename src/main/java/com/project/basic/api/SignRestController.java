package com.project.basic.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.basic.model.api.common.response.DefDataResponse;
import com.project.basic.model.api.common.response.DefResponse;
import com.project.basic.model.user.AuthRes;
import com.project.basic.model.user.UserInfo;
import com.project.basic.service.SignService;
import com.project.basic.service.UserService;
import com.project.basic.utils.ValidErrUtil;
import com.project.basic.validate.user.InsertVdt;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sign")
public class SignRestController {

    UserService userService;
    SignService signService;
    InsertVdt insertVdt;

    // Create
    @PostMapping("/up")
    public ResponseEntity<?> create(@RequestBody UserInfo userInfo, BindingResult result){

        insertVdt.validate(userInfo, result);
        if(result.hasErrors()){
            return ValidErrUtil.getValidateError(result.getFieldErrors());
        }
        
        if(userService.insert(userInfo) > 0){
            return ResponseEntity.ok(new DefResponse(HttpStatus.OK));
        }

        return ResponseEntity.ok(new DefResponse(HttpStatus.BAD_REQUEST));
    }
    // Read
    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid UserInfo userInfo, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.ok(new DefResponse(HttpStatus.BAD_REQUEST));
        }

        AuthRes authRes = signService.auth(userInfo);

        if(authRes == null){
            return ResponseEntity.ok(new DefResponse(HttpStatus.BAD_REQUEST)); 
        }
        return ResponseEntity.ok(new DefDataResponse(HttpStatus.OK, authRes));
    }
    // Update
    // Delete
}
