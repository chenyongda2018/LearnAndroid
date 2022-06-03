package com.cyd.demo.lottie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.cyd.demo.R

class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie)
        val lottieView = findViewById<LottieAnimationView>(R.id.lottie_anim)
        lottieView.playAnimation()
    }
}