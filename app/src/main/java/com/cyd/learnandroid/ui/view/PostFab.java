package com.cyd.learnandroid.ui.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.WindowManager;

import com.cyd.learnandroid.R;

public class PostFab {

    private WindowManager.LayoutParams mLayoutParams;

    private PostFabView mPostView;

    private WindowManager mWindowManager;

    private Context mContext;

    public PostFab(Context context) {
        this.mContext = context;
        initParams(context);
    }

    private void initParams(Context context) {
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.flags = mLayoutParams.flags
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mLayoutParams.height = 180;
        mLayoutParams.width = 180;

        mLayoutParams.format = PixelFormat.RGBA_8888;
        mLayoutParams.alpha = 1.0f;
        mLayoutParams.x = 100;
        mLayoutParams.y = 100;
        mWindowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);

        mPostView = (PostFabView) LayoutInflater.from(context).inflate(R.layout.post_fab_layout,null,false);
    }

    public void addPostView() {
        mWindowManager.addView(mPostView,mLayoutParams);
    }


    public void dismiss() {
        mWindowManager.removeView(mPostView);
    }

    public PostFabView getPostView() {
        return mPostView;
    }

    public void updateWindowManager() {
        mWindowManager.updateViewLayout(mPostView,mLayoutParams);
    }
}
