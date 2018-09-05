package com.yuntian.smartblog.model.dto;

import java.io.Serializable;

/**
 * @Auther: yuntian
 * @Date: 2018/8/29 20:11
 * @Description:
 */
public class UserPageDTO implements Serializable {


    private static final long serialVersionUID = -2830055403768415170L;
    private int pageNum=0;

    private int pageSize=10;


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
