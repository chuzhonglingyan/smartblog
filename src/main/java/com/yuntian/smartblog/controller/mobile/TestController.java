package com.yuntian.smartblog.controller.mobile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class TestController {



    @RequestMapping("map")
    public Map<String, String> map1() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "张三");
        map.put("age", "28");
        return map;
    }

}
