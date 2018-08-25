package com.yuntian.smartblog.base;

public class Result<T> {


    public static  final  int OK=99;
    public static  final  int  FAILURE=0;


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
