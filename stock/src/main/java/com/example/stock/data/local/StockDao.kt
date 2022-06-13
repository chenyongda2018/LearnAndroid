package com.example.stock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stock.data.local.entity.CompanyListingEntity
import com.example.stock.domain.model.CompanyListing

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities: List<CompanyListingEntity>
    )

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListings()

    @Query(
        """
            SELECT * FROM companylistingentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
            OR UPPER(:query) == symbol
        """
    )
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}