package com.yuntian.smartblog.dao;

import com.yuntian.smartblog.model.entity.BackedOperater;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BackedOperaterMapper {


    public int insertBackedOperater(BackedOperater backedOperater);

    //    public void updateUser(UserAccount userAccount);
//
//    public void deleteUser(@Param("id") Long id);
//
//    public void findUserById(@Param("id") Long id);
//
//
    public BackedOperater findBackedOperaterByName(@Param("userName") String userName);

    public BackedOperater findBackedOperaterByNameAndPassWord(@Param("userName") String userName, @Param("passWord") String passWord);


    public List<BackedOperater> selectBackedOperaterList();

}
