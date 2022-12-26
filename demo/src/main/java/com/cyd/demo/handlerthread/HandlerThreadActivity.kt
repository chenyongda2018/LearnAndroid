package com.cyd.demo.handlerthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import com.cyd.demo.R

class HandlerThreadActivity : AppCompatActivity() {

    val TAG = "HandlerThreadActivityTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_thread)
        val handlerThread = HandlerThread("my_handler_thread")
        handlerThread.start()
        val workHandler = Handler(handlerThread.looper) { msg ->
            Log.d(TAG, "workHandler -> msg: ${msg.what}, cur thread is ${Thread.currentThread().name}")
            true
        }
        val msg = Message.obtain().apply {
            this.what = 123
        }
        workHandler.sendMessage(msg)


    }
}