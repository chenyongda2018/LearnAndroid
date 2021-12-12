package com.cyd.learnandroid.ui.wxartical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cyd.learnandroid.api.WanAndroidApi
import com.cyd.learnandroid.databinding.ActivityWxPublicMediaListBinding
import com.cyd.learnandroid.ui.wxartical.model.bean.WxPublicResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WxPublicMediaListActivity : AppCompatActivity() {
    
    companion object {
        val TAG: String = WxPublicMediaListActivity::class.java.simpleName
    }

    private lateinit var mVb : ActivityWxPublicMediaListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVb = ActivityWxPublicMediaListBinding.inflate(layoutInflater)
        setContentView(mVb.root)

        mVb.getWxPublicListBtn.setOnClickListener {
            WanAndroidApi.getService()?.getWxPublicList()?.enqueue(object : Callback<WxPublicResult> {
                override fun onResponse(
                    call: Call<WxPublicResult>,
                    response: Response<WxPublicResult>
                ) {
                    response.body()?.let {
                        Log.d(TAG,it.toString())
                        mVb.resultTv.text = it.toString()
                    }
                }

                override fun onFailure(call: Call<WxPublicResult>, t: Throwable) {
                    
                }
            })
        }

    }
}