package com.example.wanandroid.aspectj;


import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import androidx.annotation.NonNull;

//@Aspect
//public class ViewClickAspect {
//
//    private static final String TAG = ViewClickAspect.class.getSimpleName();
//
//
//    @Pointcut("execution(void android.view.View.OnClickListener.onClick(..))")
//    public void handleJavaClick() {
//    }
//
//    @Around("handleJavaClick()")
//    public void onViewClicked(ProceedingJoinPoint joinPoint) {
//        try {
//            Object[] args = joinPoint.getArgs();
//            View view = getViewFromArgs(args);
//            if (view == null) {
//                joinPoint.proceed();
//                return;
//            }
//            if ("com.android.internal.policy.DecorView".equals(view.getClass().getName())) {
//                return;
//            }
//            Log.d(TAG,"onclick");
//            joinPoint.proceed();
//
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//    }
//
//    private View getViewFromArgs(Object[] args) {
//        if (args != null && args.length > 0) {
//            Object arg = args[0];
//            if (arg instanceof View) {
//                return (View) arg;
//            }
//        }
//        return null;
//    }
//}