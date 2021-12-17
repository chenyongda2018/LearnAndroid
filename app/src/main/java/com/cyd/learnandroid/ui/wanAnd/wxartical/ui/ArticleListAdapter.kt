package com.cyd.learnandroid.ui.wanAnd.wxartical.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cyd.learnandroid.databinding.ArticleListItemLayoutBinding
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxArticleBean

/**
 * Created by cyd on 2021/12/17
 **/
class ArticleListAdapter(
    private var data: List<WxArticleBean>?
) : RecyclerView.Adapter<ArticleListAdapter.ArticleItemVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemVH {
        val itemBinding =
            ArticleListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleItemVH(itemBinding)
    }

    override fun onBindViewHolder(holder: ArticleItemVH, position: Int) {
        holder.bindData(data?.get(position))
    }

    override fun getItemCount(): Int = data?.size ?: 0


    fun updateData(data: List<WxArticleBean>?) {
        if(this.data == null || this.data!!.isEmpty()) {
            this.data = data
            notifyDataSetChanged()
            return
        }

        this.data?.let {
            val lastIndex = it.size
            val newData = arrayListOf<WxArticleBean>()
            newData.addAll(it)
            if (data != null) {
                newData.addAll(data)
            }
            val newIndex = newData.size
            if(lastIndex < newIndex) {
                notifyItemRangeInserted(lastIndex,newIndex - 1)
            }
        }
    }



    class ArticleItemVH(private val itemBinding: ArticleListItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindData(article: WxArticleBean?) {
            itemBinding.title.text = article?.title
        }
    }
}