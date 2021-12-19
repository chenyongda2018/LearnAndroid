package com.cyd.learnandroid.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import com.cyd.learnandroid.databinding.ActivityIntentBinding
import com.example.wanandroid.base.BaseActivity

class IntentActivity : BaseActivity<ActivityIntentBinding>() {

    override fun getViewBing(): ActivityIntentBinding? = ActivityIntentBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        bindCallIntent()

        bindMapIntent()

        bindWebIntent()

        bindEmailIntent()

        bindCalendar()
    }

    private fun bindCalendar() {
        val calendarIntent =
            Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
                val beginTime = Calendar.getInstance().apply {
                    set(2021, 1, 19, 22, 22)
                }
                val endTime = Calendar.getInstance().apply {
                    set(2021, 1, 20, 22, 22)
                }
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                putExtra(CalendarContract.Events.TITLE, "TEST")
                putExtra(CalendarContract.Events.EVENT_LOCATION, "xiao mi")
            }

        mVb?.calendarIntent?.setOnClickListener {
            startActivity(calendarIntent)
        }
    }

    private fun bindEmailIntent() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("1243724041@qq.com"))
            putExtra(Intent.EXTRA_SUBJECT,"email test")
            putExtra(Intent.EXTRA_TEXT,"email test text")
            putExtra(Intent.EXTRA_STREAM,Uri.parse("content://path/to/email/attachment"))
        }
        mVb?.emailIntent?.setOnClickListener {
            startActivity(emailIntent)
        }
    }

    private fun bindWebIntent() {
        val intent = Uri.parse("http://www.android.com").let { url ->
            Intent(Intent.ACTION_VIEW, url)
        }
        mVb?.webIntent?.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun bindMapIntent() {
        val mapIntent = Uri.parse(
            "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California"
        ).let { location ->
            Intent(Intent.ACTION_VIEW, location)
        }

        val activities = packageManager.queryIntentActivities(mapIntent,
            PackageManager.MATCH_DEFAULT_ONLY)


        mVb?.mapIntent?.setOnClickListener {
            if(activities.isNotEmpty()) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(this,"没有程序能够响应intent",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindCallIntent() {
        val callIntent = Uri.parse("tel:10086").let { number ->
            Intent(Intent.ACTION_DIAL, number)
        }
        mVb?.callIntent?.setOnClickListener {
            startActivity(callIntent)
        }
    }
}