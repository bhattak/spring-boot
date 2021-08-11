package com.example.demo.springAOP;

import com.example.demo.exception.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class Helper {

//    @Before("execution(* com.example.demo.springAOP..*(..)) and args(id)")
//    public void log1(JoinPoint joinPoint, Integer id) {
//        System.err.println("Before executing the function ....");
//        System.out.println("Join Point signature : " + joinPoint.getSignature());
//        System.out.println("Path variable is : " + id);
//    }
//
//    @After("execution(* com.example.demo.springAOP..*(..)) ")
//    public void log2() {
//        System.err.println("After executing the function ....");
//    }
//

//    @Before("execution( * aop*(..))")
//    public void beforeCheck(JoinPoint joinPoint) {
//        System.out.println("Join Point signature : " + joinPoint.getSignature());
//    }
//    @After("execution( * aop*(..))")
//    public void afterCheck(JoinPoint joinPoint) {
//        System.err.println("After executing the function ....");
//    }


    @AfterReturning(pointcut = "execution( * aop*(..))", returning = "value")
    public void log3(JoinPoint joinPoint, Integer value) {
        System.out.println("Join Point signature : " + joinPoint.getSignature());
        System.out.println("Return value is : " + value);
    }

    @AfterThrowing(pointcut = "execution( * aop*(..))", throwing = "exception")
    public void expHandler( NotFoundException exception) {
//      System.out.println("Join Point signature : " + joinPoint.getSignature());
        System.out.println("Exception  is : " + exception);
    }

}
