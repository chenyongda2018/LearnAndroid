package com.example.stock.presentation.company_listings

import com.example.stock.domain.model.CompanyListing

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
