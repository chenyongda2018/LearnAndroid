package com.example.stock.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = "EBNLT92CYEAOVQPY"
        const val BASE_URL = "https://www.alphavantage.co"
    }

}