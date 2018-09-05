package com.yuntian.smartblog.service;


import com.yuntian.common.base.model.vo.Result;
import com.yuntian.smartblog.model.entity.UserAccount;
import com.yuntian.smartblog.model.vo.UserAccountVo;

public interface UserService {

    public Result insertUser(UserAccount userAccount);


    public Result login(UserAccount userAccount);

    public Result loginOut(String token);

    public UserAccountVo findUserByName(String name);


    Result selectUserList(String pageNum, String pageSize);

    Result selectUserList(int pageNum, int pageSize);


    public Result selectUserListByRedis(String pageNum, String pageSize);

}
