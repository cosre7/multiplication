package com.ts.multiplication;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.ts.multiplication..*(..))")
	private static void advicePoint() {}
	
	@Around("advicePoint()")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		logBefore(joinPoint);

		try {
			result = joinPoint.proceed();
			
		} catch (Exception e) {
			afterThrowing(joinPoint, e);
		}
		
		afterReturning(joinPoint, result);
		return result;
		
	}
	
	private void logBefore(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		logger.info("### Before - Method : " + methodSignature.toString());
		for (Object arg : joinPoint.getArgs()) {
			logger.info(arg.toString());
		}
	}
	
	private void afterReturning(JoinPoint joinPoint, Object returnValue) throws RuntimeException {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		logger.info("### AfterReturn - Method : " + methodSignature.toString());
		logger.info((String) returnValue);
	}
	
	private void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		logger.error("### AfterThrowing - Method : " + methodSignature.toString());
		logger.error(ex.getMessage());
	}
}
