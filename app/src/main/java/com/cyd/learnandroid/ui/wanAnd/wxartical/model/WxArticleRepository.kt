package com.cyd.learnandroid.ui.wanAnd.wxartical.model

import com.cyd.learnandroid.api.IWanAndroidService
import com.cyd.learnandroid.api.WanAndroidApi
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxArticleListResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @date: 2021/12/17
 * @author: chenyongda3
 * Description:
 */
class WxArticleRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend fun getArticleList(chapterId: Int, page: Int): WxArticleListResult? =
        withContext(defaultDispatcher) {
            WanAndroidApi.getService()
                ?.getWxArticleList(chapterId, page)?.execute()?.body()
        }
}