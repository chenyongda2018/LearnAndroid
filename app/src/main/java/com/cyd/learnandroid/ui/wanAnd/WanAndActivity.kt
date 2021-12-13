package com.cyd.learnandroid.ui.wanAnd

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cyd.learnandroid.databinding.ActivityWanAndBinding
import com.cyd.learnandroid.ui.base.BaseActivity
import com.cyd.learnandroid.ui.wanAnd.wxartical.WxMediaFragment

class WanAndActivity : BaseActivity<ActivityWanAndBinding>() {


    override fun getViewBing(): ActivityWanAndBinding? {
        return ActivityWanAndBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mVb?.toolBar)
        mVb?.viewPager?.adapter = MainFragAdapter(this, mVb?.bottomNav?.childCount?:0)
    }

}

class MainFragAdapter(activity: FragmentActivity, private val size: Int) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment = WxMediaFragment()

}