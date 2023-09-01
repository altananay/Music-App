package com.atmosware.musicapp.core.aspects;

import com.atmosware.musicapp.common.utils.annotations.Logger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAspect {

  @Around("@annotation(logger)")
  public Object log(ProceedingJoinPoint joinPoint, Logger logger) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    Map<String, Object> parameters = obtainParameters(joinPoint);
    log.info("{} started with parameters: {}", methodName, parameters);
    Object proceed;
    try {
      proceed = joinPoint.proceed();
    } catch (Throwable exception) {
      log.error("{} failed with exception message: {}", methodName, exception.getMessage());
      throw exception;
    }
    log.info("{} finished with return value: {}", methodName, proceed);
    return proceed;
  }


  private Map<String, Object> obtainParameters(ProceedingJoinPoint joinPoint) {
    Map<String, Object> parameters = new HashMap<>();
    String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
    Object[] parameterValues = joinPoint.getArgs();
    for (int i = 0; i < parameterNames.length && i < parameterValues.length; i++) {
      parameters.put(parameterNames[i], parameterValues[i]);
    }
    return parameters;
  }
}