package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class BankAccountAspect {

    @Pointcut("execution(* org.example.service.BankService.*(..))")
    private void bankAccountPointCut() {
    }

    @Before("bankAccountPointCut()")
    private void adviceBefore(JoinPoint joinPoint) {
        System.out.println(">>> before");
        var methodName = joinPoint.getSignature().getName();
        var args = Arrays.toString(joinPoint.getArgs());
        System.out.println(methodName + " " + args);
    }

    @After("bankAccountPointCut()")
    private void afterAdvice(JoinPoint joinPoint) {
        System.out.println(">>> after");
    }

    //    most powerful aspect
    @Around("bankAccountPointCut()")
    private void aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Around start");
        try {
            pjp.proceed();
        } catch (Exception e) {
        }
        System.out.println("Around end");
    }

    @AfterThrowing(value = "bankAccountPointCut()", throwing = "exception")
    private void afterThrowingAdvice(JoinPoint joinPoint,
                                     Exception exception) {

        System.out.println("after throwing advice " +
                exception.getMessage());
    }

    @AfterReturning(value = "bankAccountPointCut()",
            returning = "number")
    private void afterReturningAdvice(JoinPoint joinPoint, Integer number) {
        System.out.println("after returning advice " + number);
    }
}
