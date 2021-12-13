package com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel

import androidx.lifecycle.*
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxPublicRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxPublicMedia
import kotlinx.coroutines.launch

/**
 * Created by cyd on 2021/12/12
 **/
class WxPublicListViewModel(private val remoteRepo: WxPublicRepository) : ViewModel() {

    private var _wxPublicMedias: MutableLiveData<List<WxPublicMedia>?> = MutableLiveData()

    val wxPublicMedias : LiveData<List<WxPublicMedia>?> = _wxPublicMedias

    /**
     * 公众号列表
     */
    fun loadWxPublicMedias() {
        viewModelScope.launch {
            _wxPublicMedias.value = remoteRepo.getWxPublicMedias()?.data
        }
    }

}