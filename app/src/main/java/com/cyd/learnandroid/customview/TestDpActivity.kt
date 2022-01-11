package com.cyd.learnandroid.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cyd.learnandroid.R

class TestDpActivity : AppCompatActivity() {

    companion object {
        const val TAG = "TestDpActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dp)

        Log.d(TAG,"density: ${resources.displayMetrics.density}")
        Log.d(TAG,"dpi: ${resources.displayMetrics.densityDpi}")

        val a1 = resources.getDimension(R.dimen.dp_16)
        val a2 = resources.getDimensionPixelOffset(R.dimen.dp_16)
        val a3 = resources.getDimensionPixelSize(R.dimen.dp_16)

        Log.d(TAG,"$a1 , $a2 , $a3")

        val b1 = resources.getDimension(R.dimen.sp_16)
        val b2 = resources.getDimensionPixelOffset(R.dimen.sp_16)
        val b3 = resources.getDimensionPixelSize(R.dimen.sp_16)

        Log.d(TAG,"$b1 , $b2 , $b3")


        val c1 = resources.getDimension(R.dimen.px_16)
        val c2 = resources.getDimensionPixelOffset(R.dimen.px_16)
        val c3 = resources.getDimensionPixelSize(R.dimen.px_16)

        Log.d(TAG,"$c1 , $c2 , $c3")




    }
}