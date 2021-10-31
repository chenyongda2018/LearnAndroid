package com.cyd.learnandroid.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.R
import com.cyd.learnandroid.databinding.ActivitySurfaceViewBinding

class SurfaceViewActivity : AppCompatActivity() {
    var mViewBinding: ActivitySurfaceViewBinding ? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivitySurfaceViewBinding.inflate(layoutInflater)
        setContentView(mViewBinding?.root)
    }
}