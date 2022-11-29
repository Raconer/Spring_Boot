package com.project.basic.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.basic.model.api.common.response.DefResponse;
import com.project.basic.model.user.User;
import com.project.basic.service.UserService;
import com.project.basic.utils.ValidErrUtil;
import com.project.basic.validate.user.InsertVdt;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    UserService userService;
    InsertVdt insertVdt;
    
    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user, BindingResult result){

        insertVdt.validate(user, result);
        if(result.hasErrors()){
            return ValidErrUtil.getValidateError(result.getFieldErrors());
        }
        
        if(userService.insert(user) > 0){
            return ResponseEntity.ok(new DefResponse(HttpStatus.OK));
        }

        return ResponseEntity.ok(new DefResponse(HttpStatus.BAD_REQUEST));
    }
    // READ
    
}
