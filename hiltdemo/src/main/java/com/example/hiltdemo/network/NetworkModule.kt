package com.example.hiltdemo.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @date: 2022/12/28
 * @author: chenyongda3
 * Description:
 */
@Module
@InstallIn(ActivityComponent::class)//将此模块安装到Activity组件中
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(20,TimeUnit.MILLISECONDS)
            .readTimeout(20,TimeUnit.MILLISECONDS)
            .writeTimeout(20,TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://example.com")
            .client(okHttpClient)
            .build()
    }
}