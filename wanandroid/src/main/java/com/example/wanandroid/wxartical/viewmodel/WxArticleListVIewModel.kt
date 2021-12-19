package com.example.wanandroid.wxartical.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.wanandroid.wxartical.model.WxArticleRepository
import com.example.wanandroid.wxartical.ui.adapter.WxArticlePageSource
import java.lang.IllegalArgumentException

/**
 * Created by cyd on 2021/12/18
 **/
class WxArticleListViewModel(
    private val remoteRepo: WxArticleRepository,
    private val chapterId: Int
) : ViewModel() {

    val articleFlow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        WxArticlePageSource(remoteRepo, chapterId)
    }.flow.cachedIn(viewModelScope)

}

class WxArticleListViewModelFactory(
    private val remoteRepo: WxArticleRepository,
    private val chapterId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WxArticleListViewModel::class.java)) {
            return WxArticleListViewModel(remoteRepo, chapterId) as T
        }
        throw IllegalArgumentException("Can't find the viewModel")
    }

}