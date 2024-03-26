package com.kgc.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class CommonLogger {
    private Logger logger = Logger.getLogger(getClass());


    @Around(value = "execution(* com.kgc.service..*.*(..))|| execution(* com.kgc.controller..*.*(..))")
    public Object around(ProceedingJoinPoint jp) {
        Object result = null;
        try {
            logger.info("className:" + jp.getTarget() + "methodName:" + jp.getSignature() + "is start....Params:" + Arrays.toString(jp.getArgs()));
            result = jp.proceed();
            logger.debug("className:" + jp.getTarget() + "methodName:" + jp.getSignature() + "is end....Params:" + Arrays.toString(jp.getArgs()) + "result" + result);

        } catch (Throwable th) {
            logger.error("className:" + jp.getTarget() + "methodName:" + jp.getSignature() + "is start....Params:" + Arrays.toString(jp.getArgs()), th);
            throw new RuntimeException(th.getMessage());

        } finally {
            logger.debug("className:" + jp.getTarget() + "methodName:" + jp.getSignature() + "is end....Params:" + Arrays.toString(jp.getArgs()) + "result" + result);

        }
        return result;
    }
}
