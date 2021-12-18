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
//
//    override fun getItemCount(): Int = data?.size ?: 0
//
//
//    fun updateData(data: List<WxArticleBean>?) {
//        if (this.data == null || this.data!!.isEmpty()) {
//            this.data = data
//            notifyDataSetChanged()
//            return
//        }
//
//        this.data?.let {
//            val lastIndex = it.size
//            val newData = arrayListOf<WxArticleBean>()
//            newData.addAll(it)
//            if (data != null) {
//                newData.addAll(data)
//            }
//            val newIndex = newData.size
//            if (lastIndex < newIndex) {
//                notifyItemRangeInserted(lastIndex, newIndex - 1)
//            }
//        }
//    }
//

    class ArticleItemVH(private val itemBinding: ArticleListItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(article: WxArticleBean?) {
            itemBinding.title.text = article?.title
            itemBinding.cardView.setOnClickListener {
                Toast.makeText(itemBinding.root.context, article?.link, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

object WxArticleComparator: DiffUtil.ItemCallback<WxArticleBean>() {
    override fun areItemsTheSame(oldItem: WxArticleBean, newItem: WxArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WxArticleBean, newItem: WxArticleBean): Boolean {
        return oldItem.id == newItem.id
    }

}