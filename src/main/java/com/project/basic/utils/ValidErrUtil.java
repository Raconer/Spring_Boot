package com.project.basic.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import com.project.basic.model.api.common.response.DefDataResponse;
import com.project.basic.model.api.common.validate.Validate;
import com.project.basic.model.common.code.ValidateCode;

public class ValidErrUtil {
    public static ResponseEntity<?> getValidateError(List<FieldError> errors){
        List<Validate> validates = new ArrayList<>();
        for(FieldError error : errors){
            Validate validate = new Validate(error.getField(), error.getCode());
            validates.add(validate);
        }
        return ResponseEntity.ok(new DefDataResponse(HttpStatus.BAD_REQUEST, validates));
    }

    public static ResponseEntity<?> getValidateError(String field, ValidateCode validType){
        List<Validate> validates = new ArrayList<>();
        Validate validate = new Validate(field, validType.name());
        validates.add(validate);
        return ResponseEntity.ok(new DefDataResponse(HttpStatus.BAD_REQUEST, validates));
    }
}
