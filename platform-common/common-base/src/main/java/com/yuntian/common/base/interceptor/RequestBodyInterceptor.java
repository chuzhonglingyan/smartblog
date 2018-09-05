package com.yuntian.common.base.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuntian.common.base.filter.BodyReaderHttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yuntian
 * @Date: 2018/8/29 21:01
 * @Description: * 自定义拦截器
 * 拦截时机 Filter pre -> serviceimpl -> dispatcher -> preHandle ->controller
 * ->postHandle - > afterCompletion -> FilterAfter
 */
public class RequestBodyInterceptor implements HandlerInterceptor {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 在controller处理之前首先对请求参数进行处理，以及对公共参数的保存
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getContentType() == null) {
            return true;
        }
        if (!request.getContentType().contains("application/json")) {
            return true;
        }
        logger.info("---------------拦截器开始------------------");
        try {
            response.setHeader("Content-type", "application/json;charset=UTF-8");

            String requestMethord = request.getRequestURI();//请求方法
            if (requestMethord == null) {
                return false;
            }
            //获取请求参数
            JSONObject parameterMap = JSON.parseObject(new BodyReaderHttpServletRequestWrapper(request).getBodyString(request));

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("拦截器拦截完成");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("---------------拦截器方法二开始------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("---------------拦截器方法三开始------------------");
    }
}
