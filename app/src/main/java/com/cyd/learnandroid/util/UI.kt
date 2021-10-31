package com.cyd.learnandroid.util

import android.content.res.Resources

/**
 * Created by cyd on 2021/1/26.
 */
object UI {

    fun getScreenWidth() : Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight() : Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

}