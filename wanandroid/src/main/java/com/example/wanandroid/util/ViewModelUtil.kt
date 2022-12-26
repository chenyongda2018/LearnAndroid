package com.example.wanandroid.util

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * @date: 2022/10/26
 * @author: chenyongda3
 * Description:
 */
object ViewModelUtil {
    fun <VM : ViewModel> createViewModel(
        activity: ComponentActivity,
        factory: ViewModelProvider.Factory? = null,
        position: Int
    ): VM {
        val vbClass =
            (activity.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val viewModel = vbClass[position] as Class<VM>
        return factory?.let {
            ViewModelProvider(
                activity,
                factory
            ).get(viewModel)
        } ?: ViewModelProvider(activity).get(viewModel)
    }
}