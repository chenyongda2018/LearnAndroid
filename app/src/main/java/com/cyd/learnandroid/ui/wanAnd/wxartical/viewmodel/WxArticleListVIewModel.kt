package com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxArticleRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxArticleBean
import com.cyd.learnandroid.ui.wanAnd.wxartical.ui.WxArticlePageSource
import kotlinx.coroutines.launch
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