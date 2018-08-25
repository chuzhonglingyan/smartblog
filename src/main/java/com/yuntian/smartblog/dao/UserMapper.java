package com.yuntian.smartblog.dao;

import com.yuntian.smartblog.model.entity.UserAccount;
import com.yuntian.smartblog.model.vo.UserAccountVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {


    public int insertUser(UserAccount userAccount);

    //    public void updateUser(UserAccount userAccount);
//
//    public void deleteUser(@Param("id") Long id);
//
//    public void findUserById(@Param("id") Long id);
//
//
    public UserAccountVo findUserByName(@Param("userName") String userName);





    public List<UserAccountVo> selectUserList();

}
