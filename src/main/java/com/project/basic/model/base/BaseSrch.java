package com.project.basic.model.base;

import com.project.basic.model.common.Paging;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseSrch extends Paging {
    private String title;
}
