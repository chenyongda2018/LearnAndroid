package com.cyd.learnandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityFragmentBinding

class FragmentActivity : AppCompatActivity() {

    private lateinit var mVb : ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVb = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(mVb.root)
    }
}