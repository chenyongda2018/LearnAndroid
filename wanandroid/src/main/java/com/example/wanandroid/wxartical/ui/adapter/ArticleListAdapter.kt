package com.example.wanandroid.wxartical.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.databinding.ArticleListItemLayoutBinding
import com.example.wanandroid.wxartical.model.bean.ArticleBean

/**
 * Created by cyd on 2021/12/17
 * 微信文章列表适配器
 **/
class ArticleListAdapter(
    diffCallback: DiffUtil.ItemCallback<ArticleBean> = WxArticleComparator,
    onItemAction: ((url: String?) -> Unit)?
) : PagingDataAdapter<ArticleBean, ArticleListAdapter.ArticleItemVH>(diffCallback) {

    private var onItemClickAction: ((url: String?) -> Unit)? = onItemAction

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemVH {
        val itemBinding =
            ArticleListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleItemVH(itemBinding,onItemClickAction)
    }

    override fun onBindViewHolder(holder: ArticleItemVH, position: Int) {
        holder.bindData(getItem(position))
    }

    class ArticleItemVH(
        private val itemBinding: ArticleListItemLayoutBinding,
        private val onItemAction: ((url: String?) -> Unit)? = null
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(article: ArticleBean?) {
            with(itemBinding) {
                title.text = article?.title
                authorTv.text = article?.author
                publishTime.text = article?.niceShareDate

                cardView.setOnClickListener { onItemAction?.invoke(article?.link) }
            }

        }

    }
}

object WxArticleComparator : DiffUtil.ItemCallback<ArticleBean>() {
    override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

}