package com.example.stock.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
@Entity
data class CompanyListingEntity(
    val name: String,
    val symbol: String,
    val exchange: String,
    @PrimaryKey
    var id: Int? = null
)