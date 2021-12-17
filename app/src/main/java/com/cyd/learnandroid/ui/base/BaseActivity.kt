package com.cyd.learnandroid.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @date: 2021/12/13
 * @author: chenyongda3
 * Description:
 */
abstract class BaseActivity<VB : ViewBinding>: AppCompatActivity() {

    protected var  mVb : VB? = null

    abstract fun getViewBing(): VB?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVb = getViewBing()
        setContentView(mVb?.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        mVb = null
    }

}