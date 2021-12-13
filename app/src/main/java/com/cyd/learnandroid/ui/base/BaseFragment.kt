package com.cyd.learnandroid.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings

/**
 * Created by cyd on 2021/12/13
 **/
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected var mViewBinding: VB? = null

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding(
            inflater,
            container,
            savedInstanceState
        )

        return mViewBinding?.root
    }

}