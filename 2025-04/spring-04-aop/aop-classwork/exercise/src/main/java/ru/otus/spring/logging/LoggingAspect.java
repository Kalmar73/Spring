package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //@Before("within(ru.otus.spring.dao.PersonDaoSimple)")
    //@Before("execution(public ru.otus.spring.domain.Person ru.otus.spring.dao.PersonDaoSimple.*(String))")
    @Before("execution(public ru.otus.spring.domain.Person ru.otus.spring.dao.PersonDaoSimple.findByName(String))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
        System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());

        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
    }

    @Around("within(ru.otus.spring.dao.PersonDaoSimple)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
        System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
        var person = joinPoint.proceed();
        System.out.println("Выходной параметр : " + person);
        return person;
    }

}
