package com.source.code.springAOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LogAspect {

    @Pointcut("execution(* com.source.code.springAOP.CalculationImpl.*(..))")
    public void pointCut(){}

    @Before(value = "pointCut()")
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println(joinPoint.toString());
        System.out.println("===============[beforeMethod invoke!]====================");
    }

    @After(value = "pointCut()")
    public void afterMethod(JoinPoint joinPoint){
        System.out.println(joinPoint.toString());
        System.out.println("===============[afterMethod invoke!]====================");
    }

    @AfterReturning(value = "pointCut()")
    public void afterReturning(JoinPoint joinPoint){
        System.out.println(joinPoint.toString());
        System.out.println("===============[afterReturning invoke!]====================");
    }

    @AfterThrowing(value = "pointCut()")
    public void afterThrowing(JoinPoint joinPoint){
        System.out.println(joinPoint.toString());
        System.out.println("===============[afterThrowing invoke!]====================");
    }
}
