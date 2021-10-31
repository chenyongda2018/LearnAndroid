package com.cyd.learnandroid.customview.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DiscretePathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @date: 2021/10/31
 * @author: chenyongda3
 * Description:
 */
class MySurfaceView @JvmOverloads
constructor(ctx: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    SurfaceView(ctx, attributeSet, defStyleAttr) {

    var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5.0f
        pathEffect = DiscretePathEffect(30f,20f)
    }

    var mColors =
        arrayListOf(Color.BLUE, Color.GRAY, Color.RED, Color.GREEN, Color.CYAN, Color.WHITE)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        bubbleList.add(Bubble(event?.x ?: 0f, event?.y ?: 0f, 1f, mColors.random()))
        if(bubbleList.size > 30) bubbleList.removeAt(0)
        return super.onTouchEvent(event)
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if(holder.surface.isValid) {
                    val canvas = holder.lockCanvas()
                    canvas.drawColor(Color.BLACK)
                    bubbleList.toList().filter { it.radius < 2000f }.forEach {
                        mPaint.color = it.color
                        canvas.drawCircle(it.x, it.y, it.radius, mPaint)
                        it.radius += 10f

                    }
                    holder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    private val bubbleList = mutableListOf<Bubble>()

    private data class Bubble(
        var x: Float = 0f,
        var y: Float = 0f,
        var radius: Float = 0f,
        var color: Int = Color.BLACK
    )
}