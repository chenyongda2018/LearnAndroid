package com.example.wanandroid.wxartical.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.databinding.ArticleListItemLayoutBinding
import com.example.wanandroid.wxartical.model.bean.WxArticleBean

/**
 * Created by cyd on 2021/12/17
 * 微信文章列表适配器
 **/
class ArticleListAdapter(
    diffCallback: DiffUtil.ItemCallback<WxArticleBean> = WxArticleComparator,
    onItemAction: ((url: String?) -> Unit)?
) : PagingDataAdapter<WxArticleBean, ArticleListAdapter.ArticleItemVH>(diffCallback) {

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

        fun bindData(article: WxArticleBean?) {
            with(itemBinding) {
                title.text = article?.title
                authorTv.text = article?.author
                publishTime.text = article?.niceShareDate

                cardView.setOnClickListener {
                    onItemAction?.invoke(article?.link)
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