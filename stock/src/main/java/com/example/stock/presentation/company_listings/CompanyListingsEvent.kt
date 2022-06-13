package com.example.stock.presentation.company_listings

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
sealed class CompanyListingsEvent{
    object Refresh: CompanyListingsEvent()
    data class OnSearchQueryChange(val query: String): CompanyListingsEvent()
}
