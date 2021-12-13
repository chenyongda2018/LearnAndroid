package com.cyd.learnandroid.ui.wanAnd.wxartical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyd.learnandroid.databinding.FragWxMediaTabLayoutBinding
import com.cyd.learnandroid.ui.base.BaseFragment
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxPublicRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel.WxPublicListViewModel
import kotlinx.coroutines.Dispatchers

/**
 * Created by cyd on 2021/12/13
 **/
class WxMediaFragment : BaseFragment<FragWxMediaTabLayoutBinding>() {

    private var mViewModel = WxPublicListViewModel(WxPublicRepository(Dispatchers.IO))

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragWxMediaTabLayoutBinding? =
        FragWxMediaTabLayoutBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.wxPublicMedias.observe(viewLifecycleOwner) { wxMedias ->
            wxMedias?.forEach { media ->
                mViewBinding?.tabLayout?.let {
                    it.addTab(it.newTab().setText(media.name))
                }
            }
        }
        mViewModel.loadWxPublicMedias()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mViewBinding = null
    }
}


