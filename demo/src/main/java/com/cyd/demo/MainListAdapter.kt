package com.cyd.demo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by cyd on 2022/5/27
 **/
class MainListAdapter(
    private val context: Context,
    private val dataList: List<MainIntentBean>,
    private val onItemClick: (bean: MainIntentBean, position: Int) -> Unit
) : RecyclerView.Adapter<MainListAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_rv_simple_item_layout, parent, false)
        return MainViewHolder(view,onItemClick)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(dataList[position],position)
    }

    class MainViewHolder(
        itemView: View,
        private val onItemClick: (bean: MainIntentBean, position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bindData(intentBean: MainIntentBean,position: Int) {
            itemView.findViewById<TextView>(R.id.label).text = intentBean.label
            itemView.setOnClickListener { onItemClick(intentBean,position) }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

data class MainIntentBean(
    val label: String,
    val intent: Intent
)
