package com.cyd.learnandroid.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.cyd.learnandroid.R
import kotlin.random.Random

/**
 * Created by cyd on 2021/1/27.
 */
class MaskView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mFaceBitmap = ContextCompat.getDrawable(context, R.drawable.ic_baseline_face_24)
            ?.toBitmap(300,300)

    private var mFaceX = 0f
    private var mFaceY = 0f

    private var mPath = Path()
    private var mPathPaint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            mFaceBitmap?.let {
                drawBitmap(it,mFaceX,mFaceY,null)
            }
            drawPath(mPath,mPathPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.apply {
            when(action) {
                MotionEvent.ACTION_DOWN -> {
                    randomRenderFace()
                    mPath.reset()
                    mPath.addRect(0f,0f,width.toFloat(),height.toFloat(),Path.Direction.CW)
                    mPath.addCircle(x,y,300f,Path.Direction.CCW)
                }
                MotionEvent.ACTION_MOVE -> {
                    mPath.reset()
                    mPath.addRect(0f,0f,width.toFloat(),height.toFloat(),Path.Direction.CW)
                    mPath.addCircle(x,y,300f,Path.Direction.CCW)
                }
                MotionEvent.ACTION_UP -> {
                    mPath.reset()
                }
            }
        }
        invalidate()
        performClick()
        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    private fun randomRenderFace() {
        mFaceX = Random.nextInt(width - 300).toFloat()
        mFaceY = Random.nextInt(height - 300).toFloat()
    }

}