package com.yuntian.smartblog.interceptor;

import com.yuntian.common.api.annotation.LoginRequired;
import com.yuntian.common.base.cache.RedisKey;
import com.yuntian.common.base.cache.RedisUtil;
import com.yuntian.smartblog.exception.BusinessException;
import com.yuntian.smartblog.model.vo.UserAccountVo;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: yuntian
 * @Date: 2018/8/19 21:02
 * @Description: token登录拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {

    public final static String ACCESS_TOKEN = "token";
    public final static int NOVALID_TOKEN_CODE = 401;

    @Resource
    private RedisUtil redisUtil;

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
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if (methodAnnotation == null || methodAnnotation.value()) {
            // 判断Header是否存在令牌信息，如果存在，则允许登录
            String accessToken = request.getHeader(ACCESS_TOKEN);
            if (StringUtils.isBlank(accessToken)) {
                throw new BusinessException(NOVALID_TOKEN_CODE, "无效token，请重新登录");
            }
            //此处应该用缓存取
            UserAccountVo userAccountVo = (UserAccountVo) redisUtil.get(accessToken); //获取用户信息
            if (userAccountVo == null || userAccountVo.getId() <= 0) {
                throw new BusinessException(NOVALID_TOKEN_CODE, "无效token，请重新登录");
            }

            String orignToken = (String) redisUtil.get(RedisKey.getTokenKey(String.valueOf(userAccountVo.getId())));
            if (!StringUtils.equals(accessToken, orignToken)) {
                throw new BusinessException(NOVALID_TOKEN_CODE, "无效token，请重新登录");
            }
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