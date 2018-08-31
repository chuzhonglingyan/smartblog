package com.yuntian.smartblog.interceptor;

import com.yuntian.smartblog.annotation.SignRequired;
import com.yuntian.smartblog.exception.BusinessException;
import com.yuntian.smartblog.util.SignUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yuntian
 * @Date: 2018/8/19 21:02
 * @Description: token登录拦截器
 */
public class SignInterceptor implements HandlerInterceptor {

    public final static int NOVALID_SIGN_CODE = 408;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        SignRequired methodAnnotation = method.getAnnotation(SignRequired.class);
        if (methodAnnotation!=null&&methodAnnotation.value()) {
            if (!SignUtil.verifySign(request)) {
                throw new BusinessException(NOVALID_SIGN_CODE, "签名无效，请求无效");
            }
            logger.info("验证签名通过");
        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}