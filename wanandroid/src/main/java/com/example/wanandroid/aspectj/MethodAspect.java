package com.example.wanandroid.aspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @date: 2022/4/18
 * @author: chenyongda3
 * Description:
 */
@Aspect
public class MethodAspect {

    public static final String TAG = MethodAspect.class.getSimpleName();

    @Pointcut("call(* com.example.wanandroid.aspectj.Animal.fly(...))")
    public void callMethod() {}

    @Before("callMethod()")
    public void beforeMethodCall(JoinPoint joinPoint) {
        Log.d(TAG,"before ->" + joinPoint.getTarget().toString());
    }
}
