package com.cyd.learnandroid.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityMainBinding
import com.cyd.learnandroid.databinding.ActivityMaskBinding

class MaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var mVb = ActivityMaskBinding.inflate(layoutInflater)
        setContentView(mVb.root)
    }
}