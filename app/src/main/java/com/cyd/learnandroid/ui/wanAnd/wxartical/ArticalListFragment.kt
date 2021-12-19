package com.cyd.learnandroid.ui.wanAnd.wxartical

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyd.learnandroid.databinding.FragArticleListLayoutBinding
import com.cyd.learnandroid.ui.base.BaseFragment
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxArticleRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.ui.ArticleListAdapter
import com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel.WxArticleListViewModel
import com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel.WxArticleListViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @date: 2021/12/14
 * @author: chenyongda3
 * Description:公众号列表页
 */
class ArticleListFragment private constructor() : BaseFragment<FragArticleListLayoutBinding>() {

    private lateinit var mViewModel: WxArticleListViewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragArticleListLayoutBinding? {
        return FragArticleListLayoutBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        val chapterId = arguments?.get(EXTRA_CHAPTER_ID) as Int
        Log.d(TAG, "chapterId: $chapterId")
        mViewModel = ViewModelProvider(
            this,
            WxArticleListViewModelFactory(WxArticleRepository(Dispatchers.IO), chapterId)
        )[WxArticleListViewModel::class.java]

        val pagingAdapter = ArticleListAdapter()

        mViewBinding?.recyclerView?.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = pagingAdapter
        }

        lifecycleScope.launch {
            mViewModel.articleFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun refresh() {
//        lifecycleScope.launch {
//            mViewModel.articleFlow.
//        }
    }

    companion object {
        const val TAG = "ArticleListFragment"
        private const val EXTRA_CHAPTER_ID = "extras_chapter_id_key"

        fun newInstance(chapterId: Int): ArticleListFragment {
            val args = Bundle()
            args.putInt(EXTRA_CHAPTER_ID, chapterId)
            val fragment = ArticleListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}