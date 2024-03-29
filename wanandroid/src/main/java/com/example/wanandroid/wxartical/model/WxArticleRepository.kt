package com.example.wanandroid.wxartical.model

import com.example.wanandroid.api.WanAndroidApi
import com.example.wanandroid.wxartical.model.bean.WxArticleListResult
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

    suspend fun getArticleList(chapterId: Int, page: Int = 1): WxArticleListResult? =
        withContext(defaultDispatcher) {
            WanAndroidApi.getService()
                ?.getWxArticleList(chapterId, page)?.execute()?.body()
        }
}