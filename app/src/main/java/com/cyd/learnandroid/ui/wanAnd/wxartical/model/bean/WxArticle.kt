package com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean

/**
 * @date: 2021/12/17
 * @author: chenyongda3
 * Description:公众号文章
 */
data class WxArticleListResult(
    val data: Data,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val curPage: Int,
    val datas: List<WxArticleBean>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)


data class WxArticleBean(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val collect: Boolean,
    val desc: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val publishTime: Long,
    val realSuperChapterId: Int,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tag>,
    val title: String,
    val zan: Int
)

data class Tag(
    val name: String,
    val url: String
)