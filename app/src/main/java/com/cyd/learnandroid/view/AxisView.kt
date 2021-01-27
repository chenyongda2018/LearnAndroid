package com.cyd.learnandroid.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.withRotation
import androidx.core.graphics.withTranslation
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.cyd.learnandroid.R
import com.cyd.learnandroid.util.UI
import kotlinx.coroutines.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Created by cyd on 2021/1/26.
 */
class AxisView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), LifecycleObserver {

    private var mWavePath: Path = Path()

    private var mJob: Job? = null

    private var mAngle = 10f

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

    private var mVectorPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.purple_200)
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private var mPointPaint = Paint().apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.white)
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            drawAxis(this)
            drawLabel(this)
            drawDashedCircle(this)
            drawVector(this)
            drawProjections(this)
            drawWavePath(this)
        }
    }

    private fun drawWavePath(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 2) {
            var count = 100
            var dy = mHeight / 2 / count
            mWavePath.reset()
            mWavePath.moveTo(mRadius * cos(mAngle.toRadians()), 0f)
            repeat(count) {
                var x = (mRadius * cos(it * -0.15 + mAngle.toRadians()))
                var y = -dy * it
                mWavePath.quadTo(x.toFloat(), y, x.toFloat(), y)
            }
            drawPath(mWavePath, mVectorPaint)
            drawTextOnPath("hello",mWavePath, 1000f,20f, mTextPaint)
        }
    }

    private fun drawProjections(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight / 2) {
            drawCircle(mRadius * cos(mAngle.toRadians()), 0f, 18f, mPointPaint)
        }

        canvas.withTranslation(mWidth / 2, mHeight * 3 / 4) {
            drawCircle(mRadius * cos(mAngle.toRadians()), 0f, 18f, mPointPaint)
        }

        canvas.withTranslation(mWidth / 2, mHeight * 3 / 4) {
            var x = mRadius * cos(mAngle.toRadians())
            var y = mRadius * sin(mAngle.toRadians())

            withTranslation(x, -y) {
                drawLine(0f, 0f, 0f, -(mHeight / 4 - y), mDashedPaint)
                drawLine(0f, 0f, 0f, y, mSolidPaint)
            }
        }
    }

    private fun drawVector(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight * 3 / 4) {
            withRotation(-mAngle) {
                drawLine(0f, 0f, mRadius, 0f, mVectorPaint)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun startRotating() {
        mJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(16)
                mAngle += 1f
                invalidate()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun pauseRotating() {
        mJob?.cancel()
    }

    //绘制虚线圆
    private fun drawDashedCircle(canvas: Canvas) {
        canvas.withTranslation(mWidth / 2, mHeight * 3 / 4) {
            canvas.drawCircle(0f, 0f, mRadius, mDashedPaint)
        }
    }

    //绘制标题
    private fun drawLabel(canvas: Canvas) {
        canvas.apply {
            canvas.drawRect(90f, 100f, 500f, 250f, mSolidPaint)
            canvas.drawText("指数函数与旋转矢量", 120f, 195f, mTextPaint)
        }
    }

    //绘制坐标轴
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
        mRadius = mHeight / 4
        mRadius -= 20f
    }

    private fun Float.toRadians() = this / 180 * PI.toFloat()
}