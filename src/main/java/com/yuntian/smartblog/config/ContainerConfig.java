package com.yuntian.smartblog.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/**
 * @Auther: yuntian
 * @Date: 2018/8/18 17:07
 * @Description: 错误页面配置类
 */
@Configuration
public class ContainerConfig implements ErrorPageRegistrar {




    /**
     * 功能描述: 
     * @param:
     * @return: 
     * @auther: yuntian
     * @date: 2018/8/18 17:27
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[2];
        errorPages[0] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
        errorPages[1] = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        registry.addErrorPages(errorPages);
    }

}
