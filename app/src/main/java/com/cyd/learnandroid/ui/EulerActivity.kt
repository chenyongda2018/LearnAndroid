package com.cyd.learnandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityEluerBinding

class EulerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mVb = ActivityEluerBinding.inflate(layoutInflater)
        setContentView(mVb.root)
        lifecycle.addObserver(mVb.axisView)
    }
}