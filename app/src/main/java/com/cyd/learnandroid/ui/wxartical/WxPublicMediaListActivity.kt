package com.cyd.learnandroid.ui.wxartical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cyd.learnandroid.databinding.ActivityWxPublicMediaListBinding
import com.cyd.learnandroid.ui.wxartical.model.WxPublicRepository
import com.cyd.learnandroid.ui.wxartical.viewmodel.WxPublicListViewModel
import kotlinx.coroutines.Dispatchers

class WxPublicMediaListActivity : AppCompatActivity() {
    
    companion object {
        val TAG: String = WxPublicMediaListActivity::class.java.simpleName
    }

    private lateinit var mVb : ActivityWxPublicMediaListBinding
    private var mViewModel =  WxPublicListViewModel(WxPublicRepository(Dispatchers.IO))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVb = ActivityWxPublicMediaListBinding.inflate(layoutInflater)
        setContentView(mVb.root)

        mViewModel.wxPublicMedias?.observe(this) { wxMedias ->
            mVb.resultTv.text = wxMedias.toString()
        }

        mVb.getWxPublicListBtn.setOnClickListener {
            mViewModel.loadWxPublicMedias()
        }


    }
}