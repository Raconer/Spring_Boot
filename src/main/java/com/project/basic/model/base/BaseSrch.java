package com.project.basic.model.base;

import com.project.basic.model.common.Paging;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class BaseSrch extends Paging {
    private String title;
}
