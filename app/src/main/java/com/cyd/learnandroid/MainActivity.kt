package com.cyd.learnandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.cyd.learnandroid.databinding.ActivityMainBinding
import com.cyd.learnandroid.customview.CanvasActivity
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
            startActivity(Intent(this, CanvasActivity::class.java))
        }

        //设置沉浸式虚拟键，在MIUI系统中，虚拟键背景透明。原生系统中，虚拟键背景半透明。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

}