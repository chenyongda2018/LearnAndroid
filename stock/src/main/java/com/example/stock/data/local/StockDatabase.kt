package com.example.stock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stock.data.local.entity.CompanyListingEntity

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
@Database(
    entities = [CompanyListingEntity::class],
    version = 1
)
abstract class StockDatabase : RoomDatabase() {
    abstract val dao: StockDao
}