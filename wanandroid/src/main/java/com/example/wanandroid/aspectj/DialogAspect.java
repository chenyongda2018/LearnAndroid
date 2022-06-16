package com.example.wanandroid.aspectj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.Toast;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @date: 2022/6/15
 * @author: chenyongda3
 * Description:
 * Fix Error
 * https://stackoverflow.com/questions/7811993/error-binderproxy45d459c0-is-not-valid-is-your-activity-running
 */
@Aspect
public class DialogAspect {

    public static final String TAG = DialogAspect.class.getSimpleName();

    @Pointcut("call(* android.app.Dialog.show(..))")
    public void pointCutDialog() {
    }

    @Pointcut("call(* android.widget.Toast.show(..))")
    public void pointCutToast() {
    }

    @Around("pointCutDialog()||pointCutToast()")
    public void aroundPoint(ProceedingJoinPoint joinPoint) {
        try {
            Log.d(TAG, "getTarget ->" + joinPoint.getTarget());
            Object target = joinPoint.getTarget();
            if (target instanceof AlertDialog) {
                if (curActivityActive((AlertDialog) target)) {
                    joinPoint.proceed();
                    return;
                }
            }
            if(target instanceof Toast) {
                joinPoint.proceed();
            }
        } catch (Throwable e) {
            Log.e(TAG, "error ->" + e.getMessage());
        }
    }


    private boolean curActivityActive(AlertDialog dialog) {
        Context ctx = dialog.getContext();
        if (ctx instanceof ContextThemeWrapper) {
            Context baseCtx = ((ContextThemeWrapper) ctx).getBaseContext();
            if (baseCtx instanceof Activity) {
                return !((Activity) baseCtx).isFinishing();
            }
        }
        return true;
    }
}
