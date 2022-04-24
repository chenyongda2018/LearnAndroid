package com.example.wanandroid.aspectj;

import android.util.Log;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.AnnotationFormatError;

/**
 * @date: 2022/4/19
 * @author: chenyongda3
 * Description:
 */
@Aspect
public class LoginAspect {

    public static final String TAG = LoginAspect.class.getSimpleName();

    @Pointcut("execution(@com.example.wanandroid.aspectj.RequireLogin * *(..))")
    public void point(){}

    @Around("point()")
    public void aroundLoginPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        if(!(signature instanceof MethodSignature)) {
            throw new AnnotationFormatError("@requireLogin 只能注解方法");
        }

        MethodSignature methodSignature = (MethodSignature) signature;
        RequireLogin requireLogin = methodSignature.getMethod().getAnnotation(RequireLogin.class);
        if(requireLogin == null) {
            return;
        }

        if(LoginHelper.INSTANCE.isLogin()) {
            joinPoint.proceed();
        } else {
            Log.d(TAG,"没有登陆");
        }
    }
}
