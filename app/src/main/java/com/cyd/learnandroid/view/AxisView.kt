package com.cyd.learnandroid.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.withTranslation
import com.cyd.learnandroid.R
import com.cyd.learnandroid.util.UI

/**
 * Created by cyd on 2021/1/26.
 */
class AxisView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mRadius = 0f
    private var mHeight = 0f
    private var mWidth = 0f
    private var mSolidPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = ContextCompat.getColor(context, R.color.white)
        strokeWidth = 5f
    }

    private var mTextPaint = Paint().apply {
        textSize = 40f
        typeface = Typeface.DEFAULT_BOLD
        color = ContextCompat.getColor(context, R.color.white)
    }

    private var mDashedPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.teal_200)
        pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawAxis(this)
            drawLabel(this)
            drawDashedCircle(this)
        }
    }

    private fun drawDashedCircle(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight * 3 / 4) {
            canvas.drawCircle(0f, 0f,mRadius,mDashedPaint)
        }
    }

    private fun drawLabel(canvas: Canvas) {
        canvas.apply {
            canvas.drawRect(90f, 100f, 500f, 250f, mSolidPaint)
            canvas.drawText("指数函数与旋转矢量", 120f, 195f, mTextPaint)
        }
    }

    private fun drawAxis(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 2) {
            canvas.drawLine(-mWidth / 2, 0f, mWidth / 2, 0f, mSolidPaint)
            canvas.drawLine(0f, mHeight / 2, 0f, -mHeight / 2, mSolidPaint)
        }

        canvas.withTranslation(mWidth / 2, mHeight * 3 / 4f) {
            canvas.drawLine(-mWidth / 2f, 0f, mWidth / 2, 0f, mSolidPaint)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mHeight = h.toFloat()
        mWidth = w.toFloat()
        mRadius = if (mHeight > mWidth / 2) mWidth / 2 else mHeight / 4
    }
}