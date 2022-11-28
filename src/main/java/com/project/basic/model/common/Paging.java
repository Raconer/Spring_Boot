package com.project.basic.model.common;

import lombok.Data;

@Data
public class Paging {
    private int page = 1;
    private int startRow;
    private int total = 0;
    private int pageSize = 10;
    private int pages;

    public void setPage(int page){
        if(page < 1){
            this.page = 1;
        }else{
            this.page = page;
        }
    }

    public void setPageInfo(){
        this.pages = this.total/this.pageSize + (this.total%this.pageSize > 0?1:0);
        this.startRow = (this.page-1) * this.pageSize;
    }    
}
