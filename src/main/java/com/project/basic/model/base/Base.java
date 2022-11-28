package com.project.basic.model.base;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Base {
    private int id;
    @NotEmpty
    private String title;
}
