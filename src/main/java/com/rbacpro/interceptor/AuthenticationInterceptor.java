package com.rbacpro.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Value("${root.token}")
    private String rootToken;

    public static final String ORG = "organization";
    private static final Logger logger = LoggerFactory
            .getLogger(AuthenticationInterceptor.class);

    //TODO: This should perform check on the token in the cookies.
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Setting organization attribute in request");
        // TODO: should have got this from token in header and perform internal lookup.
        request.setAttribute(ORG, "taest");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}