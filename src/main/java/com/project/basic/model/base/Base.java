package com.project.basic.model.base;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Base {
    private Integer id;
    @NotEmpty
    private String title;
    private String content;
}
