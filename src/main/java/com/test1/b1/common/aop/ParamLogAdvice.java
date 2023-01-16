package com.test1.b1.common.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class ParamLogAdvice {

    @Before("execution(* com.test1.b1.todo.service.*Service.*(..))")
    public void logBefore(JoinPoint joinPoint) {

        log.info("-----------------pointcut-----------------");
        log.info(Arrays.toString(joinPoint.getArgs()));
        log.info("-----------------pointcut-----------------");
        log.info(joinPoint.getSignature());

    }

    @Around("execution(* com.test1.b1.todo.service.*Service.*(..))")
    public Object checkTime(ProceedingJoinPoint pjp) throws Throwable {

        log.info("-----------------around-----------------");
        log.info(pjp.getArgs());

        long start = System.currentTimeMillis();

        Object result = null;
        // invoke()
        result = pjp.proceed();

        long end = System.currentTimeMillis();

        log.info("-----------------check time-----------------");
        log.info(end - start);

        log.info(result);

        return result;

    }


}
