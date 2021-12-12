package com.cyd.learnandroid.ui.wxartical.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.cyd.learnandroid.ui.wxartical.model.WxPublicRepository
import com.cyd.learnandroid.ui.wxartical.model.bean.WxPublicMedia

/**
 * Created by cyd on 2021/12/12
 **/
class WxPublicListViewModel(private val remoteRepo: WxPublicRepository) : ViewModel() {

    val wxPublicMedias: LiveData<List<WxPublicMedia>?> = liveData {
        emit(remoteRepo.getWxPublicMedias()?.data)
    }

}