package com.yuntian.common.base.model.vo;

import java.io.Serializable;

public class Result<T> implements Serializable {


    public static  final  int OK=99;
    public static  final  int  FAILURE=0;
    private static final long serialVersionUID = 5345138249091326327L;


    private String msg;
    private int code;

    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
