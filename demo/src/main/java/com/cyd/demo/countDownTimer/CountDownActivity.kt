package com.cyd.demo.countDownTimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.cyd.demo.databinding.ActivityCountDownBinding

class CountDownActivity : AppCompatActivity() {

    private var _binding: ActivityCountDownBinding? = null
    private val binding get() = _binding!!

    private val mTimer: CountDownTimer = object: CountDownTimer(60*1000L,1000L) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timerTv.text = "${millisUntilFinished / 1000}后重新输入"
        }

        override fun onFinish() {
            binding.timerTv.text = "重新获取"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.timerBtn.setOnClickListener {
            mTimer.start()
        }
    }

}