package com.yuntian.smartblog.exception;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import com.yuntian.smartblog.base.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Auther: yuntian
 * @Date: 2018/8/29 20:04
 * @Description: 全局异常捕获类
 */
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
                                                           HandlerMethod handlerMethod, Exception exception) {
        if (handlerMethod == null) {
            return null;
        }

        Method method = handlerMethod.getMethod();
        if (method == null) {
            return null;
        }

        StringBuffer errorMesg = new StringBuffer();
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

        logger.error(exception.getMessage(), exception);

        // 处理自定义异常将业务异常信息返回
        if (exception instanceof BusinessException) {
            errorMesg.append(exception.getMessage());
            code = ((BusinessException) exception).getCode();
        } else {//系统异常
            errorMesg.append("系统错误！");
        }

        if (errorMesg.length() == 0) {
            errorMesg.append("系统错误！");
        }

        boolean hasResponseBodyAnn = false;
        if (AnnotationUtils.findAnnotation(method, ResponseBody.class) != null) {
            hasResponseBodyAnn =true;
        }
        if (AnnotationUtils.findAnnotation(method.getDeclaringClass(), ResponseBody.class) != null) {
            hasResponseBodyAnn = true;
        }
        if (hasResponseBodyAnn) {
            Result result = new Result();
            result.setMsg(errorMesg.toString());
            result.setCode(code);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pWriter = null;
            try {
                pWriter = response.getWriter();
                pWriter.write(JSON.toJSONString(result));
            } catch (IOException e) {
                logger.error("response的json string 异常", e);
            } finally {
                if (pWriter != null) {
                    pWriter.flush();
                    pWriter.close();
                }
            }
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg", exception.getMessage());
            modelAndView.addObject("url", request.getRequestURL());
            modelAndView.addObject("stackTrace", Throwables.getStackTraceAsString(exception));
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }


}
