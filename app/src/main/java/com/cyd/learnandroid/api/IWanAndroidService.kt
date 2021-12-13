package com.cyd.learnandroid.api

import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxPublicResult
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by cyd on 2021/12/12
 **/
interface IWanAndroidService {

    @GET("/wxarticle/chapters/json")
    fun getWxPublicList() : Call<WxPublicResult>
}