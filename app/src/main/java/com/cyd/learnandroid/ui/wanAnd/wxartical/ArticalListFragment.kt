package com.cyd.learnandroid.ui.wanAnd.wxartical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyd.learnandroid.databinding.FragArticleListLayoutBinding
import com.cyd.learnandroid.ui.base.BaseFragment

/**
 * @date: 2021/12/14
 * @author: chenyongda3
 * Description:
 */
class ArticleListFragment: BaseFragment<FragArticleListLayoutBinding>() {


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragArticleListLayoutBinding? {
        return FragArticleListLayoutBinding.inflate(inflater,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}