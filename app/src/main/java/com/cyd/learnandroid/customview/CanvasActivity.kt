package com.cyd.learnandroid.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityCanvasBinding

class CanvasActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityCanvasBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityCanvasBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)

        mViewBinding.eulerView.setOnClickListener {
            startActivity(Intent(this, EulerActivity::class.java))
        }

        mViewBinding.maskView.setOnClickListener {
            startActivity(Intent(this, MaskActivity::class.java))
        }

        mViewBinding.morph.setOnClickListener {
            startActivity(Intent(this, PostFabActivity::class.java))
        }

        mViewBinding.surfaceView.setOnClickListener {
            startActivity(Intent(this,SurfaceViewActivity::class.java))
        }

        mViewBinding.marqueeIntent.setOnClickListener {
            startActivity(Intent(this,MarqueeActivity::class.java))
        }
    }
}