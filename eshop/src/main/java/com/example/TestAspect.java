package com.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    @Before("execution(* com.example.service..*.*(..))|| execution(* com.example.dao..*.*(..))")
    //最左側*，表示任何回傳型別。
    //com.example..*，表示com.example(含)之下，所有的package
    //*(..)，表示任何的方法
    public void TestBefore(JoinPoint joinPoint) {
    	System.out.print("位於"+joinPoint.getTarget().getClass().getSimpleName()+",");
        System.out.println(joinPoint.getSignature().getName()+"即將執行...");
    }

    @After("execution(* com.example.service..*.*(..))|| execution(* com.example.dao..*.*(..))")
    public void TestAfter(JoinPoint joinPoint) {
    	System.out.print("位於"+joinPoint.getTarget().getClass().getSimpleName()+",");
        System.out.println(joinPoint.getSignature().getName()+"執行結束...");
    }
}
