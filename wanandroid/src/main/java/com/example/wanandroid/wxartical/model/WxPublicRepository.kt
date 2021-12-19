package com.example.wanandroid.wxartical.model

import com.example.wanandroid.api.WanAndroidApi
import com.example.wanandroid.wxartical.model.bean.WxPublicResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by cyd on 2021/12/12
 * 公众号列表
 **/
class WxPublicRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun getWxPublicMedias(): WxPublicResult? = withContext(defaultDispatcher) {
        WanAndroidApi
            .getService()
            ?.getWxPublicList()?.execute()?.body()
    }

}