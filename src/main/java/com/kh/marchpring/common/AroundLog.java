package com.kh.marchpring.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Component
@Aspect
public class AroundLog {
	
	@Pointcut("execution(* com.kh.marchpring..*Impl.*(..))")
	public void serviceImplPointCut() {}

	@Around("execution(* com.kh.marchpring..*Impl.*(..))")
	public Object checkTimeMethod(ProceedingJoinPoint pjp) throws Throwable {
		// stopWatch 객체 사용
		StopWatch stopWatch = new StopWatch();
		Object obj = null;
		
		stopWatch.start();
		// 메소드 실행(대상 메소드) -> 을 가져오기 위한 인터페이스 : ProceedingJoinPoint
		obj = pjp.proceed();	// pointcut 적용 시점의 메소드
		// around를 통해 obj를 기준으로 로직이 타겟메소드 전에 한 번, 후에 한 번 실행된다.
		// ( root-context.xml 파일의 <aop:around method="checkTimeMethod" pointcut-ref="serviceImplPointCut"/> )
		stopWatch.stop();
		
		// 메소드명 확인
		String methodName = pjp.getSignature().getName();
		System.out.println("메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)");
		
		return obj;
	}
}
