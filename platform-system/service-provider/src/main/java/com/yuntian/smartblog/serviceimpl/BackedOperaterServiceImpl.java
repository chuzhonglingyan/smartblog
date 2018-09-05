package com.yuntian.smartblog.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuntian.smartblog.exception.BusinessException;
import com.yuntian.common.base.model.vo.Result;
import com.yuntian.smartblog.util.AssertUtil;

import com.yuntian.smartblog.mapper.BackedOperaterMapper;
import com.yuntian.smartblog.model.entity.BackedOperater;
import com.yuntian.smartblog.model.vo.PageInfoVo;
import com.yuntian.smartblog.service.BackedOperaterService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import static com.yuntian.common.base.model.vo.Result.OK;


@Service("backedOperaterService")
public class BackedOperaterServiceImpl implements BackedOperaterService {


    @Resource
    BackedOperaterMapper backedOperaterMapper;

    @Override
    public Result insertBackedOperater(BackedOperater backedOperater) {
        AssertUtil.isNotBlank(backedOperater.getUserName(), "用户名为空");
        AssertUtil.isNotBlank(backedOperater.getPassWord(), "密码为空");
        BackedOperater userAccountTemp = backedOperaterMapper.findBackedOperaterByName(backedOperater.getUserName());
        if (userAccountTemp != null) {
            throw new BusinessException("已经存在该用户");
        }
        int resutStatus = backedOperaterMapper.insertBackedOperater(backedOperater);
        if (resutStatus <= 0) {
            throw new BusinessException("注册失败，请重新注册");
        }
        Result result = new Result();
        result.setMsg("注册成功");
        result.setCode(OK);
        return result;
    }

    @Override
    public Result findBackedOperaterByNameAndPassWord(BackedOperater backedOperater) {
        AssertUtil.isNotBlank(backedOperater.getUserName(), "用户名为空");
        AssertUtil.isNotBlank(backedOperater.getPassWord(), "密码为空");

        BackedOperater backedOperater1 = backedOperaterMapper.findBackedOperaterByNameAndPassWord(backedOperater.getUserName(), backedOperater.getPassWord());
        AssertUtil.isNotNull(backedOperater1, "该用户未注册!");

        backedOperater1.setPassWord(null);
        Result result = new Result();
        result.setMsg("登录成功");
        result.setData(backedOperater1);
        result.setCode(OK);
        return result;
    }

    @Override
    public Result findBackedOperaterByName(String name) {
        AssertUtil.isNotBlank(name, "用户名为空");
        BackedOperater backedOperater = backedOperaterMapper.findBackedOperaterByName(name);
        AssertUtil.isNotNull(backedOperater, "该用户未注册!");

        Result result = new Result();
        result.setMsg("登录成功");
        result.setCode(OK);
        return result;
    }

    @Override
    public Result selectBackedOperaterList(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<BackedOperater> backedOperaterDomains = backedOperaterMapper.selectBackedOperaterList();

        PageInfoVo pageInfoVo = new PageInfoVo(new PageInfo(backedOperaterDomains));
        Result result = new Result();
        result.setMsg("成功");
        result.setCode(OK);
        result.setData(pageInfoVo);
        return result;
    }

    @Override
    public Result selectBackedOperaterList(String pageNum, String pageSize) {
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

        return selectBackedOperaterList(pageNumInt, pageSizeInt);
    }


}
