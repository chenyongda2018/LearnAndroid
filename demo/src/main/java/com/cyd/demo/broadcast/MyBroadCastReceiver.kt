package com.cyd.demo.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

/**
 * @date: 2022/8/23
 * @author: chenyongda3
 * Description:
 */
class MyBroadCastReceiver : BroadcastReceiver() {
    private val TAG = "MyBroadCastReceiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"hhh",Toast.LENGTH_SHORT).show()
        Log.d(TAG, this.toString())
    }
}