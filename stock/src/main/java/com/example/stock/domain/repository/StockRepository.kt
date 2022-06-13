package com.example.stock.domain.repository

import com.example.stock.domain.model.CompanyListing
import com.example.stock.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

}