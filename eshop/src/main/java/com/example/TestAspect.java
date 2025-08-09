package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	
	//執行到該行，便會啟動logback
	private static final Logger logger = LoggerFactory.getLogger(TestAspect.class);
	//必須指定當前類別名，以告訴logback要呈顯的資訊
	//但是，既然都在該類了，logback不能直接偵測現在就在該類？因會耗費效能

    @Before("execution(* com.example.service..*.*(..))|| execution(* com.example.dao..*.*(..))")
    //最左側*，表示任何回傳型別。
    //com.example..*，表示com.example(含)之下，所有的package
    //*(..)，表示任何的方法
    public void TestBefore(JoinPoint joinPoint) {
    	logger.info("位於"+joinPoint.getTarget().getClass().getSimpleName()+" ");
    	logger.info(joinPoint.getSignature().getName()+"即將執行...");
    }

    @After("execution(* com.example.service..*.*(..))|| execution(* com.example.dao..*.*(..))")
    public void TestAfter(JoinPoint joinPoint) {
    	logger.info("位於"+joinPoint.getTarget().getClass().getSimpleName()+" ");
    	logger.info(joinPoint.getSignature().getName()+"執行結束...");
    }
}
