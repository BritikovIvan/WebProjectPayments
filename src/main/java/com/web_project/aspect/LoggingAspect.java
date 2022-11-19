package com.web_project.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Log4j2
public class LoggingAspect {
    @Pointcut("within(com.web_project.controller.*) || within(com.web_project.service.impl.*)")
    public void controllerAndServicePointcut() {
    }

    @Around("controllerAndServicePointcut()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        var method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()";
        log.debug("Calling a method " + method);
        var start = System.nanoTime();
        var returnValue = joinPoint.proceed();
        var end = System.nanoTime();
        log.debug("The method " + method + " completed in " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        return returnValue;
    }
}
