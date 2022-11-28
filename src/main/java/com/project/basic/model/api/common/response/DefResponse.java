package com.project.basic.model.api.common.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class DefResponse {
    private int code;
    private String message;

    public DefResponse(HttpStatus status){
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }
}
