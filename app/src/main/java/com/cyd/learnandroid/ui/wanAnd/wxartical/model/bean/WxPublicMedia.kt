package com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean

/**
 * Created by cyd on 2021/12/12
 * 微信公众号
 **/

data class WxPublicResult(
    val data: List<WxPublicMedia>,
    val errorCode: Int,
    val errorMsg: String
) {
    data class WxPublicMedia(
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Long,
        val parentChapterId: Int,
        val userControlSetTop: Boolean
    )
}



