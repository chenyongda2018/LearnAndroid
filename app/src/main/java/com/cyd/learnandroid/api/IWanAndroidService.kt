package com.cyd.learnandroid.api

import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxArticleListResult
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxPublicResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by cyd on 2021/12/12
 **/
interface IWanAndroidService {

    //公众号列表
    @GET("/wxarticle/chapters/json")
    fun getWxPublicList(): Call<WxPublicResult>

    //公众号文章列表
    @GET("/wxarticle/list/{chapterId}/{page}/json")
    fun getWxArticleList(
        @Path("chapterId") chapterId: Int,
        @Path("page") page: Int
    ): Call<WxArticleListResult>
}