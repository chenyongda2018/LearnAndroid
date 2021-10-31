package com.cyd.learnandroid.ui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class PostFabView extends View implements View.OnClickListener {

    public static final String TAG = "post_fab";

    Paint mPaint;
    float xRadius = 0f;
    float yRadius = 0f;
    int mColor = 0xff14B9C7;
    float defaultRadius = 0f;

    float scaleX = 535 / 160.0f;
    float scaleY = 376 / 160.0f;

    private int mState = 0; // 0-close , 1-expand

    private View mShadowLayout;

    private AnimatorSet mExpandAnim;
    private AnimatorSet mCloseAnim;
    public static final int DURATION = 150;

    public PostFabView(Context context) {
        this(context, null);
    }

    public PostFabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostFabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PostFabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float height = getMeasuredHeight();
        defaultRadius = xRadius = yRadius = height / 2.0f;

        mExpandAnim = getExpandAnim(getContext());
        mCloseAnim = getCloseAnim(getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int leftTopX = 0;
        int leftTopY = 0;
        Log.d(TAG, "leftTopX = " + leftTopX + ", leftTopY = " + leftTopY);

        int rightBottomX = getWidth();
        int rightBottomY = getHeight();
        Log.d(TAG, "rightBottomX = " + rightBottomX + ", rightBottomY = " + rightBottomY);

        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(leftTopX, leftTopY, rightBottomX, rightBottomY, xRadius, yRadius, mPaint);

        setPivotX(rightBottomX);
        setPivotY(rightBottomY);
    }

    //按钮展开时的动画集合
    private AnimatorSet getExpandAnim(Context context) {
        AnimatorSet animSet = new AnimatorSet();

        //圆角变化动画
        ValueAnimator radiusAnimator = ValueAnimator.ofFloat(defaultRadius, 30.0f);
        radiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                yRadius = value * 1.4f;
                xRadius = value;
                invalidate();
            }
        });

        //颜色转变动画
        ValueAnimator colorAnimator = ValueAnimator.ofInt(0xff14B9C7,0xffffffff);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.addUpdateListener(mColorUpdateListener);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1f, scaleX);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1f, scaleY);
        ObjectAnimator shadowAnim = ObjectAnimator.ofFloat(mShadowLayout,"alpha",0,0.3f);

        animSet.play(radiusAnimator)
                .with(scaleXAnimator)
                .with(scaleYAnimator)
                .with(colorAnimator)
                .with(shadowAnim);
        animSet.setDuration(DURATION);
        animSet.addListener(animatorListener);

        return animSet;

    }

    //按钮收起时的动画集合
    private AnimatorSet getCloseAnim(Context context) {
        AnimatorSet animSet = new AnimatorSet();

        //圆角变化动画
        ValueAnimator radiusAnimator = ValueAnimator.ofFloat(30.0f, defaultRadius);
        radiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                yRadius = value;
                xRadius = value;
                invalidate();
            }
        });

        //颜色转变动画
        ValueAnimator colorAnimator = ValueAnimator.ofInt(0xffffffff,0xff14B9C7);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.addUpdateListener(mColorUpdateListener);

        ObjectAnimator shadowAnim = ObjectAnimator.ofFloat(mShadowLayout,"alpha",0.3f,0);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", scaleX, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", scaleY, 1f);

        animSet.play(radiusAnimator)
                .with(scaleXAnimator)
                .with(scaleYAnimator)
                .with(colorAnimator)
                .with(shadowAnim);
        animSet.setDuration(DURATION);
        animSet.addListener(animatorListener);

        return animSet;
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if(mState == 0) {
                mState = 1;
            } else {
                mState = 0;
            }

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private ValueAnimator.AnimatorUpdateListener mColorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mColor = (int) animation.getAnimatedValue();
        }
    };

    @Override
    public void onClick(View v) {
        if (mState == 1) {
            if (mCloseAnim != null) {
                mCloseAnim.start();
            }
        } else {
            if (mExpandAnim != null) {
                mExpandAnim.start();
            }
        }
    }

    public void setShadowLayoutView(@NotNull View view) {
        this.mShadowLayout = view;
    }
}
