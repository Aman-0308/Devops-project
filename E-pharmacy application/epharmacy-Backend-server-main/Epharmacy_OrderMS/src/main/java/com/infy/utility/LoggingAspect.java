package com.infy.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.infy.service.*.*(..))", throwing = "exception")
    public void logServiceExceptions(Exception exception) {
        logger.error("Exception occurred in service layer: {}", exception.getMessage(), exception);
    }
}
