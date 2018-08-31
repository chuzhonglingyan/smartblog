package com.yuntian.smartblog.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: yuntian
 * @Date: 2018/8/29 20:57
 * @Description:
 */
@WebFilter(filterName = "myFilter", urlPatterns = {"/*"})
public class ApiFilter implements Filter {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("--------------过滤器初始化------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("--------------执行过滤操作------------");

        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("--------------过滤器销毁------------");
    }
}
