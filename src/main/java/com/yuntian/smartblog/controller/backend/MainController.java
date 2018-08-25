package com.yuntian.smartblog.controller.backend;

import com.yuntian.smartblog.controller.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController extends AbstractController {


    @RequestMapping("index")
    public String index(HttpSession session) {
        return "index";
    }

}
