package com.cyd.learnandroid.ui.wanAnd

import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityWanAndBinding
import com.cyd.learnandroid.ui.base.BaseActivity

class WanAndActivity : BaseActivity() {

    private lateinit var mViewBinding: ActivityWanAndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityWanAndBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        setSupportActionBar(mViewBinding.toolBar)
    }
}