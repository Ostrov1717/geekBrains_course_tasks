package org.example.lesson16;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class DetailedLoggingAspect {

    @Pointcut("execution(* org.example.lesson16.MyService.doSomething(String))")
    public void myServiceDoSomethingPointcut() {}

    @Before("myServiceDoSomethingPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("   -> 2. @Before: Выполняется ДО метода. Аргументы: {}",
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "myServiceDoSomethingPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("   <- 4. @AfterReturning: Выполняется ПОСЛЕ УСПЕХА. Результат: {}", result);
    }

    @AfterThrowing(pointcut = "myServiceDoSomethingPointcut()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.info("   <- 4. @AfterThrowing: Выполняется ПОСЛЕ ОШИБКИ. Исключение: {}",
                error.getMessage());
    }

    @After("myServiceDoSomethingPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("   <-> 5. @After: Выполняется В ЛЮБОМ СЛУЧАЕ (finally).");
    }

    @Around("myServiceDoSomethingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(">>> 1. @Around: Вход (до @Before и метода)");
        long startTime = System.currentTimeMillis();

        Object result;
        try {
            result = joinPoint.proceed();

        } catch (Throwable e) {
            log.info("<<< 6. @Around: Перехвачено исключение (до @AfterThrowing/After)");
            throw e;
        }

        long duration = System.currentTimeMillis() - startTime;
        log.info("<<< 7. @Around: Выход (после @AfterReturning/After). Длительность: {}ms", duration);

        return result;
    }
}
