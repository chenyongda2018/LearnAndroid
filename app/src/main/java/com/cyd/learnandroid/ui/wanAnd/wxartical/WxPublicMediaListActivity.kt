package com.cyd.learnandroid.ui.wanAnd.wxartical

import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityWxPublicMediaListBinding
import com.cyd.learnandroid.ui.base.BaseActivity
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxPublicRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel.WxPublicListViewModel
import kotlinx.coroutines.Dispatchers

class WxPublicMediaListActivity : BaseActivity() {
    
    companion object {
        val TAG: String = WxPublicMediaListActivity::class.java.simpleName
    }

    private lateinit var mVb : ActivityWxPublicMediaListBinding

    private var mViewModel =  WxPublicListViewModel(WxPublicRepository(Dispatchers.IO))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVb = ActivityWxPublicMediaListBinding.inflate(layoutInflater)
        setContentView(mVb.root)

        mViewModel.wxPublicMedias.observe(this) { wxMedias ->
            wxMedias?.forEach {
                mVb.tabLayout.addTab(mVb.tabLayout.newTab().setText(it.name))
            }
        }
        mViewModel.loadWxPublicMedias()
    }
}