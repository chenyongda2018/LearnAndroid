package com.cyd.demo.gesturedetector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.cyd.demo.R

class GestureActivity : AppCompatActivity() {

    val TAG  = "GestureActivity"

    lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture)
        gestureDetector = GestureDetector(this,object :GestureDetector.SimpleOnGestureListener() {
            override fun onShowPress(e: MotionEvent?) {
                Log.d(TAG,"onShowPress -> ${e?.toString()}")
                super.onShowPress(e)
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                Log.d(TAG,"onSingleTapConfirmed -> ${e?.toString()}")

                return super.onSingleTapConfirmed(e)
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                Log.d(TAG,"onSingleTapUp -> ${e?.toString()}")

                return super.onSingleTapUp(e)
            }

            override fun onDoubleTap(e: MotionEvent?): Boolean {
                Log.d(TAG,"onDoubleTap -> ${e?.toString()}")

                return super.onDoubleTap(e)
            }

            override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                Log.d(TAG,"onDoubleTapEvent -> ${e?.toString()}")

                return super.onDoubleTapEvent(e)
            }

            override fun onDown(e: MotionEvent?): Boolean {
                Log.d(TAG,"onDown -> ${e?.toString()}")

                return super.onDown(e)
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                Log.d(TAG,"onFling -> velocityX: ${velocityX}, velocityY: ${velocityY}")

                return super.onFling(e1, e2, velocityX, velocityY)
            }

            override fun onLongPress(e: MotionEvent?) {
                Log.d(TAG,"onLongPress -> ${e?.toString()}")

                super.onLongPress(e)
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                Log.d(TAG,"onScroll ->")

                return super.onScroll(e1, e2, distanceX, distanceY)
            }

            override fun onContextClick(e: MotionEvent?): Boolean {
                Log.d(TAG,"onContextClick -> ${e?.toString()}")

                return super.onContextClick(e)
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
}