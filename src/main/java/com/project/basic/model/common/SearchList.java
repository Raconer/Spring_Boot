package com.project.basic.model.common;

import java.util.List;

import lombok.Data;

@Data
public class SearchList<E> {
    private int pageNum;
    private int total;
    private List<E> list;

    public SearchList(Object pageInfo, List<E> list){
        Paging searchOpt = (Paging) pageInfo;
        this.pageNum = searchOpt.getPage();
        this.total = searchOpt.getTotal();
        this.list = list;
    }
}