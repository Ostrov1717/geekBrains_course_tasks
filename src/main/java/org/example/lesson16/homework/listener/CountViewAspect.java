package org.example.lesson16.homework.listener;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CountViewAspect {

    private final ApplicationEventPublisher eventPublisher;

    @Around("@annotation(org.example.lesson16.homework.CountView)")
    public Object trackView(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof Product product) {
            eventPublisher.publishEvent(new ProductViewedEvent(this, product));
        }

        return result;
    }
}
