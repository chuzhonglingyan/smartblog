package com.yuntian.smartblog;

import com.yuntian.smartblog.util.AssertUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: yuntian
 * @Date: 2018/8/19 18:03
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {


    @Test
    public void  test1(){

        AssertUtil.isNotBlank("","字符窜为空");
    }

    @Test
    public void  test2(){

        AssertUtil.isNotNull(null,"对象为空");
    }
}
