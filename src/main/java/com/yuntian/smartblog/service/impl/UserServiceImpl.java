package com.yuntian.smartblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntian.smartblog.base.Result;
import com.yuntian.smartblog.cache.RedisKey;
import com.yuntian.smartblog.cache.RedisUtil;
import com.yuntian.smartblog.dao.UserMapper;
import com.yuntian.smartblog.exception.BusinessException;
import com.yuntian.smartblog.model.entity.UserAccount;
import com.yuntian.smartblog.model.vo.PageInfoVo;
import com.yuntian.smartblog.model.vo.UserAccountVo;
import com.yuntian.smartblog.service.UserService;
import com.yuntian.smartblog.util.AssertUtil;
import com.yuntian.smartblog.util.PasswordUtil;
import com.yuntian.smartblog.util.TokenUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

import static com.yuntian.smartblog.base.Result.OK;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    RedisUtil redisUtil;

    @Resource
    UserMapper userMapper;

    @Override
    @Transactional
    public Result insertUser(UserAccount userAccount) {
        AssertUtil.isNotBlank(userAccount.getUserName(), "用户名为空");
        AssertUtil.isNotBlank(userAccount.getPassWord(), "密码为空");
        UserAccount userAccountTemp = userMapper.findUserByName(userAccount.getUserName());
        if (userAccountTemp != null) {
            throw new BusinessException("已经存在该用户");
        }

        userAccount.setPassWord(PasswordUtil.md5HexWithSalt(userAccount.getPassWord()));
        int resutStauts = userMapper.insertUser(userAccount);
        if (resutStauts <= 0) {
            throw new BusinessException("注册失败，请重新注册");
        }

        Result result = new Result();
        result.setMsg("注册成功");
        result.setCode(OK);
        return result;
    }

    @Override
    public Result login(UserAccount userAccount) {
        AssertUtil.isNotBlank(userAccount.getUserName(), "用户名为空");
        AssertUtil.isNotBlank(userAccount.getPassWord(), "密码为空");
        UserAccountVo userAccountVo = findUserByName(userAccount.getUserName());


        AssertUtil.isNotTrue(PasswordUtil.verify(userAccount.getPassWord(), userAccountVo.getPassWord()), "密码错误");

        String token = TokenUtil.createToken(String.valueOf(userAccount.getId()));
        userAccountVo.setToken(token);
        //清楚token
        String useIdKey = RedisKey.getTokenKey(String.valueOf(userAccountVo.getId()));
        String oldToken = (String) redisUtil.get(useIdKey);
        if (StringUtils.isNotBlank(oldToken)) {
            redisUtil.remove(oldToken);
        }
        if (StringUtils.isNotBlank(useIdKey)) {
            redisUtil.remove(useIdKey);
        }


        redisUtil.set(RedisKey.getTokenKey(String.valueOf(userAccountVo.getId())), token, RedisKey.TOKEN_EXPIRE);//缓存当前用户的token
        redisUtil.set(token, userAccountVo, RedisKey.TOKEN_EXPIRE);//缓存当前用户信息


        Result result = new Result();
        result.setMsg("登录成功");
        result.setData(userAccountVo);
        result.setCode(OK);
        return result;
    }

    @Override
    public Result loginOut(String token) {
        UserAccountVo userAccountVo = (UserAccountVo) redisUtil.get(token);
        AssertUtil.isNotNull(userAccountVo, "该用户信息不存在!");
        redisUtil.remove(RedisKey.getTokenKey(String.valueOf(userAccountVo.getId())));
        redisUtil.remove(token);
        Result result = new Result();
        result.setMsg("退出登录成功");
        result.setCode(OK);
        return result;
    }


    @Override
    public UserAccountVo findUserByName(String name) {
        AssertUtil.isNotBlank(name, "用户名为空");
        UserAccountVo user = userMapper.findUserByName(name);
        AssertUtil.isNotNull(user, "该用户未注册!");
        return user;
    }

    @Override
    public Result selectUserList(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserAccountVo> userDomains = userMapper.selectUserList();

        PageInfoVo pageInfoVo = new PageInfoVo(new PageInfo(userDomains));
        Result result = new Result();
        result.setMsg("成功");
        result.setCode(OK);
        result.setData(pageInfoVo);
        return result;
    }

    @Override
    public Result selectUserList(String pageNum, String pageSize) {
        int pageNumInt = 0;
        try {
            pageNumInt = Integer.valueOf(pageNum);
        } catch (NumberFormatException e) {
            throw new BusinessException("参数有问题");
        }

        int pageSizeInt = 10;
        try {
            pageSizeInt = Integer.valueOf(pageSize);
        } catch (NumberFormatException e) {
            throw new BusinessException("参数有问题");
        }

        return selectUserList(pageNumInt, pageSizeInt);
    }


    @Override
    public Result selectUserListByRedis(String pageNum, String pageSize) {
        int pageNumInt = 0;
        try {
            pageNumInt = Integer.valueOf(pageNum);
        } catch (NumberFormatException e) {
            throw new BusinessException("参数有问题");
        }

        int pageSizeInt = 10;
        try {
            pageSizeInt = Integer.valueOf(pageSize);
        } catch (NumberFormatException e) {
            throw new BusinessException("参数有问题");
        }

        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNumInt, pageSizeInt);
        List<UserAccountVo> userDomains = userMapper.selectUserList();

        PageInfoVo pageInfoVo = new PageInfoVo(new PageInfo(userDomains));

        // 具体使用
        redisUtil.lPush("list", JSON.toJSONString(userDomains));
        redisUtil.set("name", "angwu");


        Result result = new Result();
        result.setMsg("成功");
        result.setCode(OK);
        result.setData(pageInfoVo);
        return result;
    }


}
