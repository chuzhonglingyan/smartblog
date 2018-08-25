package com.yuntian.smartblog.service;

import com.yuntian.smartblog.base.Result;
import com.yuntian.smartblog.model.entity.BackedOperater;

public interface BackedOperaterService {

    public Result insertBackedOperater(BackedOperater backedOperater);

    public Result findBackedOperaterByNameAndPassWord(BackedOperater backedOperater);

    public Result findBackedOperaterByName(String name);


    Result selectBackedOperaterList(String pageNum, String pageSize);

    Result selectBackedOperaterList(int pageNum, int pageSize);



}
