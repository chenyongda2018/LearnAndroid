package com.cyd.learnandroid.ui.wanAnd.wxartical.model

import com.cyd.learnandroid.api.IWanAndroidService
import com.cyd.learnandroid.api.WanAndroidApi
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxPublicResult
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