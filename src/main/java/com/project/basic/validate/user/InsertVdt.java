package com.project.basic.validate.user;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mysql.cj.util.StringUtils;
import com.project.basic.model.common.code.ValidateCode;
import com.project.basic.model.user.UserInfo;
import com.project.basic.repository.UserMapper;

@Component
public class InsertVdt implements Validator{

    UserMapper userMapper;

    UserInfo user;

    public InsertVdt(UserMapper userMapper){
        this.userMapper = userMapper;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return UserInfo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        user = (UserInfo) target;

        UserInfo registered = userMapper.get(user);
       
        if(registered != null){
            errors.rejectValue("email", "Already Registered User!");
        }else{
            if(StringUtils.isEmptyOrWhitespaceOnly(user.getEmail())){
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", ValidateCode.EMPTY.name());
            }
    
            if(StringUtils.isEmptyOrWhitespaceOnly(user.getPassword())){
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", ValidateCode.EMPTY.name());
            }
    
            if(StringUtils.isEmptyOrWhitespaceOnly(user.getUsername())){
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", ValidateCode.EMPTY.name());
            }
        }
    }
}
