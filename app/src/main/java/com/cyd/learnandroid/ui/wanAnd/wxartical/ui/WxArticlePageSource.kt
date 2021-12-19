package com.cyd.learnandroid.ui.wanAnd.wxartical.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxArticleRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxArticleBean
import java.lang.Exception

/**
 * Created by cyd on 2021/12/18
 * 文章列表分页
 **/
class WxArticlePageSource(
    private val remoteRepo: WxArticleRepository,
    private val chapterId: Int
) : PagingSource<Int, WxArticleBean>() {

    companion object {
        const val TAG = "WxArticlePageSource"
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WxArticleBean> {
        try {
            val nextPageNumber = params.key ?: 1
            Log.d(TAG, "load(), nextPage: $nextPageNumber")
            val response = remoteRepo.getArticleList(chapterId, nextPageNumber)?.data

            val data = response?.datas ?: emptyList()
            Log.d(TAG, "load(), data: ${data.toString()}")

            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (nextPageNumber >= response?.pageCount ?: 1) null else nextPageNumber.plus(
                    1
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, WxArticleBean>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}