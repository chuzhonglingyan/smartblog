package com.yuntian.smartblog.config;

import com.yuntian.smartblog.interceptor.SignInterceptor;
import com.yuntian.common.base.interceptor.RequestBodyInterceptor;
import com.yuntian.smartblog.interceptor.BackendLoginInterceptor;
import com.yuntian.smartblog.interceptor.TokenInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * @Auther: yuntian
 * @Date: 2018/8/18 17:09
 * @Description:
 */
@Configuration
public class WebMvcConfigurerAdapter extends BaseWebMvcConfigurerAdapter {


    /**
     * 配置静态访问资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //自定义项目内目录
        //registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
        //指向外部目录
        registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/");
//        super.addResourceHandlers(registry);
    }

    /**
     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/toLogin").setViewName("login");
//        super.addViewControllers(registry);
    }


    @Bean
    BackendLoginInterceptor BackendLoginInterceptor() {
        return new BackendLoginInterceptor();
    }


    @Bean
    TokenInterceptor TokenInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    RequestBodyInterceptor RequestBodyInterceptor() {
        return new RequestBodyInterceptor();
    }


    @Bean
    SignInterceptor SignInterceptor() {
        return new SignInterceptor();
    }


    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(BackendLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/error/**", "/api/**", "/user/**", "/assets/**", "/js/**");

        registry.addInterceptor(TokenInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("api/login", "api/register");

        registry.addInterceptor(RequestBodyInterceptor())
                .addPathPatterns("/api/**");


        registry.addInterceptor(SignInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("api/login", "api/register");
    }


}
