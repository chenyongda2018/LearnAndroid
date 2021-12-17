package com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxArticleRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxArticleBean
import kotlinx.coroutines.launch

/**
 * Created by cyd on 2021/12/18
 **/
class WxArticleListViewModel(
    private val remoteRepo: WxArticleRepository
) : ViewModel() {

    private var _articleList: MutableLiveData<List<WxArticleBean>> = MutableLiveData()

    val articleList: LiveData<List<WxArticleBean>> = _articleList


    fun getArticleList(chapterId: Int, page: Int) {
        viewModelScope.launch {
            _articleList.value = remoteRepo.getArticleList(chapterId,page)?.data?.datas
        }
    }

}