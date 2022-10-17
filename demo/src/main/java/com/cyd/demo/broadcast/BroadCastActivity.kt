package com.cyd.demo.broadcast

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.demo.R
import com.cyd.demo.databinding.ActivityBroadCastBinding

class BroadCastActivity : AppCompatActivity() {

    private var _binding: ActivityBroadCastBinding? = null
    private val binding get() = _binding!!
    val br: MyBroadCastReceiver = MyBroadCastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBroadCastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val intent = Intent("helloworld").apply {
                setClassName(this@BroadCastActivity,"${packageName}.broadcast.MyBroadCastReceiver")
            }
            sendBroadcast(intent)
        }

        val filter = IntentFilter("helloworld")
//        registerReceiver(br,filter)
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(br)
    }
}