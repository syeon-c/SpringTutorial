package com.test1.b1.todo.controller.interceptor;

import com.test1.b1.common.annotations.JWTAuth;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class TodoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod method = (HandlerMethod) handler;

        log.info("-----------Todo interceptor handler----------");

        log.info(method);

        log.info("-----------Todo interceptor handler----------");

        JWTAuth authAnnotation = method.getMethodAnnotation(JWTAuth.class);
        log.info(authAnnotation);

        if (authAnnotation != null) {
            log.info("CHECK JWT ANNOTATION");
        }

        return true;

    }
}
