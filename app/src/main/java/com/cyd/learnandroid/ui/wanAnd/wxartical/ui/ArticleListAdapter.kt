package com.cyd.learnandroid.ui.wanAnd.wxartical.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cyd.learnandroid.databinding.ArticleListItemLayoutBinding
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxArticleBean

/**
 * Created by cyd on 2021/12/17
 * 微信文章列表适配器
 **/
class ArticleListAdapter(
    diffCallback: DiffUtil.ItemCallback<WxArticleBean> = WxArticleComparator
) : PagingDataAdapter<WxArticleBean, ArticleListAdapter.ArticleItemVH>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemVH {
        val itemBinding =
            ArticleListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleItemVH(itemBinding)
    }

    override fun onBindViewHolder(holder: ArticleItemVH, position: Int) {
        holder.bindData(getItem(position))
    }

    class ArticleItemVH(private val itemBinding: ArticleListItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(article: WxArticleBean?) {
            with(itemBinding) {
                title.text = article?.title
                authorTv.text = article?.author
                publishTime.text = article?.niceShareDate

                cardView.setOnClickListener {
                    Toast.makeText(itemBinding.root.context, article?.link, Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

    }
}

object WxArticleComparator : DiffUtil.ItemCallback<WxArticleBean>() {
    override fun areItemsTheSame(oldItem: WxArticleBean, newItem: WxArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WxArticleBean, newItem: WxArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

}