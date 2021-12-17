package com.cyd.learnandroid.ui.wanAnd.wxartical

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cyd.learnandroid.databinding.FragArticleListLayoutBinding
import com.cyd.learnandroid.ui.base.BaseFragment
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxArticleRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.ui.ArticleListAdapter
import com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel.WxArticleListViewModel
import kotlinx.coroutines.Dispatchers

/**
 * @date: 2021/12/14
 * @author: chenyongda3
 * Description:公众号列表页
 */
class ArticleListFragment private constructor() : BaseFragment<FragArticleListLayoutBinding>() {

    private val mViewModel: WxArticleListViewModel =
        WxArticleListViewModel(WxArticleRepository(Dispatchers.IO))

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragArticleListLayoutBinding? {
        return FragArticleListLayoutBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        val chapterId = arguments?.get(EXTRA_CHAPTER_ID) as Int
        Log.d(TAG, "chapterId: $chapterId")


        val adapter = ArticleListAdapter(null)

        mViewBinding?.recyclerView?.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        mViewModel.articleList.observe(this) { articles ->
            Log.d(TAG, articles?.toString() + "")
            adapter.updateData(articles)
        }

        mViewModel.getArticleList(chapterId, 1)

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