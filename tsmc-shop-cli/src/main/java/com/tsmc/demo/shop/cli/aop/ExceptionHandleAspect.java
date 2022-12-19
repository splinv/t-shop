package com.tsmc.demo.shop.cli.aop;

import com.tsmc.demo.shop.cli.annotations.HandleException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandleAspect {

    @Around("execution( * *.*(..)) && @annotation(catcher)")
    public Object handle(ProceedingJoinPoint pjp, HandleException catcher) throws Throwable {
        try {
            return pjp.proceed();
        }
        catch (Throwable e) {

            boolean matched = false;
            for(Class<? extends Throwable> klass : catcher.exceptions()) {
                if(!e.getClass().equals(klass)) {
                    continue;
                }

                System.out.println(e.getMessage());
                matched = true;
            }

            if(!matched) {
                throw e;
            }
        }

        return null;
    }
}

