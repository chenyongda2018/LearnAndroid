package com.example.wanandroid.wxartical.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import com.example.wanandroid.base.BaseActivity
import com.example.wanandroid.databinding.ActivityWxArticleBinding

class WxArticleActivity : BaseActivity<ActivityWxArticleBinding>() {

    override fun getViewBing(): ActivityWxArticleBinding? {
        return ActivityWxArticleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.extras?.getString(EXTRA_URL_KEY) ?: ""

        if(url.isNotBlank()) {
            mVb?.webViewLayout?.settings?.apply {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    javaScriptEnabled = true
                    blockNetworkImage = false
                }
            }
            mVb?.webViewLayout?.loadUrl(url)
        }
    }


    companion object {

        private const val EXTRA_URL_KEY = "EXTRA_URL_KEY"

        @JvmStatic
        fun start(context: Context?, url: String?) {
            val starter = Intent(context, WxArticleActivity::class.java)
                .putExtra(EXTRA_URL_KEY, url)
            context?.startActivity(starter)
        }
    }
}