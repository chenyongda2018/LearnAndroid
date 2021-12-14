package com.cyd.learnandroid.ui.wanAnd.wxartical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
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

            mViewBinding?.viewPager?.adapter = ArticleListAdapter(this, wxMedias?.size ?: 0)

        }
        mViewModel.loadWxPublicMedias()


    }


}

class ArticleListAdapter(frag: Fragment, val size: Int) : FragmentStateAdapter(frag) {

    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        return ArticleListFragment()
    }

}


