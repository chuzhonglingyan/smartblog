package com.yuntian.common.base.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author zhangxd
 */
public class Paging<T> implements Serializable {


    private static final long serialVersionUID = -2860823249996515002L;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 每页条数
     */
    private int pageSize;
    /**
     * 排序字段
     */
    private String orderBy;

    private List<T> list;

    public Paging() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public Paging(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Paging(int pageNum, int pageSize, String orderBy) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
