package com.cyd.learnandroid.ui.wanAnd.wxartical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.cyd.learnandroid.databinding.FragWxMediaTabLayoutBinding
import com.cyd.learnandroid.ui.base.BaseFragment
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.WxPublicRepository
import com.cyd.learnandroid.ui.wanAnd.wxartical.model.bean.WxPublicMedia
import com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel.WxPublicListViewModel
import com.cyd.learnandroid.ui.wanAnd.wxartical.viewmodel.WxPublicListViewModelFactory
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers

/**
 * Created by cyd on 2021/12/13
 **/
class WxMediaFragment : BaseFragment<FragWxMediaTabLayoutBinding>() {

    private lateinit var mViewModel: WxPublicListViewModel

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragWxMediaTabLayoutBinding? =
        FragWxMediaTabLayoutBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(
            this,
            WxPublicListViewModelFactory(WxPublicRepository(Dispatchers.IO))
        )[WxPublicListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mViewModel.wxPublicMedias.observe(viewLifecycleOwner) { wxMedias ->
            //添加tabLayout
            wxMedias?.forEachIndexed { idx, media ->
                mViewBinding?.tabLayout?.let {
                    it.addTab(it.newTab().setText(media.name).setId(idx))
                }
            }

            mViewBinding?.viewPager?.adapter = WxArticlePageAdapter(this, wxMedias)
        }
        bindVp2Tab()
        mViewModel.loadWxPublicMedias()
    }

    private fun bindVp2Tab() {
        mViewBinding?.tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewBinding?.viewPager?.currentItem = tab?.id ?: 0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        mViewBinding?.viewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mViewBinding?.tabLayout?.let {
                    it.selectTab(it.getTabAt(position))
                }
            }
        })
    }

}

class WxArticlePageAdapter(frag: Fragment, private val wxMediaList: List<WxPublicMedia>?) :
    FragmentStateAdapter(frag) {

    override fun getItemCount(): Int = wxMediaList?.size ?: 0

    override fun createFragment(position: Int): Fragment {
        return ArticleListFragment.newInstance(wxMediaList?.get(position)?.id ?: 400)
    }

}


