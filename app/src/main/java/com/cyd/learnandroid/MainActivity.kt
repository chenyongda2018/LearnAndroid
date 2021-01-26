package com.cyd.learnandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityMainBinding
import com.cyd.learnandroid.ui.CanvasActivity
import com.cyd.learnandroid.ui.FragmentActivity
import com.cyd.learnandroid.ui.IntentActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater);

        setContentView(mViewBinding.root)

        mViewBinding.intentBtn.setOnClickListener {
            startActivity(Intent(this, IntentActivity::class.java))
        }

        mViewBinding.fragmentBtn.setOnClickListener {
            startActivity(Intent(this,FragmentActivity::class.java))
        }
        
        mViewBinding.canvasBtn.setOnClickListener { 
            startActivity(Intent(this,CanvasActivity::class.java))
        }
    }

}