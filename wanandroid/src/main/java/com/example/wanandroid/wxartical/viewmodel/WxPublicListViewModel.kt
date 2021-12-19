package com.example.wanandroid.wxartical.viewmodel

import androidx.lifecycle.*
import com.example.wanandroid.wxartical.model.WxPublicRepository
import com.example.wanandroid.wxartical.model.bean.WxPublicMedia
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

/**
 * Created by cyd on 2021/12/12
 **/
class WxPublicListViewModel(private val remoteRepo: WxPublicRepository) : ViewModel() {

    private var _wxPublicMedias: MutableLiveData<List<WxPublicMedia>?> = MutableLiveData()

    val wxPublicMedias: LiveData<List<WxPublicMedia>?> = _wxPublicMedias

    /**
     * 公众号列表
     */
    fun loadWxPublicMedias() {
        viewModelScope.launch {
            _wxPublicMedias.value = remoteRepo.getWxPublicMedias()?.data
        }
    }

}

class WxPublicListViewModelFactory(private val remoteRepo: WxPublicRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WxPublicListViewModel::class.java)) {
            return WxPublicListViewModel(remoteRepo) as T
        }
        throw IllegalArgumentException("Can't find the viewModel")
    }


}