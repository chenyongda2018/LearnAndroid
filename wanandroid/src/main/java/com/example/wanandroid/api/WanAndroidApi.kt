package com.example.wanandroid.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by cyd on 2021/12/12
 **/
object WanAndroidApi {
    private const val BASE_URL = "https://www.wanandroid.com"

    var retrofit: Retrofit? = null


    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10_000, TimeUnit.MILLISECONDS)
            .readTimeout(10_000, TimeUnit.MILLISECONDS)
            .writeTimeout(10_000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    fun getService(): IWanAndroidService? {
        return retrofit?.create(IWanAndroidService::class.java)
    }
}