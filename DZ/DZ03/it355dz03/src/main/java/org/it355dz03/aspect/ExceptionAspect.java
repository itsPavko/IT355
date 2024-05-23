package org.it355dz03.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class ExceptionAspect {

    @Before("execution(public double org.it355dz03.service.impl.*.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("Poziva se BEFORE: " + joinPoint.getSignature());
    }

    @After("execution(* org.it355dz03.service.impl.*.*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("Poziva se AFTER: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* org.it355dz03.service.impl.*.*(..))")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("Poziva se AFTERRETURNING: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(* org.it355dz03.service.impl.*.*(..))")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("Poziva se AFTERTHROWING: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* org.it355dz03.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Poziva se AROUND " + proceedingJoinPoint.getSignature().getName());
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("Izuzetak u metodi " + proceedingJoinPoint.getSignature());
            throw e;
        }
        System.out.println("Zavrsio se AROUND " + proceedingJoinPoint.getSignature().getName());
        return result;
    }


}
