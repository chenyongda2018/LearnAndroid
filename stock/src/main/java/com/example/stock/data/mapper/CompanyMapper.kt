package com.example.stock.data.mapper

import com.example.stock.data.local.entity.CompanyListingEntity
import com.example.stock.domain.model.CompanyListing

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}