package com.example.wanandroid

import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.wanandroid.base.BaseActivity
import com.example.wanandroid.databinding.ActivityWanAndBinding
import com.example.wanandroid.wxartical.WxMediaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class WanAndActivity : BaseActivity<ActivityWanAndBinding>() {
    companion object {
        const val INDEX_HOME = 0
        const val INDEX_SQUARE = 1
        const val INDEX_WX_PUBLIC = 2
        const val INDEX_SYSTEM = 3
        const val INDEX_PROGRAM = 4
    }

    private val fragments by lazy {
        mapOf(
            INDEX_HOME to NavHostFragment(),
            INDEX_SQUARE to NavHostFragment(),
            INDEX_WX_PUBLIC to WxMediaFragment(),
            INDEX_SYSTEM to NavHostFragment(),
            INDEX_PROGRAM to NavHostFragment(),
        )
    }

    override fun getViewBing(): ActivityWanAndBinding? {
        return ActivityWanAndBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mVb?.toolBar)
        mVb?.viewPager?.adapter = MainFragAdapter(this, fragments)
        BnvVp2Mediator(mVb?.bottomNav, mVb?.viewPager) { bnv, vp2 ->
            vp2?.isUserInputEnabled = false
        }.attach()
    }

}

class MainFragAdapter(activity: FragmentActivity, private val fragments: Map<Int, Fragment>) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position] ?: error("can't find fragment")
    }
}

class BnvVp2Mediator(
    private val bnv: BottomNavigationView?,
    private val vp2: ViewPager2?,
    private val configAction: ((BottomNavigationView?, ViewPager2?) -> Unit)? = null
) {
    val map = mutableMapOf<MenuItem, Int>()

    init {
        bnv?.menu?.forEachIndexed { index, item -> map[item] = index }
    }

    fun attach() {
        configAction?.invoke(bnv, vp2)

        vp2?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                bnv?.selectedItemId = bnv?.menu?.getItem(position)?.itemId ?: 0
            }
        })

        bnv?.setOnItemSelectedListener { menuItem ->
            vp2?.currentItem = map[menuItem] ?: error("Can not find menuItem")

            true
        }
    }
}