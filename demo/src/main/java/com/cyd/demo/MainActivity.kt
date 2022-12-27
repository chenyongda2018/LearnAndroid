package com.cyd.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cyd.demo.broadcast.BroadCastActivity
import com.cyd.demo.countDownTimer.CountDownActivity
import com.cyd.demo.gesturedetector.GestureActivity
import com.cyd.demo.handlerthread.HandlerThreadActivity
import com.cyd.demo.lottie.LottieActivity
import com.cyd.demo.navigation.NavigationActivity
import com.cyd.demo.room.RoomActivity
import com.cyd.demo.rxjava.RxJavaActivity
import com.cyd.demo.table.TableActivity
import com.example.annotation.Test

@Test
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentList = mutableListOf<MainIntentBean>(
            MainIntentBean("GestureDetector", Intent(this,GestureActivity::class.java)),
            MainIntentBean("HandlerThread", Intent(this,HandlerThreadActivity::class.java)),
            MainIntentBean("Lottie", Intent(this,LottieActivity::class.java)),
            MainIntentBean("Jetpack Navigation", Intent(this,NavigationActivity::class.java)),
            MainIntentBean("RxJava", Intent(this,RxJavaActivity::class.java)),
            MainIntentBean("Room", Intent(this,RoomActivity::class.java)),
            MainIntentBean("CountDownTimer", Intent(this,CountDownActivity::class.java)),
            MainIntentBean("BroadCast", Intent(this,BroadCastActivity::class.java)),
            MainIntentBean("Table", Intent(this, TableActivity::class.java)),
        )

        val rv = findViewById<RecyclerView>(R.id.main_rv).apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            this.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))
        }
        rv.adapter = MainListAdapter(this,intentList) {bean: MainIntentBean, position: Int ->
            startActivity(bean.intent)
        }
    }
}



