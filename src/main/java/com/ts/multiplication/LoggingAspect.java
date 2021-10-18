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

/*
 * - 객체지향 언어의 클래스와 비슷한 개념
 * - 그 자체로 애플리케이션 도메인 로직을 담은 핵심 기능은 아니지만, 많은 오브젝트에 걸쳐서 필요한 부가기능을 
 *   추상화해놓은 것
 * - 하나 이상의 포인트컷과 어드바이스로 구성
 * - 다양한 조합을 갖는 포인트컷과 어드바이스를 하나의 모듈로 정의할 수 있다.
 * */
@Component // 빈 자동 등록
@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* com.ts.multiplication..*.*(..))")
	private void advicePoint() {}
	
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
