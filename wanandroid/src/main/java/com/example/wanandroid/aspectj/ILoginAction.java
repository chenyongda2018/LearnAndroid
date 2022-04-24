package com.example.wanandroid.aspectj;

import android.content.Context;

/**
 * @date: 2022/4/19
 * @author: chenyongda3
 * Description:
 */
public interface ILoginAction {
    //登陆操作
    void login(Context context,int actionType);

    //是否登陆
    boolean isLogin(Context context);
}
